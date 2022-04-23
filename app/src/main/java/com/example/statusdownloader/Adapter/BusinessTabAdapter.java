package com.example.statusdownloader.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.statusdownloader.Fragments.BImageFragment;
import com.example.statusdownloader.Fragments.BSavedFragment;
import com.example.statusdownloader.Fragments.BVideoFragment;

public class BusinessTabAdapter extends FragmentPagerAdapter {

    private int btabno;
    public BusinessTabAdapter(@NonNull FragmentManager fm, int behaviorResumeOnlyCurrentFragment, int btabno) {
        super(fm, btabno);
        this.btabno = btabno;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position)
        {
            case 0 : return new BImageFragment();
            case 1 : return new BVideoFragment();
            case 2 : return new BSavedFragment();
            default: return null;

        }


    }

    @Override
    public int getCount() {
        return btabno;
    }
}
