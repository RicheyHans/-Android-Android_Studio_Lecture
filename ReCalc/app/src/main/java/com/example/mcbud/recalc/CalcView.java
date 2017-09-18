package com.example.mcbud.recalc;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by mcbud on 2017-09-15.
 */

public class CalcView implements ICalcView {

    ICalc calc;
    Activity activity;

    public CalcView(ICalc calc){
        this.calc = calc;       // 프레젠터로부터 컨텍스트 자원을 전달받는다.
        activity = (CalcPresentor)calc;
    }
}

@Override
public void append(String factor){
    String sentence = getSentence();
    int size = sentence.length();
    String lastChar = sentence.charAt(size-1)+"";

    if(size == 0){
        if(factor.equals("+")) || factor.equals("-") || factor.equals("/") || factor.equals("*") || factor.equals("0"){
            Toast.makeText(activity, "첫 입력값으로 연산자가 들어올 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}

