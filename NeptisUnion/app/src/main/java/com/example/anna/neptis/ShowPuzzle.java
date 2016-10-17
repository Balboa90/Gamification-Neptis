package com.example.anna.neptis;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class ShowPuzzle extends AppCompatActivity {

    String nome;
    String url;
    TextView nome_titolo;
    TextView descrizione;
    ImageButton solve;
    ImageButton hint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_puzzle);

        nome = getIntent().getExtras().getString("name");

        nome_titolo = (TextView) findViewById(R.id.l_puzzle_name);
        nome_titolo.setText(nome);

        descrizione = (TextView) findViewById(R.id.l_test_puzzle_descr);

        ///

        solve = (ImageButton) findViewById(R.id.ib_solve);
        hint = (ImageButton) findViewById(R.id.ib_hint);

        /***********_______START TEMPLATE JSON REQUEST________**********/

        RequestQueue queue = Volley.newRequestQueue(ShowPuzzle.this);
        url ="http://10.0.2.2:8000/getPuzzleDescription/"+nome;
        // Request a string response from the provided URL.
        Log.d("url= ",url);

        JsonArrayRequest jsArray = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject obj_desc = response.getJSONObject(0);
                    descrizione.setText(obj_desc.getString("description"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley error:", error.toString());
                Toast.makeText(ShowPuzzle.this,"No Description...",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsArray);
        /***********_______END TEMPLATE JSON REQUEST________**********/
    }
}

