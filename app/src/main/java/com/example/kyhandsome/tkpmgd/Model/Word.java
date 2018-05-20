package com.example.kyhandsome.tkpmgd.Model;

public class Word {
    public int id;
    public String name;
    public int id_sound;
    public String mean;
    public String decription;
    public int complete;
    public Word(int id, String name, int id_sound, String mean, String decription, int complete){
        this.id = id;
        this.name = name;
        this.id_sound = id_sound;
        this.mean = mean;
        this.decription = decription;
        this.complete = complete;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_sound() {
        return id_sound;
    }

    public void setId_sound(int id_sound) {
        this.id_sound = id_sound;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
