package com.example.mcbud.threadbasic_170928;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    Button button;
    Rotater rotater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        rotater = new Rotater(handler) ;    // 핸들러는 서브스레드 객체 new 생성 시 담는다.
        rotater.start();
    }

    // thread를 종료시키는 함수
    public void stop(View view){
        rotater.setStop();
    }

    public static final int ACTION_SET = 999;

    // seekbar를 변경하는 Handler작성
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch(msg.what){
                case ACTION_SET:
                    float curRot = button.getRotation();
                    button.setRotation(curRot+6);
                    break;
            }
        }
    };
}

class Rotater extends Thread {
    Handler handler;

    boolean RUNNING = true;
    public Rotater(Handler handler){        // 메인스레드에 만들어 둔 핸들러를 서브에 전달해줌.
        this.handler = handler;
    }

    // start 메서드 호출 시 실행. ** run() 메서드만 서브 스레드에서 실행된다. 주의!! **
    public void run(){
        //for(int i = 0; i < 60; i++)
        while(RUNNING){
            // 핸들러 측으로 메시지를 보낸다 --> 루퍼가 돌다 받아서 26 라인의 핸들러에 전달한다.
            Message msg = new Message();
            msg.what = MainActivity.ACTION_SET;
            handler.sendMessage(msg);  // msg객체 자동으로 생성. 수동도 가능
            // 자동일때는 메시지 객체 없이 handler.sendEmptyMessage(MainActivity.ACTION_SET);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void setStop(){
        RUNNING = false;
    }

}