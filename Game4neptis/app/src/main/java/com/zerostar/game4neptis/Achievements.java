package com.zerostar.game4neptis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by lorpe on 21/09/2016.
 */

public class Achievements extends AppCompatActivity {

    TextView title;
    //TEST
    //ListView list_achieve;
    //TEST
    TextView test_achiev;
    TextView test_achiev_desc;
    ImageView test_star_empty;
    //TEST

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        //setContentView(R.layout.activity_achievements);
        //TEST
        setContentView(R.layout.activity_achievements_test);
        //TEST

        title = (TextView) findViewById(R.id.l_achievements_title);
        //TEST
        //list_achieve = (ListView) findViewById(R.id.list_achievements);
        //TEST
        test_achiev = (TextView) findViewById(R.id.l_achievement_title);
        test_achiev_desc = (TextView) findViewById(R.id.l_achievement_descr);
        test_star_empty = (ImageView) findViewById(R.id.img_empty_star);

        test_achiev.setText("Aspirant Detective");
        test_achiev_desc.setText("Solve your first puzzle");




    }
}
