package com.example.piyal.spycalculator;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Boolean test=false;
    String check_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor mCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null,
                null, null);
        int name=mCursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
        int number = mCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int date = mCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = mCursor.getColumnIndex(CallLog.Calls.DURATION);
        int type = mCursor.getColumnIndex(CallLog.Calls.TYPE);
        //StringBuilder sb = new StringBuilder();
        while (mCursor.moveToNext()) {
            String phnumber = mCursor.getString(number);
            //String contactname=getContact(phnumber);
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
            SmsModel smsModel = new SmsModel(callTypeStr,"", calldate, callduration, phnumber);
            //addlist(smsModel);
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
        build();
    }

    private String getContact(String phoneNumber) {
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

    private void build() {
        RecyclerView r= (RecyclerView) findViewById(R.id.rec);
        List<SmsModel>list=getList();
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setHasFixedSize(true);
        MemberView v=new MemberView(this,list);
        r.setAdapter(v);
    }

    private void addlist(SmsModel smsModel) {
     CallLogStorage db=new CallLogStorage(this);
        db.addItem(smsModel);
    }
    public List<SmsModel> getList() {
        List<SmsModel>list=new CallLogStorage(this).getlist();
        return list;
    }
}
