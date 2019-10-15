package com.example.piyal.spycalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {
    TextView text;
    TextView text1;
    String password;
    Boolean issum=false;
    Boolean issub=false;
    Boolean ismul=false;
    Boolean isdiv=false;
    boolean e=true;
    double value_1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        text= (TextView) findViewById(R.id.text_id1);
        text1= (TextView) findViewById(R.id.text_id2);
    }
    public void Buttonclick(View view) {
        int id=view.getId();
        SharedPreferences setting=getSharedPreferences("PREFS",0);
        password=setting.getString("password","");

        switch (id){

            case R.id.btplus:
            {
                issum=true;
                e=false;
                break;
            }
            case R.id.btminus:
            {

                issub=true;
                e=false;
                break;
            }
            case R.id.btdmul:
            {
                e=false;
                ismul = true;
                break;
            }
            case R.id.btdiv:
            {
                e=false;
                isdiv = true;
                break;
            }
            case R.id.bt1:
            {
                if(e){
                text.setText(text.getText()+"1");
                }
                else {
                    text1.setText(text1.getText()+"1");
                }
                break;
            }
            case R.id.bt2:
            {
                if(e){
                    text.setText(text.getText()+"2");
                }
                else {
                    text1.setText(text1.getText()+"2");
                }
                break;
            }
            case R.id.bt3:
            {
                if(e){
                    text.setText(text.getText()+"3");
                }
                else {
                    text1.setText(text1.getText()+"3");
                }
                break;
            }
            case R.id.bt4:
            {
                if(e){
                    text.setText(text.getText()+"4");
                }
                else {
                    text1.setText(text1.getText()+"4");
                }
                break;
            }
            case R.id.bt5:
            {
                if(e){
                    text.setText(text.getText()+"5");
                }
                else {
                    text1.setText(text1.getText()+"5");
                }
                break;
            }
            case R.id.bt6:
            {
                if(e){
                    text.setText(text.getText()+"6");
                }
                else {
                    text1.setText(text1.getText()+"7");
                }
                break;
            }
            case R.id.bt7:
            {
                if(e){
                    text.setText(text.getText()+"7");
                }
                else {
                    text1.setText(text1.getText()+"7");
                }
                break;
            }
            case R.id.bt8:
            {
                if(e){
                    text.setText(text.getText()+"8");
                }
                else {
                    text1.setText(text1.getText()+"8");
                }
                break;
            }
            case R.id.bt9:
            {
                if(e){
                    text.setText(text.getText()+"9");
                }
                else {
                    text1.setText(text1.getText()+"9");
                }
                break;
            }
            case R.id.dotbt:
            {
                if(e){
                    text.setText(text.getText()+".");
                }
                else {
                    text1.setText(text1.getText()+".");
                }
                break;
            }
            case R.id.zerobt:{
                if(e){
                    text.setText(text.getText()+"0");
                }
                else {
                    text1.setText(text1.getText()+"0");
                }
                break;
            }
            case R.id.btclr:
            {
                text.setText("");
                text1.setText("");
                e=true;
                isdiv=false;
                issub=false;
                ismul=false;
                issum=false;
                break;
            }
            case R.id.delbt:{
                if(e){
                String tt=text.getText().toString();
                tt=tt.substring(0,tt.length()-1);
                text.setText(tt);}
                else{
                    String tt=text1.getText().toString();
                    tt=tt.substring(0,tt.length()-1);
                    text1.setText(tt);
                }
                break;
            }
            case R.id.btequal:
            {
                e=true;
                if(text.getText().toString().equals(password)){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

                }
                else {
                    if(text.getText().toString().equals("")){
                        text.setText("0.0");
                    }
                    if(text1.getText().toString().equals("")){
                        text1.setText("0.0");
                    }
                        double n=Double.parseDouble(text.getText().toString());
                        double n1=Double.parseDouble(text1.getText().toString());
                    if(issum){
                            double sum=n+n1;
                            text1.setText(String.valueOf(sum));
                            text.setText("ANSWER");

                        }
                        if(issub){
                            double sum=n-n1;
                            text1.setText(String.valueOf(sum));
                            text.setText("ANSWER");
                        }
                        if(ismul){
                            double sum=n*n1;
                            text1.setText(String.valueOf(sum));
                            text.setText("ANSWER");

                        }
                        if(isdiv){
                            double sum=n*n1;
                            text1.setText(String.valueOf(sum));
                            text.setText("ANSWER");
                        }

                }
                break;
            }
        }
    }
}
