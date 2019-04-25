package com.colorapps.code.test1.Model;

public class Album {
    String title;
    String artist;
    String id;

    public Album (String title,String artist,String id){
        this.title=title;
        this.artist=artist;
        this.id=id;
        return;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {return id;}

    public String getArtist() {
        return artist;
    }
}
