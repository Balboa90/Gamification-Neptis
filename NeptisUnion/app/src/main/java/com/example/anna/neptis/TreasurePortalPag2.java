package com.example.anna.neptis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.provider.MediaStore;
import android.renderscript.Double2;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreasurePortalPag2 extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private final static int CAMERA_REQUEST_CODE = 1;
    private static GoogleMap mMap;

    String heritage;//= getIntent().getExtras().getString("heritage")
    String url,url2,url3;
    String latitudine;
    String longitudine;
    String code,lat,lon,info;//attributi di ObjTesoro
    int found;//per i tesori
    List list; //lista dei tesori presenti nell'heritage passato come parametro

    String user;


    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    ListView tesori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure_portal_pag2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    LatLng herit;
    @Override
    public void onMapReady(GoogleMap googleMap) {

        /***********_______START TEMPLATE JSON REQUEST________**********/
        mMap = googleMap;
        RequestQueue queue = Volley.newRequestQueue(TreasurePortalPag2.this);
        String spaces = heritage.replace(" ","%20");
        url ="http://10.0.2.2:8000/getCoordinatesHeritage/"+spaces+"/";

        Log.d("url= ",url);

        JsonArrayRequest jsCoordinates = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        int contLength = response.length();
                        for(int i = 0;i< contLength;i++) {
                            JSONObject jsObj = (JSONObject) response.get(i);
                            latitudine = jsObj.getString("latitude");
                            longitudine = jsObj.getString("longitude");
                            //Log.d("VERIFICA LATITUDINE ",latitudine);
                            //Log.d("VERIFICA LONGITUDINE ",longitudine);

                            herit = new LatLng(Double.parseDouble(latitudine),Double.parseDouble(longitudine));
                            //mMap.addMarker(new MarkerOptions().position(herit).title(heritage).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))); //aggiungere

                            mMap.addMarker(new MarkerOptions().position(herit).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))); //aggiungere
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(herit).zoom(15).build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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
        queue.add(jsCoordinates);

        /***********_______END TEMPLATE JSON REQUEST________**********/
    }

    private static final int RANGE_METERS = 3 * 100;//raggio di 2km
    String urlUpdate;
    int trovato;
    LatLng pos;
    Intent openTreasure;
    final static int CODE = 1;

    //implementazione click sui marker relativi ai tesori (da escludere il marker dell'heritage-renderlo non cliccabile)
    @Override
    public boolean onMarkerClick(final Marker marker) {
        pos = marker.getPosition();
        final String code_treas = marker.getTitle();

        if(code_treas == null){

        }else {
            Log.d("MARKER CLICCATO: ", code_treas);


            //NOTA:la distanza bisogna calcolarla tra posizione utente e marker cliccato non tra herit e marker cliccato
            //modificare dopo aver implementato la ricerca della posizione utente
            double d = SphericalUtil.computeDistanceBetween(pos, herit);
            // Log.d("DISTANZA MARKER: ", Double.toString(d));
            boolean inRange = SphericalUtil.computeDistanceBetween(pos, herit) < RANGE_METERS;

            //se il marker rientra nel range e il forziere non è stato ancora trovato, apre il forziere e mostra le carte
            if (inRange) {
                openTreasure = new Intent(TreasurePortalPag2.this, TreasureInfoActivity.class);

                /***********_______START TEMPLATE JSON REQUEST________**********/
                RequestQueue queue = Volley.newRequestQueue(TreasurePortalPag2.this);
                //Log.d("CODICE MARKER:",code_treas);
                urlUpdate = "http://10.0.2.2:8000/upadateFoundTreas/" + code_treas + "/" + user + "/";

                Log.d("url= ", urlUpdate);
                JsonObjectRequest jsFoundTreas = new JsonObjectRequest(Request.Method.GET, urlUpdate, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        Toast.makeText(TreasurePortalPag2.this, "Trovato nuovo tesoro!", Toast.LENGTH_SHORT).show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley error:", error.toString());
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(jsFoundTreas);

                //l'activity si apre dopo un certo tempo
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        //start your activity here
                        openTreasure.putExtra("user", user);
                        openTreasure.putExtra("codice_tesoro", code_treas);
                        openTreasure.putExtra("trovato", "1");
                        openTreasure.putExtra("heritage",heritage);
                        startActivity(openTreasure);
                    }

                }, 1500L);
                /***********_______END TEMPLATE JSON REQUEST________**********/

            } else {
                Toast.makeText(TreasurePortalPag2.this, "Sei troppo lontano dal tesoro. Avvicinati!", Toast.LENGTH_LONG).show();
            }
        }
        return true;

    }

    @Override
    protected void onResume(){
        super.onResume();

        ///Log.d("ON RESUME: ","ok");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        user = getIntent().getExtras().getString("user");

        /*___________________________gestione TESORI all'interno della scrollbar______________________*/
        tesori = (ListView) findViewById(R.id.list_treasures);
        tesori.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);


        list = new LinkedList<ObjTesoro>();

        //***********_______TEMPLATE JSON REQUEST________**********
        // Instantiate the RequestQueue.
        heritage = getIntent().getExtras().getString("heritage").trim();
        RequestQueue queue = Volley.newRequestQueue(this);
        String spaces = heritage.replace(" ","%20");
        url2 ="http://10.0.2.2:8000/getTreasureElements/" + user + "/" + spaces + "/";

        Log.d("url= ",url2);

        // Request a string response from the provided URL.
        JsonArrayRequest jsTreasureElements = new JsonArrayRequest(Request.Method.GET, url2,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int contLength = response.length();
                    for(int i = 0;i< contLength;i++) {
                        //int j = 1;
                        JSONObject jsObj = (JSONObject) response.get(i);
                        code = jsObj.getString("code");
                        lat = jsObj.getString("latitude");
                        lon = jsObj.getString("longitude");
                        info = jsObj.getString("info");
                        found = jsObj.getInt("found");
                        Log.d("CODICE TESORO INIZIO: ",code);
                        Log.d("TROVATO: ",Integer.toString(found));


                        latlngs.add(new LatLng(Double.parseDouble(lat),Double.parseDouble(lon))); //some latitude and logitude value

                        LatLng point = latlngs.get(i);


                        options.position(point);


                        if (found == 1) {
                            Log.d("entrato nell'if: ","ok");
                            //options.title(code);
                            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                            mMap.addMarker(options);
                        } else {
                            Log.d("entrato nell'else: ","ok");
                            options.title(code);
                            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                            mMap.addMarker(options);
                            mMap.setOnMarkerClickListener(TreasurePortalPag2.this);//click su marker
                        }



                        list.add(new ObjTesoro(code,lat,lon,info,user,found));

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


        TreasureAdapter adapter = new TreasureAdapter(this, R.layout.adapter_treasure, list);
        tesori.setAdapter(adapter);
        // Add the request to the RequestQueue.
        queue.add(jsTreasureElements);

        /***********_______END TEMPLATE JSON REQUEST________**********/


            /*__________________________fine gestione gridView all'interno della scrollbar________________________*/


        //gestione click su fotocamera
        ImageButton camera = (ImageButton)findViewById(R.id.camera_image);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCamera,CAMERA_REQUEST_CODE);
                    /*Toast toast = Toast.makeText(view.getContext(),"Camera ImageButton",Toast.LENGTH_SHORT);
                    toast.show();*/


            }});

        /*__________________gestione bottone SITE INFORMATION____________________*/
        Button siteInformation = (Button)findViewById(R.id.site_information);
        siteInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(view.getContext(),"Site Information Button",Toast.LENGTH_SHORT);
                toast.show();


            }});
        /*__________________fine gestione bottone SITE INFORMATION____________________*/




    }

    @Override
    public void onBackPressed() {
        Intent openParentActivity = getParentActivityIntent();
        openParentActivity.putExtra("user",user);
        startActivity(openParentActivity);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {

        if (requestCode == CAMERA_REQUEST_CODE && resultCode== RESULT_OK){
            Toast.makeText(this,"camera ok!",Toast.LENGTH_LONG).show();
        }


    }
}
