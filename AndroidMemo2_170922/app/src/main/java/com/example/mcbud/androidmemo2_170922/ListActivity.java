package com.example.mcbud.androidmemo2_170922;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcbud.androidmemo2_170922.domain.Memo;
import com.example.mcbud.androidmemo2_170922.domain.MemoDAO;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreate,btnRead,btnUpdate,btnDelete;
    EditText editTitle,editContent;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initViews();
        initListener();
        init();
    }

    MemoDAO dao = null;

    private void init(){
        dao = new MemoDAO(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreate:
                createAfterRead();
                break;
            case R.id.btnRead:
                read();
                break;
            case R.id.btnUpdate:
                break;
            case R.id.btnDelete:
                break;
        }
    }

    private void createAfterRead(){
        // Memo 데이터 화면에서 가져오기
        Memo memo = getMemoFromScreen();
        // 생성
        create(memo);
        // 결과 안내
        showInfo("입력되었습니다!!!");
        // 화면초기화
        resetScreen();
        // 목록 갱신
        read();
    }

    private Memo getMemoFromScreen(){
        // 1. 화면에서 입력된 값을 가져온다
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();
        // 2. Memo 객체를 하나 생성해서 값을 담는다
        return new Memo(title,content);
    }

    private void create(Memo memo){
        dao.create(memo);
    }

    private void resetScreen(){
        editTitle.setText("");
        editContent.setText("");
    }

    private void showInfo(String comment){
        Toast.makeText(this, comment, Toast.LENGTH_SHORT).show();
    }

    public void read(){
        // 0. 쿼리 있어야 되지만 생략 > DAO 에 이미 만들어 놨음
        // 1. DB 실행한 후 결과값을 받아서 처리

        ArrayList<Memo> data = dao.read();
        textResult.setText(" ");
        for(Memo memo :data){
            textResult.append(memo.toString());
        }
    }

    private void initListener() {
        btnCreate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void initViews(){
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editContent = (EditText) findViewById(R.id.editContent);
        textResult = (TextView) findViewById(R.id.textResult);
    }

    @Override
    protected void onDestroy() {
        // 사용한 Database 클래스는 닫아준다.
        if(dao != null){
            dao.close();
        }
        super.onDestroy();
    }

}
