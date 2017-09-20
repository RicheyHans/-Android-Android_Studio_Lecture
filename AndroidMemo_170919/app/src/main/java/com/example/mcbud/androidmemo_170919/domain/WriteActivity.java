package com.example.mcbud.androidmemo_170919.domain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mcbud.androidmemo_170919.R;
import com.example.mcbud.androidmemo_170919.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteActivity extends AppCompatActivity {
    private Button btnPost;
    private EditText editTitle;
    private EditText editAuthor;
    private EditText editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        initView();
        initListener();
    }

    /**
     * 내용을 파일에 작성
     * - 파일쓰기
     *   내부저장소 - Internal : 개별앱만 접근가능, 파일탐색기에서 보이지 않는다.
     *   외부저장소 - External : 모든앱이 접근가능 > 권한 필요
     */
    private Memo getMemoFromScreen(){
        Memo memo = new Memo();
        memo.setNo(1);
        memo.setTitle(editTitle.getText().toString());
        memo.setAuthor(editAuthor.getText().toString());
        memo.setContent(editContent.getText().toString());
        memo.setDatetime(System.currentTimeMillis());
        return memo;
    }

    private void write(Memo memo){
        try {
            String filename = System.currentTimeMillis() + ".txt";
            FileUtil.write(this, filename, memo.toString());

            setResult(RESULT_OK); // 나를 호출한 액티비티로 성공/실패 값을 넘겨준다.

            Toast.makeText(this, "등록되었습니다!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "에러:"+e.toString(), Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private void initListener() {
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Memo memo = getMemoFromScreen();
                write(memo);
            }
        });
    }

    private void initView() {
        btnPost = (Button) findViewById(R.id.btnPost);
        editTitle = (EditText) findViewById(R.id.editTitle);
        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editContent = (EditText) findViewById(R.id.editContent);
    }


}
