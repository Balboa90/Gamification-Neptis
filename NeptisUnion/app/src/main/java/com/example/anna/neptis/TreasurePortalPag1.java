package com.example.anna.neptis;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure_portal_pag1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //questo posso aggiungerlo quando ho sistemato server e database
        /*if(LoginActivity.flag_login_session == false) {*/

        //verifico se la sessione tramite facebook è iniziata
        //altrimento faccio un Toast in cui dico all'utente che deve loggarsi prima di giocare
        /*if (AccessToken.getCurrentAccessToken() == null)
            Toast.makeText(getApplicationContext(),"Effettuare il login..",Toast.LENGTH_SHORT).show();*/

        //}

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


        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.50.6.67:8000/getHeritages/";

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

        ImageView lente = (ImageView)findViewById(R.id.lens);
        lente.setImageAlpha(100);


        /*******fine configurazione dello spinner*******/


        /******inizio configurazione bottoni cards_list e missions_list******/
        Button card_list_button = (Button)findViewById(R.id.cards_list_button);
        card_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //questo funziona correttamente
                Toast toast = Toast.makeText(view.getContext(),"Hai cliccato su cards list button",Toast.LENGTH_SHORT);
                toast.show();
                /*definisco l'intenzione di aprire la pagina2(TreasurePortalPag2
                Intent openPage2 = new Intent(TreasurePortalPag1.this,TreasurePortalPag2.class);
                startActivity(openPage2);*/

            }});
        Button missions_list_button = (Button)findViewById(R.id.missions_list_button);
        missions_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAchievements = new Intent(TreasurePortalPag1.this,Achievements.class);
                //per inviare parametri all'activity Achievement
                openAchievements.putExtra("game","game1");
                startActivity(openAchievements);
                /*Toast toast = Toast.makeText(view.getContext(),"Hai cliccato su missions list button",Toast.LENGTH_SHORT);
                toast.show();*/
            }
        });
        /******fine configurazione bottoni cards_list e missions_list******/


    }

    /********************gestione click sugli elementi dello spinner*********************/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //prendo il valore dell'elemento selezionato
        String item = parent.getItemAtPosition(position).toString();

        if(item.equals("Colosseo")){
            Intent openTreasurePage2 = new Intent(TreasurePortalPag1.this,TreasurePortalPag2.class);
            startActivity(openTreasurePage2);
        }
        //visualizzo l'elemento selezionato
        if(!(item.equals(""))&& (!item.equals("Colosseo")))
            Toast.makeText(parent.getContext(),item,Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }
    /********************fine gestione click sugli elementi dello spinner*********************/

}
