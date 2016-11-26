package com.newproject.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String weatherUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?q=331403&mode=json&units=metric&cnt=7&appid=70aecadcb0f56f51a2b7a31973e35394";

        FetchWeather fetchWeather = new FetchWeather(this);
        fetchWeather.execute(weatherUrl);


    }
}


