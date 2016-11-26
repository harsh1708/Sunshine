package com.newproject.sunshine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFrag extends Fragment {

    private View rootView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_detail, container, false);


        TextView textView = (TextView) rootView.findViewById(R.id.details_frag);

        Bundle bundle= getArguments();
        if (bundle!=null){

            WeatherData weatherData = bundle.getParcelable("item");

            Log.e("Harsh", weatherData.getWeatherCondition());

            //String value= bundle.getString("item");
            //textView.setText(value);
        }




        return rootView;
    }

}
