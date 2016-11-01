package com.example.anna.neptis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PortalsMainActivity extends AppCompatActivity {

    boolean accedi = false;
    final static int RQ_CODE = 1;
    private SharedPreferences prefs;
    private String pre;

    private String user,pass;
    private User current_user;
    TextView utente_loggato;

    private boolean flag_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portals_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        utente_loggato = (TextView)findViewById(R.id.nome_user);
        utente_loggato.setText("Eseguire l'accesso");


        //DEBUG CONTROLLO PREFERENZE//
        prefs = getSharedPreferences("session", Context.MODE_PRIVATE);
        pre = prefs.getString("current_session", "");
        Log.d("Preferenze salvate: ",pre);
        //////////////////////////////
        //Log.d("FLAG: ",Boolean.toString(flag_login));

        /*if(flag_login) {
            Log.d("Accesso eseguito: ","oooooooooooooooooookkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            accedi = Boolean.parseBoolean(getIntent().getExtras().getString("accedi"));
        }

        if(accedi ) {
            user = getIntent().getExtras().getString("email");
            Log.d("EMAIL: ", user);
            pass = getIntent().getExtras().getString("password");
            Log.d("PASSWORD: ", pass);

            current_user = new User(user, pass);
            utente_loggato.setText(current_user.getEmail());
        }*/


        /*__________________gestione imageButton dei 4 portali____________________*/

        /**
         *
         * MANAGEMENT YELLOW PORTAL
         *
         */
        ImageButton yellowPortalButton = (ImageButton)findViewById(R.id.yellow_portal);
        yellowPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if ( pre == "") {
                    Intent openYellowPortal = new Intent(PortalsMainActivity.this, LoginDialogActivity.class);
                    startActivityForResult(openYellowPortal, RQ_CODE);

                } else {*/
                    Intent openYellowPortal = new Intent(PortalsMainActivity.this, TreasurePortalPag1.class);
                    openYellowPortal.putExtra("user",user);
                    startActivity(openYellowPortal);
                //}
            }
            // }

        });
        /**
         *
         * MANAGEMENT GREEN PORTAL
         *
         */
        ImageButton greenPortalButton = (ImageButton)findViewById(R.id.green_portal);
        greenPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pre == "") {
                    Intent openGreenPortal = new Intent(PortalsMainActivity.this,LoginDialogActivity.class);
                    startActivityForResult(openGreenPortal, RQ_CODE);

                } else {
                    Intent openGreenPortal = new Intent(PortalsMainActivity.this, TravelPortalActivity.class);
                    openGreenPortal.putExtra("user",user);
                    startActivity(openGreenPortal);
                }
            }
        });
        /**
         *
         * MANAGEMENT RED PORTAL
         *
         */
        ImageButton redPortalButton = (ImageButton)findViewById(R.id.red_portal);
        redPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pre == "") {
                    Intent openRedPortal = new Intent(PortalsMainActivity.this,LoginDialogActivity.class);
                    startActivityForResult(openRedPortal, RQ_CODE);
                } else {
                    Intent openRedPortal = new Intent(PortalsMainActivity.this, PuzzlePortal.class);
                    openRedPortal.putExtra("user",user);
                    startActivity(openRedPortal);
                }
            }
        });
        /**
         *
         * MANAGEMENT BLUE PORTAL
         *
         */
        ImageButton bluePortalButton = (ImageButton)findViewById(R.id.blue_portal);
        bluePortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(),"Blue portal",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        /*____________________fine gestione imageButton dei 4 portali_______________________*/


    }

    @Override
    protected void onResume(){
        super.onResume();
        //DEBUG CONTROLLO PREFERENZE//
        prefs = getSharedPreferences("session", Context.MODE_PRIVATE);
        pre = prefs.getString("current_session", "");
        Log.d("Preferenze salvate: ",pre);
        //////////////////////////////

    }


    private void goLoginScreen() {
        Intent openLoginPage = new Intent(this,LoginDialogActivity.class);
        openLoginPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openLoginPage);
    }

    //revoke permession
    void deleteFacebookApplication(){
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions", null, HttpMethod.DELETE, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                boolean isSuccess = false;
                try {
                    isSuccess = response.getJSONObject().getBoolean("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isSuccess && response.getError()==null){
                    // Application deleted from Facebook account
                }

            }
        }).executeAsync();
    }


    public void logout(View view) {

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("current_session", "");
        editor.apply();

        utente_loggato.setText("Eseguire l'accesso");
        accedi = false;

        Toast.makeText(view.getContext(),"LogOutEffettuato",Toast.LENGTH_SHORT).show();
        Intent logout = new Intent(PortalsMainActivity.this,LoginDialogActivity.class);
        startActivityForResult(logout, RQ_CODE);
        /*
        if(LoginDialogActivity.flag_login_session == true) {
            deleteFacebookApplication();
            LoginManager.getInstance().logOut();
            accedi = false;
            Intent openPagLogin = new Intent(PortalsMainActivity.this,LoginDialogActivity.class);
            startActivity(openPagLogin);
            //goLoginScreen();
            //Per uscire dall'app appena viene premuto il tasto dil logout
        */
            /*moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);*/
        /*}else{
            Toast.makeText(view.getContext(),"Impossibile effettuare il logout. Utente non loggato..",Toast.LENGTH_SHORT).show();
        }
        */

        //NB: non viene chiusa l'app, rimane aperta in background
        /*Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
    }

    @Override
   protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RQ_CODE){
           if(resultCode == Activity.RESULT_OK){
                Log.d("Accedi ok: ","Accedi ok");
                //accedi = true;
            }
           /*
            else{
                Log.d("accedi ok: ", " Non ok");
            }*/

            if(resultCode == 1) {
                Log.d("Accesso effettuato: ", "ooooooooooooooooooooookkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                user = data.getStringExtra("email");
                Log.d("EMAIL: ", user);
                pass = data.getStringExtra("password");
                Log.d("PASSWORD: ", pass);

                current_user = new User(user, pass);
                utente_loggato.setText(current_user.getEmail());
            }

        }

    }

}