package com.example.mcbud.subwaytsrefact_171020;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by mcbud on 2017-10-24.
 */

public class DetailActivity extends AppCompatActivity {
    private TextView textStnNm;
    private String stnText;
    private RecyclerView recyclerViewUp;
    private RecyclerView recyclerViewDown;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        load();
    }

    private void init(){
        // 지하철 역 명 객체화
        textStnNm = (TextView) findViewById(R.id.textStnName);

        // MainActivity 에서 전달된 Intent 정보 획득
        Intent intent = getIntent();
        stnText = intent.getStringExtra("textStn");
        textStnNm.setText(stnText);

        // 상~하행선 리사클러뷰 객체화
        recyclerViewUp = (RecyclerView) findViewById(R.id.recyclerViewUp);
        recyclerViewDown = (RecyclerView) findViewById(R.id.recyclerViewDown);
    }

    private void load(){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();
    }
}
