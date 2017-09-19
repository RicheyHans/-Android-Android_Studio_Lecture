package com.example.mcbud.androidmemo_170919;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Button;

import com.example.mcbud.androidmemo_170919.domain.WriteActivity;

public class ListActivity extends AppCompatActivity {

    private ListViewCompat list;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        initListenr();
    }

    private void initView(){

    }
    private void initListenr(){
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });
    }
}
