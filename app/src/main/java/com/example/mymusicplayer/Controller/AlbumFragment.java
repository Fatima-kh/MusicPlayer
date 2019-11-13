package com.example.mymusicplayer.Controller;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.card.MaterialCardView;
import com.example.mymusicplayer.Model.Music;
import com.example.mymusicplayer.R;
import com.example.mymusicplayer.Repository.SongRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {
    RecyclerView recyclerView;
    AlbumAdapter adapter;
    SongRepository repository;
    //private OnFragmentInteractionListener mListener;
    public AlbumFragment() {
        // Required empty public constructor
    }
    public static AlbumFragment newInstance() {

        Bundle args = new Bundle();

        AlbumFragment fragment = new AlbumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_music, container, false);
        recyclerView = v.findViewById(R.id.recycler_music);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        repository = SongRepository.getInstance();
        adapter = new AlbumAdapter(repository.getFilteredMusicList(1));
        recyclerView.setAdapter(adapter);
        return v;
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumName, artistName;
        ImageView songImgView;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            songImgView = itemView.findViewById(R.id.image_album_image_view);
            albumName = itemView.findViewById(R.id.album_name_textView);
            artistName = itemView.findViewById(R.id.artist_name_textView);
        }

        public void bind(Music music) {
            albumName.setText(music.getAlbum());
            songImgView.setImageBitmap(music.getBitmap());
            artistName.setText(music.getArtist());




        }
    }

    public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
        List<Music> albumList;

        public AlbumAdapter(List<Music> albumlist) {
            this.albumList = albumlist;

        }

        public void setAdapter(List<Music> list) {
            albumList = list;


        }


        @NonNull
        @Override
        public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.album_item, parent, false);
            return new AlbumViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
            holder.bind(albumList.get(position));
        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }
    }
    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AlbumFragment.OnFragmentInteractionListener) {
            mListener = (AlbumFragment.OnFragmentInteractionListener) context;
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
        void messageFromChildFragment(Uri uri);
    }*/

}
