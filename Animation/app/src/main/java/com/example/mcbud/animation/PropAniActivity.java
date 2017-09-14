package com.example.mcbud.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

public class PropAniActivity extends AppCompatActivity {

    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop_ani);
        btnGo = (Button)findViewById(R.id.btnGo);
    }

    // float y =0;
    public void move(View view){

        // 1. 대상을 정의한다
        // 2. 애니메이터 설정
        // y = y+100;
        ObjectAnimator aniY = ObjectAnimator.ofFloat(
                btnGo,  // 가.움직일 대상
                "translationY", // 나.애니메이션 속성(움직임)
                300 // 다. 속성(위치일경우 거리)
        );
        // 3.애니메이터 실행
        // ani.start();


        // 복합 애니메이션
        ObjectAnimator aniX = ObjectAnimator.ofFloat(
                btnGo,  // 가.움직일 대상
                "translationX", // 나.애니메이션 속성(움직임)
                300 // 다. 속성(위치일경우 거리)
        );
        // 애니메이션 셋에 담아 동시 실행
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX);
        aniSet.setDuration(3000);
        aniSet.setInterpolator(new OvershootInterpolator());   // 움직이는 시간동안 움직이는 정도를 설정. 속도 기울기를 조절
        // LinearInterpolator : 일정 속도 유지
        // 점점 빠르게 : accelerate_Interpolator
        // 점점 느리게 : decelerate_
        // 위 둘 동시에 : accelerate_decelerate_Interpolator
        // 시작위치에서 조금 뒤로 당겼다 이동 : anticipate_interpolator
        // 도착위치 조금 지나쳤다가 도착위치로 : overshoot_interpolator
        // 위 둘 동시 : aniticipate_overshoot_interpolator
        // 도착위치서 튕김 : bounce_interpolator
        aniSet.start();
    }

    public void goJoystick(View view){
        Intent intent = new Intent(this, JoystickActivity.class);
        startActivity(intent);

    }
}
