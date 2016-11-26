package com.newproject.sunshine;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vikram on 13/11/16.
 */

public class WeatherData implements Parcelable {

    private String weatherCity;
    private long date;
    private double minTemp;
    private double maxTemp;
    private double pressure;
    private double humidity;

    private String weatherCondition;
    private int weatherId;
    public Object setCityName;

    public WeatherData(){}

    public String getWeatherCity() {
        return weatherCity;
    }

    public void setWeatherCity(String weatherCity) {
        this.weatherCity = weatherCity;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    protected WeatherData(Parcel in) {
        weatherCity = in.readString();
        date = in.readLong();
        minTemp = in.readDouble();
        maxTemp = in.readDouble();
        pressure = in.readDouble();
        humidity = in.readDouble();
        weatherCondition = in.readString();
        weatherId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(weatherCity);
        dest.writeLong(date);
        dest.writeDouble(minTemp);
        dest.writeDouble(maxTemp);
        dest.writeDouble(pressure);
        dest.writeDouble(humidity);
        dest.writeString(weatherCondition);
        dest.writeInt(weatherId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherData> CREATOR = new Parcelable.Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };
}
