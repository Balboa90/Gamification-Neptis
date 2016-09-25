package com.zerostar.game4neptis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PuzzlePortal extends AppCompatActivity {

    ListView list_attivi;
    ListView list_incoming;
    ImageButton ib_achievements;
    ImageButton ib_partecipa;
    ImageButton ib_cerca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        list_attivi = (ListView) findViewById(R.id.list_active);




        list_incoming = (ListView) findViewById(R.id.list_incoming);


        ib_achievements = (ImageButton) findViewById(R.id.ib_obiettivi) ;
        ib_achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_a = new Intent(PuzzlePortal.this, Achievements.class);
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