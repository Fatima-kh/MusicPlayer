package com.example.mymusicplayer.Controller;
import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mymusicplayer.Model.Music;
import com.example.mymusicplayer.R;
import com.example.mymusicplayer.Repository.SongRepository;

public abstract class SingleFragmentActivity extends AppCompatActivity implements TabbedViewFragment.OnFragmentInteractionListener, MusicFragment.OnFragmentInteractionListener,AlbumFragment.OnFragmentInteractionListener {

    public static final String TAG = "SingleFragmentActivity";

    public abstract Fragment createFragment();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        getSongs();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null)
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, createFragment())
                    .commit();
    }
    @Override
    public void messageFromParentFragment(Uri uri) {
        Log.i("TAG", "received communication from parent fragment");
    }

    @Override
    public void messageFromChildFragment(Uri uri) {
        Log.i("TAG", "received communication from child fragment");
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void getSongs() {

        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri albumUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Cursor cursor_music = null;
        Cursor cursor_album = null;
        if (Build.VERSION.SDK_INT < 26) {
            if (ActivityCompat.checkSelfPermission(SingleFragmentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                String[] per = {Manifest.permission.READ_EXTERNAL_STORAGE};
                Log.d(TAG, "music aded" + "<26");


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(per, 1);

                }

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            cursor_music = getContentResolver().query(musicUri, null, null, null);

        } else {
            cursor_music = getContentResolver().query(musicUri, null, null, null, null, null);
        }
        // cursor_album = getContentResolver().query(albumUri, null, null, null, null, null);

        if (cursor_music.getCount() != 0 && cursor_music != null) {


            int i = 0;
            cursor_music.moveToFirst();
            while (!cursor_music.isAfterLast()) {
                Long albumId = cursor_music.getLong(cursor_music.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                cursor_album = getContentResolver().query(albumUri, new String[]{MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                        MediaStore.Audio.Albums._ID + "=" + albumId,
                        null,
                        null);

                if (cursor_album != null && cursor_album.moveToFirst()) {
                    Long id = cursor_music.getLong(cursor_music.getColumnIndex(MediaStore.Audio.Media._ID));
                    String picpath = cursor_album.getString(cursor_album.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
                    String title = cursor_music.getString(cursor_music.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String artist = cursor_music.getString(cursor_music.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String album = cursor_music.getString(cursor_music.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    Uri uri1 = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
                    SongRepository.getInstance().addMusic(new Music(id, title, artist, album, uri1, picpath));
                    if (SongRepository.getInstance().getMusicList().size() > 0) {
                        Log.d(TAG, "music :" + SongRepository.getInstance().getMusicList().get(i).getTitle());
                        i++;
                    }
                    // cursor_album.moveToNext();
                }
                cursor_music.moveToNext();
            }
            cursor_album.close();
        }
        cursor_music.close();


    }
}
