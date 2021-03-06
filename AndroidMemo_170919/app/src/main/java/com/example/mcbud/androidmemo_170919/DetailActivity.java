package com.example.mcbud.androidmemo_170919;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView textTitle;
    private TextView textDate;
    private TextView textAuthor;
    private TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        init();
    }

    public void init(){
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);

        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String content = intent.getStringExtra("content");
        String datetime = intent.getStringExtra("datetime");

        textTitle.setText(title);
        textDate.setText(datetime);
        textAuthor.setText(author);
        textContent.setText(content);
    }

    private void initView() {
        textTitle = (TextView) findViewById(R.id.textTitle);
        textDate = (TextView) findViewById(R.id.textDate);
        textAuthor = (TextView) findViewById(R.id.textAuthor);
        textContent = (TextView) findViewById(R.id.textContent);
    }

}
