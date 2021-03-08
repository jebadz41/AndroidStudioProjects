package com.example.backendlessmap;

import android.app.Application;

import com.backendless.Backendless;

public class ApplicationClass extends Application {

    public static final String APPLICATION_ID = "E24E951E-3CD5-F5F9-FFBB-01719FFE2600";
    public static final String API_KEY = "F1F625BB-945E-413F-BB88-C765A106E4D8";
    public static final String SERVER_URL = "https://eu-api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp( getApplicationContext(), APPLICATION_ID, API_KEY );
        Backendless.setUrl(SERVER_URL);
    }
}
