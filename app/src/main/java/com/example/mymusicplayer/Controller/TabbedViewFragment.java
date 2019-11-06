package com.example.mymusicplayer.Controller;
import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymusicplayer.Model.Music;
import com.example.mymusicplayer.R;
import com.example.mymusicplayer.Repository.SongRepository;
import com.google.android.material.tabs.TabLayout;

public class TabbedViewFragment extends Fragment {
    public static final int count_pages = 3;
    public static final String TAG = "tag";
    private OnFragmentInteractionListener mListener;
    ViewPager viewPager;
    TabLayout tabLayout;
    private View view;
    PagerAdapter adapter;
    private SingleFragmentActivity SingleFragmentActivity;
    public static int positionSaver=0;


    public TabbedViewFragment() {
        // Required empty public constructor
    }

    public static TabbedViewFragment newInstance() {
        Bundle args = new Bundle();
        TabbedViewFragment tabbedViewFragment = new TabbedViewFragment();
        tabbedViewFragment.setArguments(args);
        return tabbedViewFragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_tabbed_view, container, false);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.container_viewpager);
        viewPager.setAdapter(new PagerAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return view;

    }
    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MusicFragment.newInstance();
                case 1:
                    positionSaver=1;
                    return AlbumFragment.newInstance();
                case 2:
                    return MusicFragment.newInstance();
            }

            return MusicFragment.newInstance();

        }

        @Override
        public int getCount() {
            return count_pages;

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "MUSICS";
                case 1:
                    return "ALBUMS";
                case 2:
                    return "ARTISTS";


            }
            return null;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment musicFragment=new MusicFragment();
        Fragment albumFragment=new AlbumFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if(positionSaver==0){
            transaction.replace(R.id.container_viewpager, musicFragment).commit();
        }
        else if(positionSaver==1){
            transaction.replace(R.id.container_viewpager,albumFragment ).commit();
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromParentFragment(Uri uri);
    }



}
