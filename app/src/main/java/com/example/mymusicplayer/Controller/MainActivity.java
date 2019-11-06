package com.example.mymusicplayer.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.mymusicplayer.R;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

public class MainActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return TabbedViewFragment.newInstance();
    }
}

