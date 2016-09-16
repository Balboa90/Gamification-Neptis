package com.example.anna.neptis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Field;


public class TreasurePortalPag1 extends AppCompatActivity implements OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure_portal_pag1);


        /*******inizio configurazione dello spinner*******/

        Spinner dropdown = (Spinner)findViewById(R.id.spinner_menu);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(dropdown);

            // Set popupWindow height to 500px
            popupWindow.setHeight(320);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            Toast.makeText(getApplicationContext(),"Errore!",Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<?>adapter = ArrayAdapter.createFromResource(this,R.array.spinner_options,android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //applico l'adapter allo spinner
        dropdown.setAdapter(adapter);

        ImageView lente = (ImageView)findViewById(R.id.lens);
        lente.setImageAlpha(100);

        dropdown.setOnItemSelectedListener(this);

        /*******fine configurazione dello spinner*******/


        /******inizio configurazione bottoni cards_list e missions_list******/
        Button card_list_button = (Button)findViewById(R.id.cards_list_button);
        card_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //questo funziona correttamente
                Toast toast = Toast.makeText(view.getContext(),"Hai cliccato su cards list button",Toast.LENGTH_SHORT);
                toast.show();
                /*definisco l'intenzione di aprire la pagina2(TreasurePortalPag2
                Intent openPage2 = new Intent(TreasurePortalPag1.this,TreasurePortalPag2.class);
                startActivity(openPage2);*/

            }});
        Button missions_list_button = (Button)findViewById(R.id.missions_list_button);
        missions_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(),"Hai cliccato su missions list button",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        /******fine configurazione bottoni cards_list e missions_list******/


    }

    /********************gestione click sugli elementi dello spinner*********************/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //prendo il valore dell'elemento selezionato
        String item = parent.getItemAtPosition(position).toString();

        if(item.equals("Colosseo")){
            Intent openTreasurePage2 = new Intent(TreasurePortalPag1.this,TreasurePortalPag2.class);
            startActivity(openTreasurePage2);
        }
        //visualizzo l'elemento selezionato
        if(!(item.equals(""))&& (!item.equals("Colosseo")))
            Toast.makeText(parent.getContext(),item,Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }
    /********************fine gestione click sugli elementi dello spinner*********************/

}
