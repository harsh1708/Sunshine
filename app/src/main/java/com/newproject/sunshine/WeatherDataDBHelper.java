package com.newproject.sunshine;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by vikram on 26/11/16.
 */

public class WeatherDataDBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "sunshine.db";
    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + WeatherDataContract.WeatherEntry.TABLE_NAME
                    + " (" + WeatherDataContract.WeatherEntry.COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + WeatherDataContract.WeatherEntry.COLUMN_DATE + "LONG, "
                    + WeatherDataContract.WeatherEntry.COLUMN_WEATHER_ID + " INTEGER, "
                    + WeatherDataContract.WeatherEntry.COLUMN_WEATHER_CONDITION + " TEXT, "
                    + WeatherDataContract.WeatherEntry.COLUMN_MAX_TEMP + " DOUBLE, "
                    + WeatherDataContract.WeatherEntry.COLUMN_MIN_TEMP + " DOUBLE, "
                    + WeatherDataContract.WeatherEntry.COLUMN_PRESSURE + " DOUBLE, "
                    + WeatherDataContract.WeatherEntry.COLUMN_HUMIDITY + " DOUBLE, "
                    + WeatherDataContract.WeatherEntry.COLUMN_WEATHERCITY + " TEXT, "
                    + ")";

    public WeatherDataDBHelper(Context context, String name,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void saveWeatherDataArrayListInDB(ArrayList<WeatherData> weatherDataArrayList) {

        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < weatherDataArrayList.size(); i++) {
            WeatherData tempWeatherData = weatherDataArrayList.get(i);

            ContentValues contentValues = new ContentValues();

            contentValues.put(WeatherDataContract.WeatherEntry.COLUMN_DATE, tempWeatherData.getDate());
            contentValues.put(WeatherDataContract.WeatherEntry.COLUMN_MAX_TEMP, tempWeatherData.getMaxTemp());
            contentValues.put(WeatherDataContract.WeatherEntry.COLUMN_MIN_TEMP, tempWeatherData.getMinTemp());
            contentValues.put(WeatherDataContract.WeatherEntry.COLUMN_HUMIDITY, tempWeatherData.getHumidity());
            contentValues.put(WeatherDataContract.WeatherEntry.COLUMN_PRESSURE, tempWeatherData.getPressure());
            contentValues.put(WeatherDataContract.WeatherEntry.COLUMN_WEATHERCITY, tempWeatherData.getWeatherCity());
            contentValues.put(WeatherDataContract.WeatherEntry.COLUMN_WEATHER_CONDITION, tempWeatherData.getWeatherCondition());
            contentValues.put(WeatherDataContract.WeatherEntry.COLUMN_WEATHER_ID, tempWeatherData.getWeatherId());

            db.insert(WeatherDataContract.WeatherEntry.TABLE_NAME, null, contentValues);
        }
    }

//    public ArrayList<WeatherData> getAllWeatherFromDataBase(){
//        ArrayList<WeatherData> weatherDataArrayList = new ArrayList<>();
//
//        SQLiteDatabase db = getReadableDatabase();
//
//        String query = "SELECT * FROM " + WeatherDataContract.WeatherEntry.TABLE_NAME;
//
//        Cursor c = db.rawQuery(query, null);
//
//        if(c.moveToFirst()){
//            do {
//
//                WeatherData tempWeatherData = new WeatherData();
//
//
//
//
//            } while (c.moveToNext());
//        }
//
//    }


}
