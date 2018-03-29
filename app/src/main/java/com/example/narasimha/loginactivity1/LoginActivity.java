package com.example.narasimha.loginactivity1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.narasimha.loginactivity1.databinding.ActivityLoginBinding;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
     ActivityLoginBinding activityLoginBinding;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("LoginActivity", "onCreate");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setTitle(R.string.title_login);
        }
        sharedPreferences = getSharedPreferences("MYAPP", MODE_PRIVATE);
        boolean loginStatus = sharedPreferences.getBoolean("isLoggedIn", false);
        if (loginStatus) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValidUser = isValidFields();
                if (isValidUser) {
                    String username = activityLoginBinding.edtUsername.getText().toString();
                    int password = 0;
                    try {

                        password = Integer.parseInt(activityLoginBinding.edtPwd.getText().toString());

                    } catch (NumberFormatException e){

                        e.printStackTrace();

                    }
                    if (username.equals("Android") && password == 123456) {
                        //Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                        Toasty.success(LoginActivity.this, "Success!", Toast.LENGTH_SHORT, true).show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle b = new Bundle();
                        mainIntent.putExtras(b);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();
                        startActivity(mainIntent);
                        finish();
                    }
                    else {
//Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        Toasty.error(LoginActivity.this, "Login Failed.", Toast.LENGTH_SHORT, true).show();

                    }
                }

            }
        });
        activityLoginBinding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(regIntent);
            }
        });
    }
    /**
     * returns true if the fields satisfies given criteria/conditions otherwise return false
     *
     * @return
     */

    private boolean isValidFields() {
        if (activityLoginBinding.edtUsername.getText().length() < 5 || activityLoginBinding.edtUsername.getText().length() > 10) {
            Toast.makeText(LoginActivity.this, "Please enter valid Username", Toast.LENGTH_LONG).show();
            return false;
        }
        if (activityLoginBinding.edtPwd.getText().length() < 5) {
            Toast.makeText(LoginActivity.this, "Please enter valid Password", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }
    @Override
    protected void onStart() {
        super.onStart();
        //write logic to reload the data saved in onDestroy
        Log.d("LoginActivity", "OnStart Called");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LoginActivity", "OnResume Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LoginActivity", "OnPause Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LoginActivity", "onStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LoginActivity", "OnDestroy Called");
        //logic to store data entered in the ui

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LoginActivity", "OnRestart Called");
    }

}

