package com.example.piyal.spycalculator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by piyal on 2/6/2018.
 */
public class Database extends SQLiteOpenHelper {
    private static String DATABASE_NAME="call_database";
    private static String DATABASE_TABLE="calllogtable";
    private static String KEY="SMS_ID";
    private static String SMSTEXT="calltype";
    private static String SMS_TIME="calltime";
    private static String SMS_DATE="calldate";
    private static String PH_NUMBER="phonenumber";
    private static String CALL_DURATION="call_duration";
    private static int VERSION=1;
    public Database(Context context) {
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
    public void addItem(SmsModel smsModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SMSTEXT,smsModel.getCalltype());
        values.put(SMS_TIME,smsModel.getCalltime());
        values.put(SMS_DATE,smsModel.getCalldate());
        values.put(CALL_DURATION,smsModel.getCall_duration());
        values.put(PH_NUMBER,smsModel.getPhone_number());
        //db.insert(DATABASE_TABLE,null,values);
        db.insertWithOnConflict(DATABASE_TABLE, null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

    }
    public List<SmsModel> getlist(){
        SQLiteDatabase db=this.getWritableDatabase();
        List<SmsModel> list=new ArrayList<>();
        try{
        String sql="SELECT  * FROM "+DATABASE_TABLE;
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
        }}
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
