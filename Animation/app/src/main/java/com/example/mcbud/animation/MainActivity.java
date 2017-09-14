package com.example.mcbud.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMove, btnRotate, btnScale, btnAlpha, object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView(){
        btnMove = (Button)findViewById(R.id.btnMove);
        btnRotate = (Button)findViewById(R.id.btnRotate);
        btnScale = (Button)findViewById(R.id.btnScale);
        btnAlpha = (Button)findViewById(R.id.btnAlpha);
        object = (Button)findViewById(R.id.object);
    }

    private void initListener(){
        btnMove.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnAlpha.setOnClickListener(this);
        object.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnMove: move(); break;
            case R.id.btnRotate: rotate(); break;
            case R.id.btnScale: scale(); break;
            case R.id.btnAlpha: alpha(); break;
            case R.id.object:
                Intent intent = new Intent(this, PropAniActivity.class);
                startActivity(intent);
        }
    }

    private void move(){
        // 뷰 애니메이션 실행 하기
        // 1. 애니메이션 xml을 먼저 정의한다.
        // 2, AnimationUtil로 정의된 애니메이션을 로드(메모리에 올린다)
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);
        // 3. 로드된 애니메이션을 실제 위젯에 적용한다.
        object.startAnimation(animation);
    }
    private void rotate(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        object.startAnimation(animation);
    }
    private void scale(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        object.startAnimation(animation);
    }
    private void alpha(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        object.startAnimation(animation);
    }
}
