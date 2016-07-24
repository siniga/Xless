package com.tech.sincon.xless.models;

/**
 * Created by getcore03 on 7/23/2016.
 */
public class Person {
    private int image;
    private String name;

    public Person(String name, int image){
       this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
