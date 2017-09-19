package com.example.mcbud.basiclist_170919;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // intent를 통해 넘어온 값 추출
        Intent intent = getIntent();    // startActivity를 통해 넘어온 intent를 꺼낸다.

        /*
        // 인텐트에서 키 밸류의 번들을 추출하고
        // 그 번들에서 목적 값을 추출한다.
        Bundle bundle = intent.getExtras();
        String result = bundle.getString("valueKey");
        */

        // intent에서 번들을 따로 추출하지 않고 값을 추출하는 방법
        String result = intent.getStringExtra("valueKey");

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(result);

        // putExtra를 하면 키와 밸류가 함께 들어간다. 이 경우, entry와 비슷한 개념인 buundle로
        // 키와 밸류가 들어간다. 그래서 번들을 먼저 꺼내는 형태가 된다.
    }
}
