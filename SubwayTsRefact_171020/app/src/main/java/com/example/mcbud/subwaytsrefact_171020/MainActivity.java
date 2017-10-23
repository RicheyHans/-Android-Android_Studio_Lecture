package com.example.mcbud.subwaytsrefact_171020;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.mcbud.subwaytsrefact_171020.DAO.StationList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1st, btn2nd, btn3rd, btn4th, btn5th, btn6th, btn7th, btn8th;
    RecyclerView recyclerView;
    StationAdapter adapter;
    StationList sl = new StationList();
    String[] stationNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initListener();

    }

    public void init(){
        btn1st = (Button) findViewById(R.id.btn1st);
        btn2nd = (Button) findViewById(R.id.btn2nd);
        btn3rd = (Button) findViewById(R.id.btn3rd);
        btn4th = (Button) findViewById(R.id.btn4th);
        btn5th = (Button) findViewById(R.id.btn5th);
        btn6th = (Button) findViewById(R.id.btn6th);
        btn7th = (Button) findViewById(R.id.btn7th);
        btn8th = (Button) findViewById(R.id.btn8th);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    public void initListener(){
        btn1st.setOnClickListener(this);
        btn2nd.setOnClickListener(this);
        btn3rd.setOnClickListener(this);
        btn4th.setOnClickListener(this);
        btn5th.setOnClickListener(this);
        btn6th.setOnClickListener(this);
        btn7th.setOnClickListener(this);
        btn8th.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn1st:
                stationNum = sl.first.clone();
                initStnAdapter(stationNum);
                break;
            case R.id.btn2nd:
                stationNum = sl.second.clone();
                initStnAdapter(stationNum);
                break;
            case R.id.btn3rd:
                stationNum = sl.third.clone();
                initStnAdapter(stationNum);
                break;
            case R.id.btn4th:
                stationNum = sl.fourth.clone();
                initStnAdapter(stationNum);
                break;
            case R.id.btn5th:
                stationNum = sl.fifth.clone();
                initStnAdapter(stationNum);
                break;
            case R.id.btn6th:
                stationNum = sl.sixth.clone();
                initStnAdapter(stationNum);
                break;
            case R.id.btn7th:
                stationNum = sl.seventh.clone();
                initStnAdapter(stationNum);
                break;
            case R.id.btn8th:
                stationNum = sl.eighth.clone();
                initStnAdapter(stationNum);
                break;
        }
    }

    public void initStnAdapter(String[] str){
        adapter = new StationAdapter();
        adapter.setData(str);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
