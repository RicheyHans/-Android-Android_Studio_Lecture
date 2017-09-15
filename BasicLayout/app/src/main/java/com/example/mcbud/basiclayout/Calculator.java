package com.example.mcbud.basiclayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Calculator extends AppCompatActivity implements View.OnClickListener {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlus, btnMinus, btnMultiply, btnDivede, btnC, btnResult;
    TextView textPreview, textResult;
    Double result = 0.0;
    private ConstraintLayout stage;

    //int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        /*
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.widthPixels/4;
        */
        initView();
        initListener();
    }

    private void initView(){
        // 버튼
        btn0 = (Button)findViewById(R.id.btn_0);
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);
        btn3 = (Button)findViewById(R.id.btn_3);
        btn4 = (Button)findViewById(R.id.btn_4);
        btn5 = (Button)findViewById(R.id.btn_5);
        btn6 = (Button)findViewById(R.id.btn_6);
        btn7 = (Button)findViewById(R.id.btn_7);
        btn8 = (Button)findViewById(R.id.btn_8);
        btn9 = (Button)findViewById(R.id.btn_9);
        btnPlus = (Button)findViewById(R.id.btn_plus);
        btnMinus = (Button)findViewById(R.id.btn_minus);
        btnMultiply = (Button)findViewById(R.id.btn_multiply);
        btnDivede = (Button)findViewById(R.id.btn_divide);
        btnC = (Button)findViewById(R.id.btn_c);
        btnResult = (Button)findViewById(R.id.btn_result);
        // 텍스트 창
        textPreview = (TextView)findViewById(R.id.textPreview);
        textResult = (TextView)findViewById(R.id.textResult);
        stage = (ConstraintLayout)findViewById(R.id.stage);
        //btn0.setHeight(height);
    }
    private void initListener(){
        // 리스너 설정. 즉 onClick(View v)의 선언/정의된 객체를 파라미터로 받는다.
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivede.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            switch (v.getId()) {
                case R.id.btn_0:
                    append("0");
                    buttonEffect(v);
                    break;
                case R.id.btn_1:
                    append("1");
                    buttonEffect(v);
                    break;
                case R.id.btn_2:
                    append("2");
                    buttonEffect(v);
                    break;
                case R.id.btn_3:
                    append("3");
                    buttonEffect(v);
                    break;
                case R.id.btn_4:
                    append("4");
                    buttonEffect(v);
                    break;
                case R.id.btn_5:
                    append("5");
                    buttonEffect(v);
                    break;
                case R.id.btn_6:
                    append("6");
                    buttonEffect(v);
                    break;
                case R.id.btn_7:
                    append("7");
                    buttonEffect(v);
                    break;
                case R.id.btn_8:
                    append("8");
                    buttonEffect(v);
                    break;
                case R.id.btn_9:
                    append("9");
                    buttonEffect(v);
                    break;
                case R.id.btn_plus:
                    append("+");
                    buttonEffect(v);
                    break;
                case R.id.btn_minus:
                    append("-");
                    buttonEffect(v);
                    break;
                case R.id.btn_multiply:
                    append("*");
                    buttonEffect(v);
                    break;
                case R.id.btn_divide:
                    append("/");
                    buttonEffect(v);
                    break;
                case R.id.btn_c:
                    textPreview.setText("0");
                    textResult.setText("0");
                    result = 0.0;
                    buttonEffect(v);
                    break;
                case R.id.btn_result:
                    result = calc();
                    buttonEffect(v);
                    textResult.setText("" + result);
                    textPreview.setText("0");
                    result = 0.0;
                    break;
            }
        }
    }

    private double calc() {
        String inputText = textPreview.getText() + "";
        String[] splittedText = inputText.split("(?<=[*/+-])|(?=[*/+-])");
        List<String> splitList = new ArrayList<>(Arrays.asList(splittedText));
        List<Double> operList = new ArrayList<>();
        int index = 0;

        System.out.println("입력받은 텍스트 첫 스플릿: " + splitList);

        for (int i = 0; i < splitList.size(); i++) {
            if (splitList.get(i).equals("*") || splitList.get(i).equals("/")) {
                if (splitList.get(i).equals("*")) {
                    double tempResult = (Double.parseDouble(splitList.get(i - 1))) * (Double.parseDouble(splitList.get(i + 1)));
                    splitList.remove(i - 1);
                    splitList.remove(i - 1);
                    splitList.remove(i - 1);
                    splitList.add(i - 1, String.valueOf(tempResult));
                    //break;
                } else {
                    double tempResult = (Double.parseDouble(splitList.get(i - 1))) / (Double.parseDouble(splitList.get(i + 1)));
                    splitList.remove(i - 1);
                    splitList.remove(i - 1);
                    splitList.remove(i - 1);
                    splitList.add(i - 1, String.valueOf(tempResult));
                    //break;
                }
                i--;
            }
        }
        for (String item : splitList) {
            if (item.equals("+") || item.equals("-")) {
                index++;
                break;
            }
            System.out.println(item);
        }
        if (index == 0) {
            operList.add(Double.parseDouble(splitList.get(0)));
        }
        for (int j = 0; j < splitList.size(); j++) {
            if (splitList.get(j).equals("+") || splitList.get(j).equals("-")) {
                if (splitList.get(j).equals("+")) {
                    operList.add(Double.parseDouble(splitList.get(j - 1)) + Double.parseDouble(splitList.get(j + 1)));
                    double imsi = Double.parseDouble(splitList.get(j - 1)) + Double.parseDouble(splitList.get(j + 1));
                    splitList.remove(j - 1);
                    splitList.remove(j - 1);
                    splitList.remove(j - 1);
                    splitList.add(j - 1, imsi + "");

                    System.out.println("템프리절트가 spliList 들어간 값: " + splitList);
                } else if (splitList.get(j).equals("-")) {
                    System.out.println("템프리절트가 spliList 들어간 값: " + splitList);
                    double imsi = Double.parseDouble(splitList.get(j - 1)) - Double.parseDouble(splitList.get(j + 1));
                    operList.add(Double.parseDouble(splitList.get(j - 1)) - Double.parseDouble(splitList.get(j + 1)));
                    splitList.remove(j - 1);
                    splitList.remove(j - 1);
                    splitList.remove(j - 1);
                    splitList.add(j - 1, imsi + "");


                    System.out.println("템프리절트가 spliList 들어간 값: " + splitList);
                }
                j--;
            }

        }
//        if(splitList.size() != 0) {
//        	for(int k = 0; k < splitList.size(); k++) {
//        		operList.add(k, Double.parseDouble(splitList.get(k)));
//        	}
//        }
//
        System.out.println("스플릿에 남아있을 때 옮겨지는지 테스트: " + operList);

        for (Double item : operList) {
            result = result + item;
        }
        System.out.println("최종 답 " + result);
        return result;
    }


        // System.out.println("최종 답 "+result);



    private void append(String str) {
        // 1. 처음에 연산자가 오면 예외처리
        if (textPreview.getText().toString().equals("0")) {
            textPreview.setText("");
            if (str.equals("+") || str.equals("-")
                    || str.equals("*") || str.equals("/")) {
                Toast.makeText(this
                        , "연산자를 먼저 입력할 수 없습니다!"
                        , Toast.LENGTH_SHORT).show();
                return;
            }
        }
        // 2. 연산자를 연속해서 입력하면 예외처리
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
            String[] splittedTextOper = textPreview.getText().toString().split("(?<=[*/+-])|(?=[*/+-])");
            String operCheck = splittedTextOper[splittedTextOper.length - 1];
            if ((operCheck.equals("+")) || (operCheck.equals("-")) || (operCheck.equals("*")) || (operCheck.equals("/"))) {
                Toast.makeText(this, "연산자를 잘못 입력했습니다!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        textPreview.append(str);
    }

    private void buttonEffect(View v){
        Button original = (Button)v;
        final Button dummy = new Button(Calculator.this);



        dummy.setText(original.getText().toString());  // 텍스트 카피
        dummy.setWidth(original.getWidth());
        dummy.setHeight(original.getHeight());
        dummy.setBackgroundColor(Color.RED);

        LinearLayout parent = (LinearLayout)original.getParent();

        dummy.setX( original.getX() + parent.getX());
        dummy.setY( original.getY() + parent.getY());

        stage.addView(dummy);

        ObjectAnimator aniY = ObjectAnimator.ofFloat(
                dummy, "y", textPreview.getY()
        );
        ObjectAnimator aniX = ObjectAnimator.ofFloat(
                dummy, "x", textPreview.getX()
        );
        ObjectAnimator aniR = ObjectAnimator.ofFloat(
                dummy, "rotation", 720
        );

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX, aniR);
        aniSet.setDuration(1000);

        // 애니메이션 종료 체크를 위한 리스너
        aniSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                stage.removeView(dummy);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        aniSet.start();
    }
}
