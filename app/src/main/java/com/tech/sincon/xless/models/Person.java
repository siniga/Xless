package com.tech.sincon.xless.models;

import java.util.List;

/**
 * Created by getcore03 on 7/24/2016.
 */
public class Person {

    int image;
    String name;

    public void Person(int image, String name){
        this.image = image;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
