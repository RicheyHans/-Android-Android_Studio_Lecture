package com.example.mcbud.musicplayer2_171012;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;
/**
 * Created by mcbud on 2017-10-12.
 */

public class ListPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragments;
    public ListPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
