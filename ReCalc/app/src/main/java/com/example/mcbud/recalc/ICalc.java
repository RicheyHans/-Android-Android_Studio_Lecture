package com.example.mcbud.recalc;

import java.util.List;

/**
 * Created by mcbud on 2017-09-15.
 */

public interface ICalc {

    // 식을 분리하는 메서드
    List<String> splitSentence(String sentence);

    // 식을 계산하는 메서드
    double calc();


}
