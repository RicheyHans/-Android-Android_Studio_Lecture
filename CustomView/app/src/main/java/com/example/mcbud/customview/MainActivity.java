package com.example.mcbud.customview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button btnAni;

    ConstraintLayout stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stage = (ConstraintLayout)findViewById(R.id.stage);

        CustomView cv = new CustomView(this);
        cv.setX(300);
        cv.setY(300);
        stage.addView(cv);

        findViewById(R.id.btnAni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this, "버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnDraw).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            Intent intent = new Intent(MainActivity.this, DrawActivity.class);
            startActivity(intent);
            }
        });
    }
}
