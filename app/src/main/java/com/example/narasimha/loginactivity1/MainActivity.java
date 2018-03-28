package com.example.narasimha.loginactivity1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.narasimha.loginactivity1.Model.Weather;
import com.example.narasimha.loginactivity1.adapter.HomeDataAdapter;
import com.example.narasimha.loginactivity1.db.MyDBHelper;
import java.util.ArrayList;
import es.dmoral.toasty.Toasty;
public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setTitle(R.string.title_home);
        }
        setContentView(R.layout.activity_main);
         //create recycler view
        RecyclerView homeRecyclerView = findViewById(R.id.homeRecyclerView);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        //get the data
        MyDBHelper myDBHelper = new MyDBHelper(this, "MyDb", 2);
        //get data from db/server
        ArrayList<Weather> weatherData = myDBHelper.getAllWeatherInfo();
        //create adapter with data
        HomeDataAdapter homeDataAdapter = new HomeDataAdapter(weatherData);
        //setadapter to recycler view
        homeRecyclerView.setAdapter(homeDataAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toasty.info(MainActivity.this, "Settings Clicked").show();
        }
        else if (id == R.id.action_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences("MYAPP", MODE_PRIVATE);
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();
            Toasty.error(MainActivity.this, "You logged out from App ").show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        else if(id == R.id.action_add_weather){
            startActivity(new Intent(MainActivity.this, AddWeatherActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

