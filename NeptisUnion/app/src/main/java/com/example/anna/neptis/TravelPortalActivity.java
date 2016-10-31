package com.example.anna.neptis;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class TravelPortalActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int contLength;
    String totale;
    String visitati;
    TextView contatore2;
    TextView contatore1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_portal);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        /***************gestione click su medals icon****************/
        ImageButton medals_button = (ImageButton)findViewById(R.id.Medals);
        medals_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMedalsActivity = new Intent(TravelPortalActivity.this,MedalsActivity.class);
                startActivity(openMedalsActivity);
            }
        });
        /***************fine gestione click su medals icon****************/

        /***************gestione click su cont****************/
        contatore1 = (TextView)findViewById(R.id.Cont1);
        TextView slash = (TextView)findViewById(R.id.slash);
        contatore2 = (TextView)findViewById(R.id.Cont2);

        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8000/getHeritagesCount/";

        // Request a string response from the provided URL.
        JsonArrayRequest jsTotal = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try{
                    contLength = response.length();
                    for(int i = 0;i< contLength;i++){
                        JSONObject jsObj = (JSONObject)response.get(i);
                        totale = jsObj.getString("conto");
                        contatore2.setText(totale);
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
        queue.add(jsTotal);
        //***********_______END TEMPLATE JSON REQUEST________**********

        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        RequestQueue queue2 = Volley.newRequestQueue(this);
        String url2 ="http://10.0.2.2:8000/getVisitedHeritagesCount/:game20005";

        // Request a string response from the provided URL.
        JsonArrayRequest jsVisited = new JsonArrayRequest(Request.Method.GET, url2,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try{
                    contLength = response.length();
                    for(int i = 0;i< contLength;i++){
                        JSONObject jsObj = (JSONObject)response.get(i);
                        visitati = jsObj.getString("visitConto");
                        Log.d("Risposta: ",visitati);
                        contatore1.setText(visitati);
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
        queue.add(jsVisited);
        //***********_______END TEMPLATE JSON REQUEST________**********


        /*Button contatore = (Button) findViewById(R.id.Cont);
        contatore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Contatore",Toast.LENGTH_SHORT).show();
            }
        });*/
        /***************fine gestione click su cont****************/




        /***************gestione click su achievement****************/
        ImageButton achievement = (ImageButton)findViewById(R.id.achievement);
        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAchivement = new Intent(TravelPortalActivity.this, Achievements.class);
                openAchivement.putExtra("game","game2");
                startActivity(openAchivement);
            }
        });
        /***************fine gestione click su classification****************/


    }

    /**************gestione google map****************/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng firenze = new LatLng(43.776366, 11.247822);
        mMap.addMarker(new MarkerOptions()
                .position(firenze)
                .title("This is my title")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(firenze).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        // create marker
        /*MarkerOptions marker = new MarkerOptions().position(firenze).title("Hello Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marke_ok));

        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(firenze).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

    }
    /**************fine gestione google map****************/
}
