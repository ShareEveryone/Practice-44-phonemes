package com.example.kyhandsome.tkpmgd.Model;

public class Sound {
    public int id;
    public String name;
    public String textinstruction;
    public String videourl;
    public String imageurl;
    public String audiourl;
    public Sound(int id, String name, String textinstruction, String videourl, String imageurl, String audiourl){
        this.id = id;
        this.name = name;
        this.textinstruction = textinstruction;
        this.videourl = videourl;
        this.imageurl = imageurl;
        this.audiourl = audiourl;
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

    public String getTextinstruction() {
        return textinstruction;
    }

    public void setTextinstruction(String textinstruction) {
        this.textinstruction = textinstruction;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getAudiourl() {
        return audiourl;
    }

    public void setAudiourl(String audiourl) {
        this.audiourl = audiourl;
    }
}
