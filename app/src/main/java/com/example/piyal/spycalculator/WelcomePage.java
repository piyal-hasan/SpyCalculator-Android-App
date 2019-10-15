package com.example.piyal.spycalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomePage extends AppCompatActivity {
     private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        SharedPreferences setting=getSharedPreferences("PREFS",0);
        password=setting.getString("password","");
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(password.equals("")){
                    startActivity(new Intent(getApplicationContext(),Createpasswordpage.class));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(),Calculator.class));
                    finish();
                }
            }
        },2000);
    }
}
