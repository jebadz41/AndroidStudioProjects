package com.example.contacts;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.List;

public class ApplicationClass extends Application
{
    public static final String APPLICATION_ID = "31E3C22C-8148-F273-FFF5-990176D44000";
    public static final String API_KEY = "1BA9AA58-0A8F-4DC2-973D-C3791347A479";
    public static final String SERVER_URL = "https://eu-api.backendless.com";

    public static BackendlessUser user;
    public static List<Contact> contacts;

    @Override
    public void onCreate() {
        super.onCreate();


        Backendless.initApp( getApplicationContext(), APPLICATION_ID, API_KEY );
        Backendless.setUrl(SERVER_URL);

    }
}
