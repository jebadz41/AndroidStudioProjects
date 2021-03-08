package com.example.challengefragmentwithrecyclerview;

public class Car {

    private String car_logo;
    private String car_model;
    private String owner_name;
    private String owner_tel;

    public Car(String make, String car_model, String owner_name, String owner_tel) {
        this.car_logo = make;
        this.car_model = car_model;
        this.owner_name = owner_name;
        this.owner_tel = owner_tel;
    }

    public String getCar_logo() {
        return car_logo;
    }

    public void setCar_logo(String car_logo) {
        this.car_logo = car_logo;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_tel() {
        return owner_tel;
    }

    public void setOwner_tel(String owner_tel) {
        this.owner_tel = owner_tel;
    }
}
