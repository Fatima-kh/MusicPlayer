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
    public List<Music> getFilteredMusicList(int position){
        List<Music> filteredMusicList=new ArrayList<>();

        for(int i=0; i<musicList.size();i++){
            boolean flag=true;
            if(position==2){
                for(Music music:filteredMusicList){
                    if(music.getArtist().equals(musicList.get(i).getArtist())){
                        flag=false;
                    }

                }
            }else if(position==1){
                for(Music music:filteredMusicList){
                    if(music.getAlbum().equals(musicList.get(i).getAlbum())){
                        flag=false;
                    }

                }
            }
            if(flag==true){
                filteredMusicList.add(musicList.get(i));
            }
        }
       return filteredMusicList;
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
