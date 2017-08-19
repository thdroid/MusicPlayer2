package com.example.naoto.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Naoto on 2017/08/08.
 */

public class ListTrackAdapter extends ArrayAdapter<Track> {
    LayoutInflater inflater;

    public ListTrackAdapter(Context context, List item) {
        super(context,0,item);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Track item = (Track)getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_track, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.artist = (TextView) convertView.findViewById(R.id.artist);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.duration);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        long dm = item.duration / 60000;
        long ds = (item.duration - (dm * 60000)) / 1000;
        viewHolder.artist.setText(item.artist);
        viewHolder.title.setText(item.title);
        viewHolder.duration.setText(String.format("%02d:%02d", dm, ds));
        return convertView;
    }

    static class ViewHolder {
        TextView artist;
        TextView duration;
        TextView title;
    }
}
