package com.example.statusdownloader.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.statusdownloader.Fragments.ImageFragment;
import com.example.statusdownloader.Fragments.SavedFragment;
import com.example.statusdownloader.Fragments.VideoFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private int tabno;
    public TabAdapter(@NonNull FragmentManager fm, int behaviorResumeOnlyCurrentFragment, int tabno) {
        super(fm, tabno);

        this.tabno = tabno;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0 : return new ImageFragment();
            case 1 : return new VideoFragment();
            case 2 : return new SavedFragment();
            default: return null;

        }
    }

    @Override
    public int getCount() {
        return tabno;
    }
}
