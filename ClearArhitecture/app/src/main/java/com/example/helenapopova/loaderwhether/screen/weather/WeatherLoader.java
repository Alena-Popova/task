package com.example.helenapopova.loaderwhether.screen.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.example.helenapopova.loaderwhether.model.City;
import com.example.helenapopova.loaderwhether.network.ApiFactory;

import java.io.IOException;


/**
 * @author Artur Vasilov
 */
public class WeatherLoader extends AsyncTaskLoader<City> {

    private final String mCityName;

    public WeatherLoader(Context context, @NonNull String cityName) {
        super(context);
        mCityName = cityName;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public City loadInBackground() {
        try {
            return ApiFactory.getWeatherService().getWeather(mCityName).execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}


