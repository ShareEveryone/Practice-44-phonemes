package com.example.kyhandsome.tkpmgd.Model;

public class Worddo {
    public int id;
    public String name;
    public int id_sound;
    public int star;

    public Worddo(int id, String name, int id_sound, int star) {
        this.id = id;
        this.name = name;
        this.id_sound = id_sound;
        this.star = star;
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

    public int getUserid() {
        return star;
    }

    public void setUserid(int userid) {
        this.star = userid;
    }
}