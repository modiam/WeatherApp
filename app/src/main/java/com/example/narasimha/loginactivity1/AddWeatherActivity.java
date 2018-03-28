package com.example.narasimha.loginactivity1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.narasimha.loginactivity1.Model.Weather;
import com.example.narasimha.loginactivity1.db.MyDBHelper;

import java.util.ArrayList;
public class AddWeatherActivity extends AppCompatActivity {
    private final String TAG = "AddWeatherActivity";
    TextInputEditText f1;
    TextInputEditText f2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        f1 = findViewById(R.id.edt_city);
        f2 = findViewById(R.id.edt_temp);
        final MyDBHelper myDBHelper = new MyDBHelper(this, "MyDb", 2);
        ArrayList<Weather> weathers = myDBHelper.getAllWeatherInfo();
        for(Weather weather : weathers){
            Log.d(TAG, ""+weather.city+" -  "+weather.temp+" -  "+weather.units);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(f1.length() == 0){
                    f1.setError("Field mandatory");
                    return;
                }
                myDBHelper.insertWeatherData(f1.getText().toString(), "20","F");
                int size = myDBHelper.getNoOfrows();
                Snackbar.make(view, "Number of rows : "+size, Snackbar.LENGTH_LONG)

                        .setAction("Action", null).show();
            }
        });
    }

}

