package com.colorapps.code.test1.Helpers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.colorapps.code.test1.Model.Album;
import com.colorapps.code.test1.Model.Song;

import java.util.ArrayList;

public class MusicRetriever {

    public static ArrayList<Album> albums=new ArrayList<Album>();
    public static ArrayList<Song> songs=new ArrayList<Song>();
    /*public static void loadSongs(Context context){
        ContentResolver contentResolver=context.getContentResolver();
        Uri songUri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor=contentResolver.query(songUri,null,null,null);
        if(songCursor!=null && songCursor.moveToFirst()){
            int songTitle=songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist=songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songAlbum=songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            do{
                Song currentSong = new Song(songCursor.getString(songTitle),songCursor.getString(songArtist),songCursor.getString(songAlbum));
                songs.add(currentSong);

            } while (songCursor.moveToNext());
        }
    }*/
    public static void loadSongs(Context context) throws Exception{

        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);

        if(cur != null) {
            if(cur.getCount() > 0) {
                while(cur.moveToNext()){
                    Song currentSong = new Song(
                            cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                            cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                            cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                    songs.add(currentSong);
                }
            }
            cur.close();
        }
    }

    public static void loadAlbums(Context context) throws Exception{
        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.ALBUM + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);

        if(cur != null) {
            if(cur.getCount() > 0) {
                while(cur.moveToNext()){
                    if(cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media.SIZE))!=0&&!isAlbumAlreadyLoaded(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM)))){
                        Album currentAlbum = new Album(
                                cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                                cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                                cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                        albums.add(currentAlbum);
                        Log.d("Cancion: ",cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                    }

                    //Log.d("Cancion: ",cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE))+" pesa: "+cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media.SIZE)));

                }
            }
            cur.close();
        }

    }

    public static boolean isAlbumAlreadyLoaded(String albumName){
        boolean bool = false;
        if(!albums.isEmpty()){
            for(Album album : albums){
                if(album.getTitle().equals(albumName)){
                    bool=true;
                }
            }
        }
        return bool;
    }



    public static ArrayList<Album> getAlbums(){ return albums; }


}
