package com.example.mymusicplayer.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class Music {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private Uri uri;
    private String picPath;
 //   private Bitmap bitmap;

    public Uri getUri() {
        return uri;
    }

    public Bitmap getBitmap() {
       return BitmapFactory.decodeFile(getPicPath());


    }


    public String getPicPath() {
        return picPath;
    }

    public String getAlbum() {
        return album;
    }

    public Music(Long id, String title, String artist, String album, Uri uri, String picPath) {
        this.picPath = picPath;
        this.uri = uri;
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
     //   this.bitmap = bitmap;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}
