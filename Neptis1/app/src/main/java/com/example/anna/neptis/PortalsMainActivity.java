package com.example.anna.neptis;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class PortalsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portals_main);

        //verifico se la sessione è iniziata o meno
        //in questo caso verifico la sessione normale
        if(LoginActivity.flag_login_session == false) {
            //in questo caso verifico la sessione tramite facebook
            if (AccessToken.getCurrentAccessToken() == null)
                goLoginScreen();
        }



        /*__________________gestione imageButton dei 4 portali____________________*/

        /**
         *
         * MANAGEMENT YELLOW PORTAL
         *
         */
        ImageButton yellowPortalButton = (ImageButton)findViewById(R.id.yellow_portal);
        yellowPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openYellowPortal = new Intent(PortalsMainActivity.this,TreasurePortalPag1.class);
                startActivity(openYellowPortal);
            }
        });
        /**
         *
         * MANAGEMENT GREEN PORTAL
         *
         */
        ImageButton greenPortalButton = (ImageButton)findViewById(R.id.green_portal);
        greenPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(),"Green portal",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        /**
         *
         * MANAGEMENT RED PORTAL
         *
         */
        ImageButton redPortalButton = (ImageButton)findViewById(R.id.red_portal);
        redPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(),"Red portal",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        /**
         *
         * MANAGEMENT BLUE PORTAL
         *
         */
        ImageButton bluePortalButton = (ImageButton)findViewById(R.id.blue_portal);
        bluePortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(),"Blue portal",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        /*____________________fine gestione imageButton dei 4 portali_______________________*/

        /*__________________gestione imageButton login FACEBOOK____________________*/
        /**
         *
         * MANAGEMENT FACEBOOK LOGIN
         *
         */
        ImageButton facebookLogin = (ImageButton)findViewById(R.id.facebook_icon);
        facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(),"Facebook login",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        /*____________________fine gestione imageButton login FACEBOOK_______________________*/

        /*__________________gestione textView Login Register___________________*/
        TextView loginRegister = (TextView)findViewById(R.id.login);
        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLoginActivity = new Intent(PortalsMainActivity.this,LoginActivity.class);
                startActivity(openLoginActivity);
            }
        });
         /*__________________fine gestione textView Login Register___________________*/


    }

    private void goLoginScreen() {
        Intent openLoginPage = new Intent(this,LoginActivity.class);
        openLoginPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openLoginPage);
    }


    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
