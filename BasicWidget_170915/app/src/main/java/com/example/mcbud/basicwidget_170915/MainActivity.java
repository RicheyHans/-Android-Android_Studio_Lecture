package com.example.mcbud.basicwidget_170915;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textResult, textSeekbarResult;
    ToggleButton btnToggle;
    CheckBox checkDog, checkPig, checkCow;
    RadioGroup radioGroup;
    RadioButton radioRed, radioGreen, radioBlue;
    ProgressBar progressBar;
    Switch mSwitch;
    SeekBar seekBar;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnToggle.setOnCheckedChangeListener(checkedChangeListener);
        mSwitch.setOnCheckedChangeListener(checkedChangeListener);

        checkDog.setOnCheckedChangeListener(checkboxListner);
        checkPig.setOnCheckedChangeListener(checkboxListner);
        checkCow.setOnCheckedChangeListener(checkboxListner);

        radioGroup.setOnCheckedChangeListener(radioListener);

        progressBar.setVisibility(View.INVISIBLE);  // INVISIBLE -- 화면에 안보이는데 자리는 차지하고 있다
                                                        // VISIBLE   -- 현재 화면에 보이는 상태
                                                        // GONE      -- 화면에서 사라진 상태

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSeekbarResult.setText(progress+"");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // 라디오그룹 리스너
    RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int radio_id) {
            switch(radio_id){
                case R.id.radioRed:
                    textResult.setText("빨간불이 켜졌습니다.");
                    break;
                case R.id.radioGreen:
                    textResult.setText("녹색불이 켜졌습니다.");
                    break;
                case R.id.radioBlue:
                    textResult.setText("파란불이 켜졌습니다.");
                    break;
                case R.id.radioSpinner:
                    Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    // 체크박스 리스너
    ArrayList<String> checkedList = new ArrayList<>();
    CompoundButton.OnCheckedChangeListener checkboxListner
            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            // 체크박스 처리
            switch(compoundButton.getId()){
                case R.id.checkDog:
                    if(b){
                        checkedList.add("개");
                    }else{
                        checkedList.remove("개");
                    }
                    break;
                case R.id.checkPig:
                    if(b){
                        checkedList.add("돼지");
                    }else{
                        checkedList.remove("돼지");
                    }
                    break;
                case R.id.checkCow:
                    if(b){
                        checkedList.add("소");
                    }else{
                        checkedList.remove("소");
                    }
                    break;
            }

            String checkedResult = "";
            for(String item : checkedList){
                checkedResult += item + " ";
            }

            textResult.setText(checkedResult + "(이)가 체크되었습니다.");
        }
    };

    CompoundButton.OnCheckedChangeListener checkedChangeListener
            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
            // 토글, 스위치 처리
            switch(compoundButton.getId()){
                case R.id.btnToggle:
                    if(check){
                        textResult.setText("토글버튼이 켜졌습니다");
                    }else{
                        textResult.setText("토글버튼이 꺼졌습니다");
                    }
                    break;
                case R.id.mSwitch:
                    if(check){
                        progressBar.setVisibility(View.VISIBLE);
                    }else{
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    };

    private void initView(){
        textResult = (TextView)findViewById(R.id.textResult);
        textSeekbarResult = (TextView)findViewById((R.id.textSeekbarResult));
        btnToggle = (ToggleButton)findViewById(R.id.btnToggle);
        checkDog = (CheckBox)findViewById(R.id.checkDog);
        checkPig = (CheckBox)findViewById(R.id.checkPig);
        checkCow = (CheckBox)findViewById(R.id.checkCow);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioRed = (RadioButton)findViewById(R.id.radioRed);
        radioGreen = (RadioButton)findViewById(R.id.radioGreen);
        radioBlue = (RadioButton)findViewById(R.id.radioBlue);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        mSwitch = (Switch)findViewById(R.id.mSwitch);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
    }

}
