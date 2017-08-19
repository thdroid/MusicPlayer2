package com.example.naoto.musicplayer;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Naoto on 2017/08/08.
 */

public class TrackActivity extends Activity {
    String mpath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        final List tracks = Track.getItems(this);
        final ListTrackAdapter adapter = new ListTrackAdapter(this, tracks);
        final ListView listView = (ListView) findViewById(R.id.list_tracks);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ContentResolver resolver = getApplicationContext().getContentResolver();
                final Cursor cursor = resolver.query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        Track.COLUMNS, null, null, null
                );cursor.moveToFirst();
                cursor.moveToPosition(position);
                mpath=cursor.getString(1);
                System.out.println(mpath);
                cursor.close();
            }
        });
    }
}