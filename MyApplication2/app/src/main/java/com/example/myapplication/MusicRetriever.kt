package com.example.myapplication

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import java.util.*

class MusicRetriever(internal var context: Context) {
    var songs: ArrayList<Song>? = null

    init {
        if (songs == null) songs = ArrayList()
    }

    fun loadSongs() {
        val contentResolver = context.contentResolver
        val songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val songCursor = contentResolver.query(songUri, null, null, null, null)

        if (songCursor != null && songCursor.moveToFirst()) {
            val songId = songCursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val songData = songCursor.getColumnIndex(MediaStore.Audio.Media.TRACK)
            val songYear = songCursor.getColumnIndex(MediaStore.Audio.Media.YEAR)
            val songDuration = songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
            do {
                val currentId = songCursor.getLong(songId)
                val currentTitle = songCursor.getString(songTitle)
                val currentArtist = songCursor.getString(songArtist)
                val currentData = songCursor.getString(songData)
                val currentYear = songCursor.getString(songYear)
                val currentDuration = songCursor.getString(songDuration)
                Log.d(TAG, "song: $currentTitle")
                songs!!.add(Song(currentId, currentTitle, currentArtist, currentData , currentYear , currentDuration ))
            } while (songCursor.moveToNext())
        }
    }

    companion object {
        private val TAG = "MyActivity"
    }
}
