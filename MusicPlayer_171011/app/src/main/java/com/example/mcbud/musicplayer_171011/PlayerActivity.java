package com.example.mcbud.musicplayer_171011;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mcbud.musicplayer_171011.model.Music;

import static com.example.mcbud.musicplayer_171011.R.id.viewPager;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {

    Music music;
    MediaPlayer player = null;
    int current = -1;
    ViewPager viewPager;
    private RelativeLayout controller;
    private SeekBar seekBar;
    private TextView textCurrentTime;
    private TextView textDuration;
    private ImageButton btnPlay;
    private ImageButton btnFf;
    private ImageButton btnRew;
    private ImageButton btnNext;
    private ImageButton btnPrev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        load();
        initView();
        initViewPager();
        initControl();
        start();
    }

    private void load(){
        music = Music.getInstance();
        // music이 로드되어 있으면 다시 로드하지 않도록 처리(목록 화면이 아닌 플레이어 화면으로 넘어가는 경우)

        Intent intent = getIntent();
        current = intent.getIntExtra(Const.KEY_POSITION, -1);   // 이 이름에 해당하는 음악 로드. 그리고 null이 올 수 없으므로 -1
    }

    private void initControl(){
        // 볼륨 조절이 정상적으로 되도록(없을 경우 벨소리가 조정됨)
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setPlayer();

    }

    SeekBarThread seekBarThread = null;
    private void setPlayer(){
        // position에 해당하는 현재 아이템을 꺼낸다
        Music.Item item = music.data.get(current);
        Uri musicUri = item.musicUri;

        if(seekBarThread != null)
            seekBarThread.setStop();

        if(player != null) {
            player.release();
            player = null;
        }

        // 음악 uri를 사용해서 플레이어 초기화
        player = MediaPlayer.create(this, musicUri); // (context, 음악URI)

        // 옵션
        player.setLooping(false);

        // 화면 세팅
        String duration = militoSec( player.getDuration() );     // 123456777 => 03:15
        textDuration.setText(duration);
        textCurrentTime.setText("00:00");

        seekBar.setMax(player.getDuration());

        seekBarThread = new SeekBarThread(handler);
        seekBarThread.start();
    }

    private String militoSec(int mili){
        int sec = mili / 1000;      // 초 단위 변환
        int min = sec / 60;         // 분 단위 변환
        sec = sec % 60;             // 초 산출

        return String.format("%02d", min) + ":" + String.format("%02d", sec);      // 두 자리에 0이 자동으로 붙는 문자열
    }

    private void initView(){
        setContentView(R.layout.activity_player);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        controller = (RelativeLayout) findViewById(R.id.controller);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textCurrentTime = (TextView) findViewById(R.id.textCurrentTime);
        textDuration = (TextView) findViewById(R.id.textDuration);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnFf = (ImageButton) findViewById(R.id.btnFf);
        btnRew = (ImageButton) findViewById(R.id.btnRew);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);


        btnPlay.setOnClickListener(this);
        btnFf.setOnClickListener(this);
        btnRew.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
    }

    private void initViewPager(){
        PlayerPagerAdapter adapter = new PlayerPagerAdapter(this, music.data);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current = position;
                setPlayer();
                if(playButtonStat == Const.STAT_PLAY){
                    start();
                } else {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // current가 -1이 아닐 경우(페이지가 있을 경우)
        if(current > -1){
            viewPager.setCurrentItem(current);
        }
    }

    private void start(){
        playButtonStat = Const.STAT_PLAY;
        player.start();
        btnPlay.setImageResource(android.R.drawable.ic_media_pause);

    }

    private void pause(){
        playButtonStat = Const.STAT_PAUSE;
        player.pause();
        btnPlay.setImageResource(android.R.drawable.ic_media_play);
    }

    @Override
    protected void onDestroy() {
        if(seekBarThread != null)
            seekBarThread.setStop();
        // 이 부분이 없을 경우 무조건 백엔드 실행 가능

        if(player != null){
            player.release();
        }

        super.onDestroy();
    }

    // 목록에서 플레이어로 들어오는 순간 스테이터스 변경
    int playButtonStat = Const.STAT_PLAY;

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnPlay:
                // 음악 재생 시 음악이 자동 실행되므로, puase버튼으로 바뀌어 있어야 한다. Pause 선택 시에는 플레이로 변경
                if(playButtonStat == Const.STAT_PLAY)
                   pause();
                else
                    start();
                break;
            case R.id.btnFf:
                break;
            case R.id.btnRew:
                break;
            case R.id.btnNext:
                break;
            case R.id.btnPrev:
                break;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Const.WHAT_SET:
                    if(player != null) {
                        int cp = player.getCurrentPosition();
                        seekBar.setProgress(cp);
                        textCurrentTime.setText(militoSec(cp));
                    }
                    break;
            }
        }
    };
}

class SeekBarThread extends Thread {
    private boolean runFlag = true;
    private Handler handler;

    SeekBarThread(Handler handler){
        this.handler = handler;
    }

    public void setStop(){
           runFlag = false;
       }
    public void run(){
        while(runFlag) {
            handler.sendEmptyMessage(Const.WHAT_SET);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
