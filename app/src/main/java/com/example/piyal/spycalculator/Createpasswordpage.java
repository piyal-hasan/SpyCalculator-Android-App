package com.example.piyal.spycalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Createpasswordpage extends AppCompatActivity {

    EditText pass,confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpasswordpage);
        pass=(EditText)findViewById(R.id.passtx);
        confirm=(EditText)findViewById(R.id.conftx);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t1=pass.getText().toString();
                String t2=confirm.getText().toString();
                if(t1.equals("")||t2.equals("")){
                    Toast.makeText(getBaseContext(),"No password entered!",Toast.LENGTH_SHORT).show();

                }
                else {
                    if(t1.equals(t2)){
                        SharedPreferences setting=getSharedPreferences("PREFS",0);
                        SharedPreferences.Editor editor=setting.edit();
                        editor.putString("password",t1);
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(getBaseContext(),"enterder the password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
