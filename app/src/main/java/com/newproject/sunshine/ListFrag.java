package com.newproject.sunshine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ListFrag extends Fragment {

    private View rootView;
    private ArrayList<WeatherData> weatherDataArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);


        Bundle bundle = getArguments();
        if (bundle != null) {
            weatherDataArrayList = bundle.getParcelableArrayList("weatherData");
        } else {

        }


        ListView listView = (ListView) rootView.findViewById(R.id.list_View);

//        //dummy data
//        final ArrayList arrayList = new ArrayList();
//        arrayList.add("Today-Sunny-88/63");
//        arrayList.add("Tomorrow-Foggy-70/46");
//        arrayList.add("Weds-Cloudy-72/63");
//        arrayList.add("Thurs-Rainy-64/51");
//        arrayList.add("Fri-Foggy-70/46");
//        arrayList.add("Sat-Sunny-76/63");


//        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(), R.layout.list_item,
//                R.id.list_item_text_view, arrayList);

        CustomAdapter customAdapter = new CustomAdapter(getActivity(),
                R.layout.list_item, weatherDataArrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("item", weatherDataArrayList.get(position));

                DetailFrag detailFrag = new DetailFrag();
                detailFrag.setArguments(bundle);

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.Container, detailFrag)
                        .addToBackStack(null)
                        .commit();

            }
        });


        return rootView;
    }

    public class CustomAdapter extends ArrayAdapter {

        private final int VIEW_TYPE_TODAY = 0;
        private final int VIEW_TYPE_FUTURE_DAY = 1;

        private LayoutInflater inflater;

        public CustomAdapter(Context context, int resource, ArrayList<WeatherData> weatherDataArrayList) {
            super(context, resource, weatherDataArrayList);
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getItemViewType(int position) {
            return (position == 0) ? VIEW_TYPE_TODAY : VIEW_TYPE_FUTURE_DAY;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
        //public View newView

        //public CustomAdapter(Context context, int resource, ArrayList<WeatherData> weatherDataArrayList) {
        //  super(context, resource, weatherDataArrayList);
        //}

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(getItemViewType(position) == VIEW_TYPE_TODAY){
                convertView = inflater.inflate(R.layout.list_item_forecast_today, parent, false);

            } else {
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }

            ImageView list_item_icon = (ImageView) convertView.findViewById(R.id.list_item_icon);
            TextView text_day = (TextView) convertView.findViewById(R.id.text_day);
            TextView text_max_temp = (TextView) convertView.findViewById(R.id.text_max_temp);
            TextView text_min_temp = (TextView) convertView.findViewById(R.id.text_min_temp);
            TextView text_weather = (TextView) convertView.findViewById(R.id.text_weather);

            WeatherData weatherData = (WeatherData) getItem(position);
            //timemilli ko days me covert kerne ka method
            text_day.setText(convertTimemillisToDay(weatherData.getDate() * 1000));
            text_max_temp.setText(weatherData.getMaxTemp() + getResources().getString(R.string.degree_symbol));
            text_min_temp.setText(weatherData.getMinTemp() + getResources().getString(R.string.degree_symbol));
            text_weather.setText(weatherData.getWeatherCondition());
            list_item_icon.setImageResource(getImageFromWeatherId(weatherData.getWeatherId()));

            return convertView;
        }
    }

    private int getImageFromWeatherId(int weatherId){
        if (weatherId >= 200 && weatherId <= 232){
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 300 && weatherId <= 321){
            return R.drawable.art_clear;
        } else if (weatherId >= 500 && weatherId <= 531){
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 600 && weatherId <= 622){
            return R.drawable.art_clear;
        } else if (weatherId >= 701 && weatherId <= 781){
            return R.drawable.art_light_clouds;
        } else if (weatherId == 800){
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 801 && weatherId <= 804){
            return R.drawable.art_light_clouds;
        } else {
            return R.drawable.kweather;
        }
    }



    private String convertTimemillisToDay(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);

        String day = null;
        int dateOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dateOfWeek) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }

        return day;
    }

}
