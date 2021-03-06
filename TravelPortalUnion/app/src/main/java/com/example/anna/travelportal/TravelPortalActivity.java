package com.example.anna.travelportal;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TravelPortalActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_portal);

        //gestione fragment relativo alla google map
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
        Button contatore = (Button) findViewById(R.id.Cont);
        contatore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Contatore",Toast.LENGTH_SHORT).show();
            }
        });
        /***************fine gestione click su cont****************/




        /***************gestione click su achievement****************/
        ImageButton achievement = (ImageButton)findViewById(R.id.Achievement);
        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Achievement Game2",Toast.LENGTH_SHORT).show();
            }
        });
        /***************fine gestione click su achievement****************/


    }


    /**************gestione google map****************/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng firenze = new LatLng(43.776366, 11.247822);
        mMap.addMarker(new MarkerOptions().position(firenze).title("Siamo a Firenze!"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(firenze).zoom(15).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}