package com.example.narasimha.loginactivity1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.narasimha.loginactivity1.Model.Weather;

import java.util.ArrayList;

/**
 * Created by narasimha on 3/27/2018.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private String TAG = MyDBHelper.class.getSimpleName();
    public MyDBHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "OnCreate");
        //CREATE TABLE WEATHER ID int, city varchar(50), weather int, units varchar(10));
        String createWeatherTable = "CREATE TABLE Weather (\n" +
                "    ID int PRIMARY KEY,\n" +
                "    city varchar(50),\n" +
                "    weather int,\n" +
                "    units varchar(10)\n" +
                ");";
       // String createWeatherTable1 = "CREATE TABLE Weather (ID int, city varchar(50),weather int,units varchar(10));";
        Log.d(TAG, "Create Weather Table : " + createWeatherTable);
        db.execSQL(createWeatherTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insertWeatherData(String city, String weather, String units) {
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("INSERT INTO Weather (city, weather, units) VALUES ("+city+","+weather+","+units+")");
        ContentValues contentValues = new ContentValues();
        contentValues.put("city", city);
        contentValues.put("weather", Integer.parseInt(weather));
        contentValues.put("units", units);
        db.insert("Weather", null, contentValues);
    }
    public int getNoOfrows() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Weather", null);
        if (cursor != null) {
            return cursor.getCount();
        }
        return -1;
    }
    public ArrayList<Weather> getAllWeatherInfo(){
        ArrayList<Weather> weatherArrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Weather", null);
        while (cursor.moveToNext()){
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String temp = cursor.getString(cursor.getColumnIndex("weather"));
            String units = cursor.getString(cursor.getColumnIndex("units"));
            Weather weather= new Weather(city, temp, units);
            weatherArrayList.add(weather);
        }
        return weatherArrayList;


    }
}
