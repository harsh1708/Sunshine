package com.newproject.sunshine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<WeatherData> weatherDataArrayList;
    private Context context;
    private int isError = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.kweather);
        actionBar.setDisplayUseLogoEnabled(true);

        //how to get intent from splash activity
        Intent intent = getIntent();
        if (intent != null) {
            weatherDataArrayList = intent.getParcelableArrayListExtra("weatherData");
        }


        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("weatherData", weatherDataArrayList);

        ListFrag listFrag = new ListFrag();
        listFrag.setArguments(bundle);


        //fragment show krwane ka method
        //context fragment add kerne ke liye use kiya gya h
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.Container);
        if (fragment != null) {

        } else {
            fragmentManager.beginTransaction()
                    .add(R.id.Container, listFrag)
                    .commit();
        }

    }







//         // main activity me fragment show krwane ka method
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.Container, new ListFrag())
//                .commit();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_search:
                return true;
            case R.id.refresh:
                return true;
            case R.id.settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }
}
