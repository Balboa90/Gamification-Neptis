package com.example.anna.neptis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PuzzlePortal extends AppCompatActivity {

    ListView list_attivi;
    ListView list_incoming;
    ImageButton ib_achievements;
    ImageButton ib_partecipa;
    ImageButton ib_cerca;
    String[] list_item;
    String[] list_item2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_portal);

        list_attivi = (ListView) findViewById(R.id.list_active);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8000/getEnabledPuzzle/";
        // Request a string response from the provided URL.

        JsonArrayRequest jsArray = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Display the first 500 characters of the response string.
                //Log.d("Response is: ", response.toString());
                try{
                    int contLength = response.length();
                    list_item = new String[contLength];
                    for(int i = 0;i< contLength;i++){
                        JSONObject jsObj = (JSONObject)response.get(i);
                        String value = jsObj.getString("code");
                        list_item[i] = value;

                        ArrayAdapter<?> adapter = new ArrayAdapter<Object>(PuzzlePortal.this,android.R.layout.simple_selectable_list_item,list_item);
                        list_attivi.setAdapter(adapter);
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

        list_incoming = (ListView) findViewById(R.id.list_incoming);

        RequestQueue queue2 = Volley.newRequestQueue(this);
        String url2 ="http://10.0.2.2:8000/getSoonPuzzle/";
        // Request a string response from the provided URL.

        JsonArrayRequest jsArray2 = new JsonArrayRequest(Request.Method.GET, url2,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Display the first 500 characters of the response string.
                //Log.d("Response is: ", response.toString());
                try{
                    int contLength = response.length();
                    list_item2 = new String[contLength];
                    for(int i = 0;i< contLength;i++){
                        JSONObject jsObj = (JSONObject)response.get(i);
                        String value = jsObj.getString("code") + "  - coming soon!";
                        list_item2[i] = value;

                        ArrayAdapter<?> adapter = new ArrayAdapter<Object>(PuzzlePortal.this,android.R.layout.simple_selectable_list_item,list_item2);
                        list_incoming.setAdapter(adapter);
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
        queue2.add(jsArray2);
        //***********_______END TEMPLATE JSON REQUEST________**********

        list_attivi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goto_show_puzzle = new Intent(PuzzlePortal.this, ShowPuzzle.class);
                Object selected_puzzle = list_attivi.getItemAtPosition(position);
                String extra_nome = selected_puzzle.toString();
                //Log.d("Nome del puzzle: ",extra_nome);
                goto_show_puzzle.putExtra("name", extra_nome);
                startActivity(goto_show_puzzle);
            }
        });

        list_incoming.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),"This puzzle will be available soon!",Toast.LENGTH_SHORT).show();;
            }
        });


        ib_achievements = (ImageButton) findViewById(R.id.ib_obiettivi) ;
        ib_achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_a = new Intent(PuzzlePortal.this, Achievements.class);
                goto_a.putExtra("game","game3");
                startActivity(goto_a);
            }
        });

        ib_partecipa = (ImageButton) findViewById(R.id.ib_partecipazioni) ;
        ib_partecipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ib_cerca = (ImageButton) findViewById(R.id.ib_cerca_enigmi) ;
        ib_cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}