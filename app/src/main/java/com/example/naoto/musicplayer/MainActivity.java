package com.example.naoto.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private MediaPlayer mediaPlayer;
    private String mpath;
    private ImageButton play_button;
    private SeekBar seekBar;
    private TextView text_now, text_end, text_music;
    private Button intent_track;
    private int musicTimes = 0;
    private Handler handler = new Handler();
    public Runnable updateSeekbar = new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.removeCallbacks(updateSeekbar);
            handler.postDelayed(updateSeekbar, 1000);
        }
    };
    private Runnable updatePosition = new Runnable() {
        @Override
        public void run() {
            text_now.setText(String.format("%02d:%02d", Utils.toMin(mediaPlayer.getCurrentPosition()), Utils.toSec(mediaPlayer.getCurrentPosition())));
            handler.removeCallbacks(updatePosition);
            handler.postDelayed(updatePosition, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
        musicTimes = mediaPlayer.getDuration();
        play_button.setOnClickListener(this);
        intent_track.setOnClickListener(this);
        text_now.setText(String.format("00:00"));
        text_end.setText(String.format("%02d:%02d", Utils.toMin(musicTimes), Utils.toSec(musicTimes)));
        seekBar.setProgress(0);
        seekBar.setMax(musicTimes);
        seekBar.setOnSeekBarChangeListener(this);
    }

    private void initView() {
        mediaPlayer = MediaPlayer.create(this, R.raw.uchunohajimari);
        play_button = (ImageButton) findViewById(R.id.play_Button);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        text_end = (TextView) findViewById(R.id.text_End);
        text_now = (TextView) findViewById(R.id.text_Now);
        intent_track = (Button) findViewById(R.id.intent_track);
    }

    @Override
    public void onClick(View view) {
        if (view == play_button) {
            if (mediaPlayer.isPlaying())
                mediaPause();
            else mediaPlay();
        }
        if (view==intent_track){
            Intent intent=new Intent(MainActivity.this,TrackActivity.class);
            startActivityForResult(intent,0);
        }
    }


    private void mediaPlay() {
        mediaPlayer.start();
        handler.postDelayed(updatePosition, 1000);
        handler.postDelayed(updateSeekbar, 1000);
    }

    private void mediaPause() {
        mediaPlayer.pause();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromuser) {
        if (fromuser) mediaPlayer.seekTo(progress);
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        text_now.setText(String.format("%02d:%02d", Utils.toMin(mediaPlayer.getCurrentPosition()), Utils.toSec(mediaPlayer.getCurrentPosition())));
    }
}
