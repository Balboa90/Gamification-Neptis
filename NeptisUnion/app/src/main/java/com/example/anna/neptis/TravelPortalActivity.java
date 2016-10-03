package com.example.anna.neptis;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
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




        /***************gestione click su classification****************/
        ImageButton classification = (ImageButton)findViewById(R.id.Classification);
        classification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAchivement = new Intent(TravelPortalActivity.this, Achievements.class);
                openAchivement.putExtra("game","game2");
                startActivity(openAchivement);
                //Toast.makeText(view.getContext(),"Classifica in tempo reale",Toast.LENGTH_SHORT).show();
            }
        });
        /***************fine gestione click su classification****************/


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
    /**************fine gestione google map****************/
}
