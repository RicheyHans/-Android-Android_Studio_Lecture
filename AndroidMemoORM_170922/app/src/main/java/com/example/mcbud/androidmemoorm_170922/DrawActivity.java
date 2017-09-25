package com.example.mcbud.androidmemoorm_170922;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.mcbud.androidmemoorm_170922.dao.PicNoteDAO;
import com.example.mcbud.androidmemoorm_170922.model.PicNote;
import com.example.mcbud.androidmemoorm_170922.util.FileUtil;

import java.io.File;
import java.io.IOException;

public class DrawActivity extends AppCompatActivity {

    MainActivity ma = new MainActivity();

    FrameLayout stage;
    RadioGroup radioColor;
    DrawView draw;
    PicNoteDAO dao;
    EditText editTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        dbInit();
        init();
    }

    private void dbInit(){
        dao = new PicNoteDAO(this);
    }

    private void init(){
        radioColor = (RadioGroup) findViewById(R.id.radioColor);
        editTitle = (EditText) findViewById(R.id.editTitle);
        stage = (FrameLayout) findViewById(R.id.stage);
        draw = new DrawView(this);
        stage.addView(draw);

        // 라디오 버튼이 선택되면 draw의 paint 색상의 바꿔준다
        radioColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch(id){
                    case R.id.radioBlack:
                        draw.setColor(Color.BLACK);
                        break;
                    case R.id.radioCyan:
                        draw.setColor(Color.CYAN);
                        break;
                    case R.id.radioMagenta:
                        draw.setColor(Color.MAGENTA);
                        break;
                    case R.id.radioYellow:
                        draw.setColor(Color.YELLOW);
                        break;
                }
            }
        });
    }

    /**
     * 그림을 그린 stage를 캡쳐
     * @param view
     */
    public void captureCanvas(View view){
        // 0. 드로잉 캐쉬를 먼저 지워준다.
        stage.destroyDrawingCache();
        // 1. 다시 만든다
        stage.buildDrawingCache();
        // 2. 레이아웃에서 그려진 내용을 bitmat 형태로 가져온다
        Bitmap bitmap = stage.getDrawingCache();
        // 이미지 이름을 생성
        String filename = System.currentTimeMillis()+".jpg";
        // ------파일명 중복 검사------
        // 1. 현재 파일명을 풀 경로로 File 객체로 변환
        String dir = getFilesDir().getAbsolutePath();
        File file = new File(dir + "/" + filename);
        int count = 0;      // 중복검사는 다회 시행한다.
        // 2. 반복문을 돌면서 파일명에 (숫자)를 더해서 만들어 준다.
        while(file.exists()){
            count++;
            filename = System.currentTimeMillis()+"("+count+").jpg";
            file = new File(dir + "/" + filename);
        }
        // ----------------------------

        try {
            // /data/data/패키지/files 밑에.....
            FileUtil.write(this, filename, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 데이터베이스에 경로도 저장하고
        PicNote picNote = new PicNote();

        picNote.setBitmap(filename);
        picNote.setTitle(editTitle.getText().toString());
        picNote.setDatetime(System.currentTimeMillis());
        dao.create(picNote);

        // 제일 중요한것......................
        bitmap.recycle(); // Native 에 다 썻다고 알려준다

        finish();
    }

}
