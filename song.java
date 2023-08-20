package com.example.muzic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.EnvironmentalReverb;
import android.media.audiofx.PresetReverb;
import android.media.audiofx.AudioEffect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class song extends AppCompatActivity {
    @Override
    protected void onDestroy() {
       super.onDestroy();
        mediaplayer.stop();
       mediaplayer.release();
    }
    TextView tv;
    String name;
    ImageView play,prev,next;
    ArrayList<File> songs;
    public MediaPlayer mediaplayer;
    String songname;
    int position;
    SeekBar seekbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        tv=findViewById(R.id.textView);
        play=findViewById(R.id.play);
        prev=findViewById(R.id.prev);
        next=findViewById(R.id.next);
        seekbar=findViewById(R.id.seekbar);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        songs=(ArrayList) bundle.getParcelableArrayList("songlist");
        songname=intent.getStringExtra("currentsong");
        tv.setText(songname);
        position=intent.getIntExtra("position",0);
        name=songname;
        Uri uri=Uri.parse(songs.get(position).toString());
        mediaplayer=MediaPlayer.create(this,uri);
        mediaplayer.start();
        seekbar.setMax(mediaplayer.getDuration());
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                       mediaplayer.seekTo(seekBar.getProgress());
            }
        });
        Thread updateseek;
        updateseek=new Thread(){
            @Override
            public void run() {
                int currentposition=0;
                try{
                    while(currentposition<mediaplayer.getDuration()) {
                        currentposition = mediaplayer.getDuration();
                        sleep(500);
                    }
                }
                catch(Exception e){
                }
            }
        };
        updateseek.start();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaplayer.isPlaying())
                {
                    play.setImageResource(R.drawable.play);
                    mediaplayer.pause();
                }
                else
                {
                    play.setImageResource(R.drawable.pause);
                    mediaplayer.start();
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.stop();
                mediaplayer.release();
                if(position!=0) {
                    position -= 1;
                }
                else {
                    position = songs.size() - 1;
                }
                Uri uri = Uri.parse(songs.get(position).toString());
                mediaplayer = MediaPlayer.create(getApplicationContext(), uri);
                mediaplayer.start();
                seekbar.setMax(mediaplayer.getDuration());
                songname = songs.get(position).getName().toString();
                name=songname;
                tv.setText(songname);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.stop();
                mediaplayer.release();
                if (position != songs.size() - 1) {
                    position += 1;
                }
                else{
                    position = 0;
                }

                Uri uri = Uri.parse(songs.get(position).toString());
                mediaplayer = MediaPlayer.create(getApplicationContext(), uri);
                mediaplayer.start();
                seekbar.setMax(mediaplayer.getDuration());
                songname = songs.get(position).getName().toString();
                name=songname;
                tv.setText(songname);
            }
        });




    }
    public void openlyrics(View v){
        Intent intent1=new Intent(this,lyric.class);
        startActivity(intent1);
    }

    public void opensp(View v)
    {
        Intent intent=new Intent(this,sp.class);
        intent.putExtra("na",name);
        startActivity(intent);
    }
}
