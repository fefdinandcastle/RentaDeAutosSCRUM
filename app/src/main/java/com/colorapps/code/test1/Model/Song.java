package com.colorapps.code.test1.Model;

public class Song {
    String title;
    String artist;
    String album;

    public Song (String title,String artist,String album){
        this.title=title;
        this.artist=artist;
        this.album=album;
        return;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() { return album; }

    public String getArtist() {
        return artist;
    }


}
