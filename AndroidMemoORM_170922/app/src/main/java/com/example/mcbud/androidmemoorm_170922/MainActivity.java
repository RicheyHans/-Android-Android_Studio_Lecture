package com.example.mcbud.androidmemoorm_170922;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mcbud.androidmemoorm_170922.dao.PicNoteDAO;
import com.example.mcbud.androidmemoorm_170922.model.PicNote;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ArrayList<>();
        init();
    }

    // layout에서 메서드를 직접 호출한다.
    public void openDraw(View view){
        Intent intent = new Intent(this, DrawActivity.class);
        startActivity(intent);
    }

    private void init(){
        PicNoteDAO dao = new PicNoteDAO(this);

        //리사이클러 뷰를 사용한 목록 만들기
        // 0. 화면을 정의한다.
        // 1. 데이터를 정의한다.
        /*
        for(int i = 0; i < 1000; i++){
            PicNote picNote = new PicNote();
            picNote.setTitle("안녕하세요"+i);
            picNote.setDatetime(System.currentTimeMillis());
            // DB에 넣은 후
            dao.create(picNote);
        }*/
        // DB에서 읽어온다.
        List<PicNote> data = dao.readAll();
        // 2. Adapter를 재정의한다(상속 받는다) - 했음
        // 3. 재정의한 Adapter를 생성하면서 데이터를 담는다.
        CustomAdapter adapter = new CustomAdapter();
        adapter.setData(data);
        // 4. Adapter와 RecyclerView 컨테이너를 연결한다.
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        // 5. RecyclerView에 레이아웃 매니저를 생성
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 레이아웃 매니저의 종류
    }
}
