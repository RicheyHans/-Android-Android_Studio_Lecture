package com.example.mcbud.musicplayer_171011;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by mcbud on 2017-10-11.
 */

public class ListPagerAdapter extends FragmentStatePagerAdapter {
   // 프래그먼트를 리스트에 담아서 사용
    List<Fragment> list;

    public ListPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    // 목록의 개수
    @Override
    public int getCount() {
        return list.size();
    }
}
