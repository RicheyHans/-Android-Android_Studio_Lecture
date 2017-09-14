package com.example.mcbud.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


public class JoystickActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnUp, btnDown, btnLeft, btnRight, Player;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);

        initView();
        initListener();
    }

    int playerX = 0;
    int playerY = 0;

    private void initView(){
        btnUp = (Button)findViewById(R.id.btnUp);
        btnDown = (Button)findViewById(R.id.btnDown);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        Player = (Button)findViewById(R.id.player);


    }
    private void initListener(){
        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnUp: up(); break;
            case R.id.btnDown: down(); break;
            case R.id.btnLeft: left(); break;
            case R.id.btnRight: right();  break;
        }
    }

    private void up(){
        playerY -= 100;
        move();
    }
    private void down(){
        playerY += 100;
        move();
    }
    private void left(){
        playerX -= 100;
        move();
    }
    private void right(){
        playerX += 100;
        move();
    }

    private void move(){
        ObjectAnimator aniY = ObjectAnimator.ofFloat(Player, "translationY", playerY);
        ObjectAnimator aniX = ObjectAnimator.ofFloat(Player, "TranslationX", playerX);

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX);
        aniSet.start();
    }
}
