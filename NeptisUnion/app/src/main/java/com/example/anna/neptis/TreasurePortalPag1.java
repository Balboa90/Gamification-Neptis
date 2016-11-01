package com.example.anna.neptis;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
//import com.facebook.AccessToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;


public class TreasurePortalPag1 extends AppCompatActivity implements OnItemSelectedListener {

    int contLength;
    String [] spinner_options;
    Spinner dropdown;
    private Animation lens_anim = null;
    //private Animation lens_anim2 = null;
    private ImageView lente = null;
    String item;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure_portal_pag1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        user = getIntent().getExtras().getString("user");

        lente = (ImageView)findViewById(R.id.lens);
        //lente.setImageAlpha(100);
        /*******inizio configurazione dello spinner*******/
        dropdown = (Spinner)findViewById(R.id.spinner_menu);

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(dropdown);

            // Set popupWindow height to 500px
            popupWindow.setHeight(320);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            Toast.makeText(getApplicationContext(),"Errore!",Toast.LENGTH_SHORT).show();
        }

        //inserimento item nello spinner da database
        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8000/getHeritagesGame1/";

        // Request a string response from the provided URL.
        JsonArrayRequest jsArray = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Display the first 500 characters of the response string.
                //Log.d("Response is: ", response.toString());
                try{
                    contLength = response.length();
                    spinner_options = new String[contLength+1];
                    spinner_options[0] = "";
                    for(int i = 0;i< contLength;i++){
                        JSONObject jsObj = (JSONObject)response.get(i);
                        String value = jsObj.getString("heritage");
                        spinner_options[i+1] = value;
                        //Log.d("Spinner: ",spinner_options[i]+ "\n");
                        ArrayAdapter<?>adapter = new ArrayAdapter<Object>(TreasurePortalPag1.this,android.R.layout.simple_spinner_dropdown_item,spinner_options);
                        //applico l'adapter allo spinner
                        dropdown.setAdapter(adapter);
                        dropdown.setOnItemSelectedListener(TreasurePortalPag1.this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!",error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsArray);
        //***********_______END TEMPLATE JSON REQUEST________**********
        /*******fine configurazione dello spinner*******/

        /******inizio configurazione bottoni cards_list e achievement_list******/
        ImageButton card_list_image = (ImageButton) findViewById(R.id.cards_list_image);
        card_list_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openManageCard = new Intent(TreasurePortalPag1.this,ManageCards.class);
                startActivity(openManageCard);
                /*Toast toast = Toast.makeText(view.getContext(),"Hai cliccato su cards list",Toast.LENGTH_SHORT);
                toast.show();*/

            }});
        ImageButton achivement_list_image= (ImageButton) findViewById(R.id.achieve);
        achivement_list_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAchievements = new Intent(TreasurePortalPag1.this,Achievements.class);
                //per inviare parametri all'activity Achievement
                openAchievements.putExtra("game","game1");
                startActivity(openAchievements);
            }
        });
        /******fine configurazione bottoni cards_list e achievement_list******/
    }


    /********************gestione click sugli elementi dello spinner*********************/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //prendo il valore dell'elemento selezionato
        item = parent.getItemAtPosition(position).toString();

        Log.d("ITEM SELEZIONATO", item);

        if(!item.equals("")){
            lens_anim = AnimationUtils.loadAnimation(TreasurePortalPag1.this,R.anim.lens_animation);
            lente.startAnimation(lens_anim);

            //l'activity si apre dopo un certo tempo (dopo che è terminata l'animazione)
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    //start your activity here
                    Intent openTreasurePage2 = new Intent(TreasurePortalPag1.this,TreasurePortalPag2.class);
                    openTreasurePage2.putExtra("heritage",item);
                    openTreasurePage2.putExtra("user",user);
                    startActivity(openTreasurePage2);
                }

            }, 1100L);

        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    /********************fine gestione click sugli elementi dello spinner*********************/

}
