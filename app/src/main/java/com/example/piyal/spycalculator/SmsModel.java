package com.example.piyal.spycalculator;

/**
 * Created by piyal on 2/6/2018.
 */
public class SmsModel {
    private int key;
    private String calltype;
    private String calltime;
    private String calldate;
    private String call_duration;
    private String phone_number;

    public SmsModel() {
    }

    public SmsModel(String calltype, String calltime, String calldate, String call_duration, String phone_number) {
        this.calltype = calltype;
        this.calltime = calltime;
        this.calldate = calldate;
        this.call_duration = call_duration;
        this.phone_number = phone_number;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    public String getCalldate() {
        return calldate;
    }

    public void setCalldate(String calldate) {
        this.calldate = calldate;
    }

    public String getCall_duration() {
        return call_duration;
    }

    public void setCall_duration(String call_duration) {
        this.call_duration = call_duration;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
