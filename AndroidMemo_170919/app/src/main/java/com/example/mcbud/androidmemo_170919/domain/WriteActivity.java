package com.example.mcbud.androidmemo_170919.domain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.mcbud.androidmemo_170919.R;

public class WriteActivity extends AppCompatActivity {
    Button btnPost;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
    }
}
