package com.example.anna.neptis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    NetworkHelper networkHelper = new NetworkHelper();

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


                if (!reg_password.getText().toString().equals(confirm_password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "The passwords do not match", Toast.LENGTH_LONG).show();
                } else {
                   // String json = "{\"name\": \"" + reg_username.getText() + "\", \"password\":\"" + reg_password.getText() + "\"}";
                    String json =  "{\"user\":\"" + reg_username.getText() + "\", \"password\":\"" + reg_password.getText() + "\"}";
                    networkHelper.post("http://10.0.2.2:8000/post", json, new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Log.d("Registrazione fallita!", e.toString());
                        }
                        @Override
                        public void onResponse(Response response) throws IOException {
                            String responseStr = response.body().string();
                            final String messageText = "Status code : " + response.code() + "\n" + "Response body : " + responseStr;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("Message text", messageText);
                                    Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                }





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
