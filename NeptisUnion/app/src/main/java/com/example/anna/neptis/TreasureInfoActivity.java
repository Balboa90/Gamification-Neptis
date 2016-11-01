package com.example.anna.neptis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anna on 29/10/2016.
 */

public class TreasureInfoActivity extends AppCompatActivity {

    String treasure_code;//= getIntent().getExtras().getString("heritage")
    List treasure_list; //lista de tesori presenti nell'heritage passato come parametro
    List treas_card_list;//lista delle carte appartenenti al tesoro passato come parametro
    String url;
    String url2;
    String url3;
    String t_lat, t_lon, t_info;//attributi di Tesoro
    boolean found;//attributo tesoro
    TextView info;
    TextView latitude;
    TextView longitude;
    String c_name, c_cost, c_description;//attributi della carte
    ListView carte_tesori;


    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_in_treasure);

        user = getIntent().getExtras().getString("user");

        info = (TextView) findViewById(R.id.t_info);
        latitude = (TextView) findViewById(R.id.t_lat_val);
        longitude = (TextView) findViewById(R.id.t_lon_val);

        //heritage_tres =(TextView)findViewById(R.id.heritage_treas);

        carte_tesori = (ListView) findViewById(R.id.carte_forziere);

        treasure_list = new LinkedList<ObjTesoro>();
        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        treasure_code = getIntent().getExtras().getString("codice_tesoro").trim();

        RequestQueue queue = Volley.newRequestQueue(this);
        url = "http://10.0.2.2:8000/getInfoTreasure/" + treasure_code + "/";

        Log.d("codice tesoro: ", treasure_code);

        Log.d("url= ", url);

        // Request a string response from the provided URL.
        JsonArrayRequest jsInfoTreasure = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int contLength = response.length();
                    for (int i = 0; i < contLength; i++) {
                        JSONObject jsObj = (JSONObject) response.get(i);
                        t_info = jsObj.getString("info");
                        t_lat = jsObj.getString("latitude");
                        t_lon = jsObj.getString("longitude");

                        info.setText(t_info);
                        latitude.setText(t_lat);
                        longitude.setText(t_lon);

                        treasure_list.add(new ObjTesoro(treasure_code, t_lat, t_lon, t_info, user));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", error.toString());
            }
        });
        queue.add(jsInfoTreasure);

        /***********_______END TEMPLATE JSON REQUEST________**********/


        treas_card_list = new LinkedList<ObjCard>();
        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        RequestQueue queue2 = Volley.newRequestQueue(this);
        url2 = "http://10.0.2.2:8000/getTreasureCardInfo/" + user + "/" + treasure_code + "/";

        Log.d("url= ", url2);

        // Request a string response from the provided URL.
        JsonArrayRequest jsInfoCardTreasure = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int contLength = response.length();
                    for (int i = 0; i < contLength; i++) {
                        JSONObject jsObj = (JSONObject) response.get(i);
                        c_name = jsObj.getString("name");
                        c_cost = jsObj.getString("cost");
                        c_description = jsObj.getString("description");

                        treas_card_list.add(new ObjCard(c_cost, c_name, c_description));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", error.toString());
            }
        });

        CardAdapter adapter = new CardAdapter(TreasureInfoActivity.this, R.layout.adapter_card, treas_card_list);
        carte_tesori.setAdapter(adapter);
        // Add the request to the RequestQueue.
        queue2.add(jsInfoCardTreasure);

        /***********_______END TEMPLATE JSON REQUEST________**********/

    }



}
