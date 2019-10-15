package com.example.piyal.spycalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piyal on 3/9/2018.
 */
public class CallLogStorage extends SQLiteOpenHelper {
    private static String DATABASE_NAME="Call_log_storage_database";
    private static String DATABASE_TABLE="calllogtable";
    private static String KEY="SMS_ID";
    private static String SMSTEXT="calltype";
    private static String SMS_TIME="calltime";
    private static String SMS_DATE="calldate";
    private static String PH_NUMBER="phonenumber";
    private static String CALL_DURATION="call_duration";
    private static int VERSION=1;
    public CallLogStorage(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }
    private static String createtable="CREATE TABLE " +
            ""+DATABASE_TABLE+" ("
            +KEY+" INTEGER PRIMARY KEY," +
            ""+SMSTEXT+" TEXT," +
            ""+SMS_TIME+" TEXT," +
            ""+SMS_DATE+" TEXT," +
            ""+CALL_DURATION+" TEXT," +
            ""+ PH_NUMBER+" TEXT" +")";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(createtable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + createtable);
    }
    public long addItem(SmsModel smsModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SMSTEXT,smsModel.getCalltype());
        values.put(SMS_TIME,smsModel.getCalltime());
        values.put(SMS_DATE,smsModel.getCalldate());
        values.put(CALL_DURATION,smsModel.getCall_duration());
        values.put(PH_NUMBER,smsModel.getPhone_number());
        return db.insert(DATABASE_TABLE,null,values);
    }
    public List<SmsModel> getlist(){
        List<SmsModel> list=new ArrayList<>();
        String sql="SELECT  * FROM "+DATABASE_TABLE;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                SmsModel sms=new SmsModel();
                sms.setKey(cursor.getInt(0));
                sms.setCalltype(cursor.getString(1));
                sms.setCalltime(cursor.getString(2));
                sms.setCalldate(cursor.getString(3));
                sms.setCall_duration(cursor.getString(4));
                sms.setPhone_number(cursor.getString(5));
                list.add(sms);
            }while(cursor.moveToNext());
        }
        return list;
    }
}
