package com.example.narasimha.loginactivity1.adapter;

/**
 * Created by narasimha on 3/27/2018.
 */

import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.narasimha.loginactivity1.Model.Weather;
import com.example.narasimha.loginactivity1.R;
import java.util.ArrayList;
public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.WeatherViewHolder>{
    private ArrayList<Weather> weatherData;
    public HomeDataAdapter(ArrayList<Weather> weatherData) {
        this.weatherData = weatherData;
    }
    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //structure creation
        View view = layoutInflater.inflate(R.layout.item_home_weather, parent, false);
        return new WeatherViewHolder(view);
    }
    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        //content binding
        Weather currentWeather = weatherData.get(position);
        holder.weatherPlace.setText(currentWeather.city);
        holder.weatherTemp.setText(currentWeather.temp);
    }
    @Override
    public int getItemCount() {
        return weatherData.size();
    }
    public class WeatherViewHolder extends RecyclerView.ViewHolder{
        TextView weatherPlace, weatherTemp;
        public WeatherViewHolder(View itemView) {
            super(itemView);
            weatherPlace = itemView.findViewById(R.id.weather_place);
            weatherTemp = itemView.findViewById(R.id.weather_temp);
        }
    }
}

