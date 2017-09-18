package com.example.mcbud.recalc;

/**
 * Created by mcbud on 2017-09-15.
 */

public interface ICalcView {

    // 문자를 입력 받는다
    void append(String factor);

    // 결과를 출력한다
    String showresult(String result);
    // 식을 분리하는 메서드

    // 입력된 값을 넘겨주는 메서드
    String transferSentece();

}
