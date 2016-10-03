package com.example.anna.neptis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {



    EditText reg_username;
    EditText reg_password;
    EditText confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reg_username = (EditText) findViewById(R.id.regUsername);
        reg_password = (EditText) findViewById(R.id.regPassword);
        confirm_password = (EditText) findViewById(R.id.regConfirmPass);

        //gestione click su bottone Register
        Button bRegister = (Button)findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast success = Toast.makeText(getApplicationContext(),"Registrazione avvenuta con successo!",Toast.LENGTH_SHORT);
                success.show();*/

                Intent returnPageLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(returnPageLogin);
            }
        });

    }


    /*
    public static String getUser(){
        if(reg_username == null)return null;
        return reg_username.getText().toString();
    }


    public static String getPassword(){
        if(reg_password == null) return null;
        return reg_password.getText().toString();
    }*/

}
