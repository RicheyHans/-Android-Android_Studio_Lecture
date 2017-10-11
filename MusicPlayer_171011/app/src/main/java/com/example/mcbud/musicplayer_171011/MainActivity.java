package com.example.mcbud.musicplayer_171011;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mcbud.musicplayer_171011.dummy.DummyContent;
import com.example.mcbud.musicplayer_171011.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MusicFragment.OnListFragmentInteractionListener {

    Music music = null;
    ViewPager viewPager;
    TabLayout tablayout;

    @Override
    public void init(){
        setContentView(R.layout.activity_main);

        load();

        initView();
        initViewPager();
        initTabs();

        initListener();

    }


    private void load(){
        music = Music.getInstance();
        music.load(this);
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
    }

    private void initViewPager(){
        List<Fragment> list = new ArrayList<>();        // 다형성

        // 멀티턴의 개념이 적용되어 네이밍이 객체명.newInstance가 되며 new의 역할과 유사하다.
        MusicFragment fragTitle = MusicFragment.newInstance(1); // 컬럼의 개수가 파라미터
        MusicFragment fragArtist = MusicFragment.newInstance(1);
        MusicFragment fragAlbum = MusicFragment.newInstance(1);
        MusicFragment fragGenre = MusicFragment.newInstance(1);

        list.add(fragTitle);
        list.add(fragArtist);
        list.add(fragAlbum);
        list.add(fragGenre);

        ListPagerAdapter adapter = new ListPagerAdapter(getSupportFragmentManager(), list);

        viewPager.setAdapter(adapter);

    }

    private void initTabs(){
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.tab_title)));
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.tab_artist)));
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.tab_album)));
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.tab_genre)));
    }

    // 탭 레이아웃과 뷰 페이저를 연결해준다
    private void initListener(){
        tablayout.addOnTabSelectedListener( new TabLayout.ViewPagerOnTabSelectedListener(viewPager) );

        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener(tablayout) );
    }

    @Override
    public List<Music.Item> getList() {
        return music.data;
    }

    @Override
    public void openPlayer(int position) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(Const.KEY_POSITION, position);      // 몇 번째 음악인지 데이터를 들고가면 그 음악을 재생함. "POSITION"은 상수 개념이다
        startActivity(intent);
    }

}
