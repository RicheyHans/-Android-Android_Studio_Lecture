package com.example.mcbud.customview;

import android.graphics.Color;
import android.graphics.Path;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class DrawActivity extends AppCompatActivity {


    FrameLayout stage;
    SeekBar seekBar;
    RadioGroup radioColor;
    DrawView draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        radioColor = (RadioGroup) findViewById(R.id.radioColor);
        seekBar = (SeekBar) findViewById(R.id.seekLine);
        stage = (FrameLayout)findViewById(R.id.stage);
        draw = new DrawView(this);

        stage.addView(draw);

        // 라디오 버튼이 선택되면 draw의 paint 색상을 바꿔준다.
        radioColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.radioBlack:
                        draw.setResources(Color.BLACK, seekBar.getProgress());
                        break;
                    case R.id.radioCyan:
                        draw.setResources(Color.CYAN, seekBar.getProgress());
                        break;
                    case R.id.radioYellow:
                        draw.setResources(Color.YELLOW, seekBar.getProgress());
                        break;
                    case R.id.radioMagenta:
                        draw.setResources(Color.MAGENTA, seekBar.getProgress());
                        break;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (radioColor.getCheckedRadioButtonId()){
                    case R.id.radioBlack:
                        draw.setResources(Color.BLACK, progress);
                        break;
                    case R.id.radioCyan:
                        draw.setResources(Color.CYAN, progress);
                        break;
                    case R.id.radioYellow:
                        draw.setResources(Color.YELLOW, progress);
                        break;
                    case R.id.radioMagenta:
                        draw.setResources(Color.MAGENTA, progress);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
