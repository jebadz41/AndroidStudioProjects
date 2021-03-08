package com.example.fragmentsrecycleview;

public class Person
{
    private  String name;
    private  String telN;

    public Person(String name, String telN) {
        this.name = name;
        this.telN = telN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelN() {
        return telN;
    }

    public void setTelN(String telN) {
        this.telN = telN;
    }
}

