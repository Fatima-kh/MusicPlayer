package com.example.mymusicplayer.Controller;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.mymusicplayer.Model.Music;

import java.io.IOException;
import java.util.List;

public class ControlMusic {
    private MediaPlayer player;
    private List<Music> musicList;

    public ControlMusic() {
        player = new MediaPlayer();
    }

    public void play(Music music, Context context) throws IOException {

            if (player.isPlaying()) {
                player.stop();
                player.reset();
                player.release();
                player=new MediaPlayer();
            }



        player.setDataSource(context, music.getUri());
        player.prepare();
        player.start();


    }

    public void stop() {
        player.stop();
    }
}
