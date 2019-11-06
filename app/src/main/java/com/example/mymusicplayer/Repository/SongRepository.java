package com.example.mymusicplayer.Repository;

import com.example.mymusicplayer.Model.Music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SongRepository {
    private static  SongRepository instance ;
    List<Music> musicList;
    List<String> albums;

    public static SongRepository getInstance() {
        if (instance == null)
            instance= new SongRepository();
        return instance;
    }

    private SongRepository() {
        musicList = new ArrayList<>();
        albums=new ArrayList<>();
    }
    public List<Music> getMusicList(){
        return musicList;
    }
    public List<String> getMusicsAlbum(){
        for (Music music:musicList){
            if(!albums.contains(music.getAlbum()))
            albums.add(music.getAlbum());
        }
        return albums;
    }
    public void addMusic(Music music){
        musicList.add(music);
    }
    public void removeMusic(Music music){
        musicList.remove(music);
    }
    public void sort(){
        Collections.sort(musicList, new Comparator<Music>() {
            @Override
            public int compare(Music music, Music t1) {
                return music.getTitle().compareTo(t1.getTitle());
            }
        });
    }
}
