package com.example.endterm;

import java.sql.Timestamp;

public class Deadline {
    String timeEnd;
    Timestamp timestampEnd = null;
    String title;
    String course;
    String description;
    public Deadline(){}
    public Deadline(String title,String course, String time){
        this.title = title;
        this.course = course;
        convertTime(time);
    }
    public float getProgressBar(){
        String strNum = Long.toString(timestampEnd.getTime() - System.currentTimeMillis()).substring(0,2);
        if(Float.parseFloat(strNum) / 90 <= 1){return Float.parseFloat(strNum) / 90;}
        else{return 1;}
    }
    private void convertTime(String time){
        //####-##-## ##:##
        int year = Integer.parseInt(time.substring(0, 4));
        int mouth = Integer.parseInt(time.substring(5,7));
        int day = Integer.parseInt(time.substring(8,10));
        int hour = Integer.parseInt(time.substring(11,13));
        int minute = Integer.parseInt(time.substring(14,16));
        timestampEnd = new Timestamp(year - 1900,mouth - 1,day,hour,minute,0,0);
    }
    void covertTimeEnd(){
        int year = Integer.parseInt(timeEnd.substring(0, 4));
        int mouth = Integer.parseInt(timeEnd.substring(4,6));
        int day = Integer.parseInt(timeEnd.substring(6,8));
        int hour = Integer.parseInt(timeEnd.substring(9,11)) + 6;
        int minute = Integer.parseInt(timeEnd.substring(11,13));
        timestampEnd = new Timestamp(year - 1900,mouth - 1,day,hour,minute,0,0);
    }
    Boolean IsActual(){
        Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
        return timestampEnd.getTime() - timestampNow.getTime() > 0;
    }
}
