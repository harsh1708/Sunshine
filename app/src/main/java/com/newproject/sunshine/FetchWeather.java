package com.newproject.sunshine;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by vikram on 13/11/16.
 */

public class FetchWeather extends AsyncTask<String, Void, String> {

    //context kyun use kiya h yaha
    private Context context;
    private int isError = 0;

    public FetchWeather(Context context){
        this.context = context;
    }


    @Override
    protected String doInBackground(String... params) {
        String response = null;
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setReadTimeout(30000 /*milliseconds*/);
            urlConnection.setConnectTimeout(60000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            //parse errorCode
            if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                //Log.d(TAG, "ResponseMessage :- " + urlConnection.getResponseMessage());

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                StringBuilder stringBuilder = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                in.close();

                response = stringBuilder.toString();

            } else {
                urlConnection.disconnect();
            }

        } catch (IOException e) {
            isError = -1;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return response;
    }


    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        if(response != null) {
            Log.d("weather_output", response);
            ArrayList<WeatherData> weatherDataArrayList = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(response);
                WeatherData weatherData = new WeatherData();
                JSONObject cityObject = object.getJSONObject("city");
                String cityName = cityObject.getString("name");
                weatherData.setWeatherCity(cityName);

                JSONArray list = object.getJSONArray("list");

                if (list != null) {
                    for (int i = 0; i < list.length(); i++) {
                        WeatherData tempWeatherData = new WeatherData();
                        tempWeatherData.setWeatherCity(cityName);


                        JSONObject tempObject = (JSONObject) list.get(i);
                        JSONObject tempratureObject = tempObject.getJSONObject("temp");

                        double minTemp = tempratureObject.getDouble("min");
                        double maxTemp = tempratureObject.getDouble("max");


                        long pressure = tempObject.getLong("pressure");
                        double humidity = tempObject.getDouble("humidity");

                        long date = tempObject.getLong("dt");
                        //ye niche wala kese kiya h
                        JSONArray weatherArray = tempObject.getJSONArray("weather");
                        JSONObject firstJson = weatherArray.getJSONObject(0);
                        String weatherCondition = firstJson.getString("main");
                        int weatherId = firstJson.getInt("id");

                        tempWeatherData.setMinTemp(minTemp);
                        tempWeatherData.setMaxTemp(maxTemp);
                        tempWeatherData.setPressure(pressure);
                        tempWeatherData.setHumidity(humidity);
                        tempWeatherData.setDate(date);
                        tempWeatherData.setWeatherId(weatherId);
                        tempWeatherData.setWeatherCondition(weatherCondition);

                        weatherDataArrayList.add(tempWeatherData);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Intent intent  = new Intent(context,MainActivity.class);
            intent.putParcelableArrayListExtra("weatherData",weatherDataArrayList);
            context.startActivity(intent);

     }


    }


}



