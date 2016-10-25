package com.example.anna.neptis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class ManageCards extends AppCompatActivity {

    List<ObjCard> all_cards;
    String url;
    ListView list;
    String code,cost,name,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);

        list = (ListView) findViewById(R.id.listView);
        all_cards = new ArrayList<ObjCard>();

        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        url ="http://10.0.2.2:8000/getAllCards/";

        Log.d("url= ",url);

        // Request a string response from the provided URL.
        JsonArrayRequest jsCardCodes = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int contLength = response.length();
                    for(int i= 0;i< contLength;i++) {
                        JSONObject jsObj = (JSONObject)response.get(i);
                        code = jsObj.getString("code");
                        cost = jsObj.getString("cost");
                        name = jsObj.getString("name");
                        description = jsObj.getString("description");
                        //Log.d("Description: ", description);

                        all_cards.add(new ObjCard(code,cost,name,description));
                        list.setAdapter(new CardAdapter(ManageCards.this, android.R.layout.simple_list_item_1, all_cards));

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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Intent go_to_info = new Intent(ManageCards.this, CardInfoActivity.class);
                ObjCard select_card = (ObjCard)adapter.getItemAtPosition(position);
                String code_sel_card = select_card.getCode();
                Log.d("codice inviato: ",code_sel_card);
                go_to_info.putExtra("codice",code_sel_card);
                startActivity(go_to_info);

            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsCardCodes);

        /***********_______END TEMPLATE JSON REQUEST________**********/

    }




}





/*public class ManageCards extends AppCompatActivity {

    List<ObjCard> all_cards;
    String url;
    ListView list;
    String code,cost,name,description;
    ScrollView s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);

        list = (ListView) findViewById(R.id.listView);
        all_cards = new ArrayList<ObjCard>();
        s = (ScrollView)findViewById(R.id.ScrollView01);

        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        url ="http://10.0.2.2:8000/getAllCards/";

        Log.d("url= ",url);

        // Request a string response from the provided URL.
        JsonArrayRequest jsCardCodes = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int contLength = response.length();
                    for(int i= 0;i< contLength;i++) {
                        JSONObject jsObj = (JSONObject)response.get(i);
                        code = jsObj.getString("code");
                        cost = jsObj.getString("cost");
                        name = jsObj.getString("name");
                        description = jsObj.getString("description");
                        //Log.d("Description: ", description);

                        all_cards.add(new ObjCard(code,cost,name,description));
                        list.setAdapter(new CardAdapter(ManageCards.this, android.R.layout.simple_list_item_1, all_cards));

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

        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {


                ImageButton info = (ImageButton) v.findViewById(R.id.info_descr);
                info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent go_to_info = new Intent(ManageCards.this, CardInfoActivity.class);
                        startActivity(go_to_info);
                    }
                });

                //ObjCard select_card = (ObjCard)list.getItemAtPosition(position);
                //String code_sel_card = select_card.getCode();
                //Log.d("codice inviato: ",code_sel_card);
               // go_to_info.putExtra("codice",code_sel_card);


            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsCardCodes);

        /***********_______END TEMPLATE JSON REQUEST________*********

    }




}*/

