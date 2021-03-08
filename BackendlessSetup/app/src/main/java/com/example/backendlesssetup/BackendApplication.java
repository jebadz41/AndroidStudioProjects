package com.example.backendlesssetup;

import android.app.Application;

import com.backendless.Backendless;

public class BackendApplication extends Application {

    public static final String APPLICATION_ID = "304B68B3-674C-1576-FFB4-24CD6DF6B800";
    public static final String API_KEY = "5540B312-2618-4730-A280-5B7157C53526";
    public static final String SERVER_URL = "https://eu-api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp(getApplicationContext(), APPLICATION_ID, API_KEY);
        Backendless.setUrl(SERVER_URL);

    }
}
