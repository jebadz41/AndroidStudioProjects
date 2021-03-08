package com.example.fragmentsrecycleview;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {


    public static ArrayList<Person> people;

    @Override
    public void onCreate() {
        super.onCreate();

        people = new ArrayList<Person>();
        people.add(new Person("Chuck Norris","0980352500"));
        people.add(new Person("John Rambo","012150210300"));
        people.add(new Person("MelSonNorris","098070234235300"));
    }
}
