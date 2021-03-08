package com.example.challengefragmentwithrecyclerview;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {


    public static ArrayList<Car> cars;

    @Override
    public void onCreate() {
        super.onCreate();

        cars = new ArrayList<Car>();
        cars.add(new Car("volkswagen","Polo","Chuck","0908249523"));
        cars.add(new Car("nissan","Almera","CNors","09045645623"));
        cars.add(new Car("mercedes","E200","Casgh","0324235523"));
        cars.add(new Car("volkswagen","Polo","jackk","090827686793"));
        cars.add(new Car("nissan","Almera","Cajjrs","090675623"));
        cars.add(new Car("mercedes","E200","Caaghh","0328923"));

    }
}
