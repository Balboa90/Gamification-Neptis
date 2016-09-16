package com.example.anna.neptis;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

public class TreasurePortalPag2 extends AppCompatActivity {

    private final static int CAMERA_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure_portal_pag2);

        //per consentire la navigazione back nelle activity(si può anche personalizzare con un'icona)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        /*___________________________gestione della gridView all'interno della scrollbar______________________*/
        final GridView gridView = (GridView) findViewById(R.id.grid_treasures);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));
        /*__________________________fine gestione gridView all'interno della scrollbar________________________*/

        /*___________________________On Click event for Single Gridview Item___________________________*/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                /********potrebbe essere utile per far apparire una nuova activity con gli elementi presenti in ogni forziere*************

                 // Sending image id to FullScreenActivity
                 Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                 // passing array index
                 i.putExtra("id", position);
                 startActivity(i);


                 ***********************con i dati che ha detto Alessandro***************/

                Toast toast = Toast.makeText(getApplicationContext(),"Selezionato forziere " + position ,Toast.LENGTH_SHORT);
                toast.show();

            }
        });
        /*___________________fine gestione click sui tesori all'interno della scrollbar____________________*/


        //per abilitare la scrollview
        ScrollView sView = (ScrollView)findViewById(R.id.ScrollView01);


        /*____________________To Hide the Scollbar__________________

        sView.setVerticalScrollBarEnabled(false);
        sView.setHorizontalScrollBarEnabled(false);
         ___________________________________________________________*/










        /*__________________gestione imageButton COLOSSEO e FOTOCAMERA____________________*/
        ImageButton colosseo = (ImageButton)findViewById(R.id.colosseo_image);
        colosseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(view.getContext(),"Colosseo ImageButton",Toast.LENGTH_SHORT);
                toast.show();


            }});

        ImageButton camera = (ImageButton)findViewById(R.id.camera_image);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCamera,CAMERA_REQUEST_CODE);
                /*Toast toast = Toast.makeText(view.getContext(),"Camera ImageButton",Toast.LENGTH_SHORT);
                toast.show();*/


            }});

         /*__________________fine gestione imageButton COLOSSEO e FOTOCAMERA____________________*/



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
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode== RESULT_OK){
            Toast.makeText(this,"camera ok!",Toast.LENGTH_LONG).show();
        }
    }
}
