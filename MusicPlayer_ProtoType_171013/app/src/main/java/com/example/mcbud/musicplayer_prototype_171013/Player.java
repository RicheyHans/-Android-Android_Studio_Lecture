package com.example.mcbud.musicplayer_prototype_171013;

import android.media.MediaPlayer;
import android.net.Uri;

import com.example.mcbud.musicplayer_prototype_171013.domain.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcbud on 2017-10-13.
 */

public class Player {

    // Player 클래스의 음원 재생 기능
    MediaPlayer mp;

    // Player 클래스는 음원데이터를 보유하고 있어야 한다(PC플레이어의 재생목록 형태)
    List<Music> data;

    // 현재 플레이되고 있는 음악의 정보
    int currentPosition = -1;

    // 리스너 저장소
    List<Listener> listeners = new ArrayList<>();

    // 리스너를 등록하는 명령어
    public void addListener(Listener listener){
        listeners.add(listener);
    }

    // 리스너 인터페이스
    public interface Listener{
        void setProgress();
    }

    public void start(){
        new PlayerThread().start();
    }

    public class PlayerThread extends Thread{
        public void run(){
            while(true){
                for(Listener listener : listeners){
                    listener.setProgress();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


