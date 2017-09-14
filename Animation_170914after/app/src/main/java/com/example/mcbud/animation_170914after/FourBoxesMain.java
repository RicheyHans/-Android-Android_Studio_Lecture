package com.example.mcbud.animation_170914after;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class FourBoxesMain extends AppCompatActivity implements View.OnClickListener {
    Button spread, btn1,btn2, btn3, btn4;

    int btn1X = 0, btn1Y = 0;
    int btn2X = 0, btn2Y = 0;
    int btn3X = 0, btn3Y = 0;
    int btn4X = 0, btn4Y = 0;
    int rot = 3600;
    int rot2 = 0;
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_boxes_main);

        initView();
        initListener();
    }

    private void initView(){
        spread = (Button)findViewById(R.id.btn_spread);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
    }
    private void initListener(){
        spread.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_spread:
                if(!clicked) {
                    btn1Spread();
                    btn2Spread();
                    btn3Spread();
                    btn4Spread();
                    clicked = true;
                    spread.setText("come");

                } else {
                    btn1Come();
                    btn2Come();
                    btn3Come();
                    btn4Come();
                    clicked = false;
                    spread.setText("spread");
                }
                break;
            case R.id.btn1:
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }

    private void btn1Spread(){
        btn1X -= 160;
        btn1Y -= 160;
        spread1Start();
    }
    private void btn2Spread(){
        btn2X += 160;
        btn2Y -= 160;
        spread2Start();
    }
    private void btn3Spread(){
        btn3X -= 160;
        btn3Y += 160;
        spread3Start();
    }
    private void btn4Spread(){
        btn4X += 160;
        btn4Y += 160;
        spread4Start();
    }

    private void btn1Come(){
        btn1X += 160;
        btn1Y += 160;
        come1Start();
    }
    private void btn2Come(){
        btn2X -= 160;
        btn2Y += 160;
        come2Start();
    }
    private void btn3Come(){
        btn3X += 160;
        btn3Y -= 160;
        come3Start();
    }
    private void btn4Come(){
        btn4X -= 160;
        btn4Y -= 160;
        come4Start();
    }
    private void spread1Start(){
        ObjectAnimator aniX = ObjectAnimator.ofFloat(btn1, "TranslationX", btn1X);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(btn1, "TranslationY", btn1Y);
        ObjectAnimator rott = ObjectAnimator.ofFloat(btn1, "Rotation",rot);
        rott.setDuration(2000);
        aniX.setDuration(1000);
        aniY.setDuration(1000);
        rott.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, rott);
        aniSet.start();
    }
    private void spread2Start(){
        ObjectAnimator aniX = ObjectAnimator.ofFloat(btn2, "TranslationX", btn2X);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(btn2, "TranslationY", btn2Y);
        ObjectAnimator rott = ObjectAnimator.ofFloat(btn2, "Rotation",rot);
        rott.setDuration(2000);
        aniX.setDuration(1000);
        aniY.setDuration(1000);
        rott.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, rott);
        aniSet.start();
    }
    private void spread3Start(){
        ObjectAnimator aniX = ObjectAnimator.ofFloat(btn3, "TranslationX", btn3X);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(btn3, "TranslationY", btn3Y);
        ObjectAnimator rott = ObjectAnimator.ofFloat(btn3, "Rotation",rot);
        rott.setDuration(2000);
        aniX.setDuration(1000);
        aniY.setDuration(1000);
        rott.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, rott);
        aniSet.start();
    }

    private void spread4Start(){
        ObjectAnimator aniX = ObjectAnimator.ofFloat(btn4, "TranslationX", btn4X);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(btn4, "TranslationY", btn4Y);
        ObjectAnimator rott = ObjectAnimator.ofFloat(btn4, "Rotation",rot);
        rott.setDuration(2000);
        aniX.setDuration(1000);
        aniY.setDuration(1000);
        rott.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, rott);
        aniSet.start();
    }

    private void come1Start(){
        ObjectAnimator aniX = ObjectAnimator.ofFloat(btn1, "TranslationX", btn1X);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(btn1, "TranslationY", btn1Y);
        ObjectAnimator rott = ObjectAnimator.ofFloat(btn1, "Rotation",rot2);
        rott.setDuration(2000);
        aniX.setDuration(1000);
        aniY.setDuration(1000);
        rott.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, rott);
        aniSet.start();
    }
    private void come2Start(){
        ObjectAnimator aniX = ObjectAnimator.ofFloat(btn2, "TranslationX", btn2X);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(btn2, "TranslationY", btn2Y);
        ObjectAnimator rott = ObjectAnimator.ofFloat(btn2, "Rotation",rot2);
        rott.setDuration(2000);
        aniX.setDuration(1000);
        aniY.setDuration(1000);
        rott.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, rott);
        aniSet.start();
    }
    private void come3Start(){
        ObjectAnimator aniX = ObjectAnimator.ofFloat(btn3, "TranslationX", btn3X);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(btn3, "TranslationY", btn3Y);
        ObjectAnimator rott = ObjectAnimator.ofFloat(btn3, "Rotation",rot2);
        rott.setDuration(2000);
        aniX.setDuration(1000);
        aniY.setDuration(1000);
        rott.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, rott);
        aniSet.start();
    }
    private void come4Start(){
        ObjectAnimator aniX = ObjectAnimator.ofFloat(btn4, "TranslationX", btn4X);
        ObjectAnimator aniY = ObjectAnimator.ofFloat(btn4, "TranslationY", btn4Y);
        ObjectAnimator rott = ObjectAnimator.ofFloat(btn4, "Rotation",rot2);
        rott.setDuration(2000);
        aniX.setDuration(1000);
        aniY.setDuration(1000);
        rott.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, rott);
        aniSet.start();
    }
}
