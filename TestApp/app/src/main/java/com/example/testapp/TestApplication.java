package com.example.testapp;

import android.app.Application;

import com.backendless.Backendless;

public class TestApplication extends Application
{
    public static final String APPLICATION_ID = "02337DAC-FADB-63B9-FF76-5808D12D2E00";
    public static final String API_KEY = "A608CF26-258A-4F62-8E7F-93FC09BA61D7";
    public static final String SERVER_URL = "https://eu-api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp( getApplicationContext(), APPLICATION_ID, API_KEY );
        Backendless.setUrl(SERVER_URL);

    }
}
