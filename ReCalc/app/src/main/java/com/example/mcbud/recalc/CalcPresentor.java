package com.example.mcbud.recalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CalcPresentor extends AppCompatActivity implements ICalc {

    CalcView calcView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
