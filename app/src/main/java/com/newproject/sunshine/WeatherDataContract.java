package com.newproject.sunshine;

import android.provider.BaseColumns;

/**
 * Created by vikram on 26/11/16.
 */

public class WeatherDataContract  {

    private WeatherDataContract(){}

    // id - primary_key, day, weather condition, max, min, pressure, humidity, weathercity
    public static class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "weather";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_WEATHER_ID = "weather_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WEATHER_CONDITION = "weather_condition";
        public static final String COLUMN_MAX_TEMP = "max";
        public static final String COLUMN_MIN_TEMP = "min";
        public static final String COLUMN_PRESSURE= "pressure";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_WEATHERCITY = "city";

    }

}
