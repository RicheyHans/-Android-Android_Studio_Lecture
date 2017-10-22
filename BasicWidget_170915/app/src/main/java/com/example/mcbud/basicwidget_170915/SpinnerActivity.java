package com.example.mcbud.basicwidget_170915;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {
    // 1. 스피너에 입력될 데이터의 정의 / 스트링배열,
    String[] data = {"월", "화", "수","목", "금", "토","일"};
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // 2. 스피너와 데이터를 연결하는 아답터를 정의
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                data);

        // 3. 아답터와 스피너(리스트)를 연결
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        textView = (TextView)findViewById(R.id.textResult);

        // 4. 스피너에 리스너를 달아준다
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {      // position : 데이터의 인덱스와 동일하다.
                    String selectedValue = data[position];
                    textView.setText(selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {     // 닫혔을 때 출력

            }
        });
    }
}
