package com.example.anna.neptis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.games.achievement.Achievement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Achievements extends AppCompatActivity {

    TextView title;

    ListView list_achieve;

    String id = "";
    ObjAchievement[] list_active;
    String url;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        id = getIntent().getExtras().getString("game");

        setContentView(R.layout.activity_achievements);
        //TEST
        //setContentView(R.layout.activity_achievements_test);

        title = (TextView) findViewById(R.id.l_achievements_title);
        list_achieve = (ListView) findViewById(R.id.list_achievements);

        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        switch(id){
            case "game1":
                url ="http://10.0.2.2/getAchievementGame1/"; break;

            case "game2":
                url ="http://10.0.2.2:8000/getAchievementGame2/"; break;

            case "game3":
                url ="http://10.0.2.2:8000/getAchievementGame3/"; break;

            case "game4":
                url ="http://10.0.2.2:8000/getAchievementGame4/"; break; //10.0.2.2

            default: break;
        }

        // Request a string response from the provided URL.

        JsonArrayRequest jsArray = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Display the first 500 characters of the response string.
                //Log.d("Response is: ", response.toString());
                try{
                    int contLength = response.length();
                    list_active = new ObjAchievement[contLength];
                    for(int i = 0;i< contLength;i++){
                        JSONObject jsObj = (JSONObject)response.get(i);

                        String value = jsObj.getString("achievement");
                        ObjAchievement a_value = new ObjAchievement(value,"ciao_test");

                        list_active[i] = a_value;

                        ArrayAdapterAchievement adapter = new ArrayAdapterAchievement(Achievements.this,android.R.layout.simple_list_item_1,list_active);
                        list_achieve.setAdapter(adapter);
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


    }
}

