package com.example.anna.neptis;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        if (LoginActivity.flag_welcome == false) {
        /*
        *
        * finestra di dialogo per il benvenuto
        *
        */

            final Dialog d = new Dialog(this);
            d.setTitle("Login");
            d.setCancelable(false);
            d.setContentView(R.layout.activity_dialog);

            Button login = (Button) d.findViewById(R.id.go_to_login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    LoginActivity.flag_welcome = true;
                    Intent openLoginPage = new Intent(DialogActivity.this, LoginActivity.class);
                    startActivity(openLoginPage);
                }
            });
            d.show();
            /**
             *
             * finestra di dialogo per il benvenuto
             *
             **/
        }
    }


}
