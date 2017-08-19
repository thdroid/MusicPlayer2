package com.example.naoto.musicplayer;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naoto on 2017/08/08.
 */

public class Track {
    public static final String[] COLUMNS = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.TRACK,
    };
    public long id, albumId, artistId, duration;
    public String path, title, album, artist;
    public Uri uri;
    public int trackNo;


    public Track(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
        path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
        title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
        album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
        artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
        artistId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
        albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
        duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
        trackNo = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
        uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
    }


    public static List getItems(Context activity) {
        List list = new ArrayList();
        ContentResolver resolver = activity.getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                Track.COLUMNS, null, null, null
        );
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
                list.add(new Track(cursor));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
