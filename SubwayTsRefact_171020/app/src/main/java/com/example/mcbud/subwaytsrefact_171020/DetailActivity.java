package com.example.mcbud.subwaytsrefact_171020;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.mcbud.subwaytsrefact_171020.Model.JsonClass;
import com.example.mcbud.subwaytsrefact_171020.Model.RealtimeArrivalList;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

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
        System.out.println("init실행됨!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    private void load(){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                String str = Remote.getData("http://swopenapi.seoul.go.kr/api/subway/66795758776d6362373442475a666e/json/realtimeStationArrival/1/20/"+stnText);
                System.out.println(stnText);
                System.out.println(str);
                return str;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                data = parse(jsonString);
                setList();
            }
        }.execute();
    }

    private List<RealtimeArrivalList> parse(String string){
        Gson gson = new Gson();
        JsonClass jsonClass = gson.fromJson(string, JsonClass.class);

        RealtimeArrivalList[] realtimeArrivalLists = jsonClass.getRealtimeArrivalList();

        List<RealtimeArrivalList> result = Arrays.asList(realtimeArrivalLists);

        return result;
    }

    List<RealtimeArrivalList> data;
    private void setList(){
        UpAdapter upAdapter = new UpAdapter(data, this);
        recyclerViewUp.setAdapter(upAdapter);
        recyclerViewUp.setLayoutManager(new LinearLayoutManager(this));
    }

}
