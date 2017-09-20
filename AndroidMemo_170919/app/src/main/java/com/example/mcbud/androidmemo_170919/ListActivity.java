package com.example.mcbud.androidmemo_170919;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mcbud.androidmemo_170919.domain.Memo;
import com.example.mcbud.androidmemo_170919.domain.WriteActivity;
import com.example.mcbud.androidmemo_170919.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.mcbud.androidmemo_170919.R.id.textNo;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initView();
        initListener();
        init();
    }
    //Log.d("ListActivity","called init()");
    // 목록을 초기화
    private void init(){
        ArrayList<Memo> list = loadData();
        ListAdapter adapter = new ListAdapter(this, list);
        listView.setAdapter(adapter);
    }
    // 파일목록에서 파일을 하나씩 꺼낸후에
    // Memo 클래스로 변환한후 result 에 담는다
    private ArrayList<Memo> loadData(){
        ArrayList<Memo> result = new ArrayList<>();
        for(File item : getFilesDir().listFiles()){
            try {
                String text = FileUtil.read(this, item.getName());
                Memo memo = new Memo(text);
                result.add(memo);
            } catch (IOException e) {
                Toast.makeText(this, "에러:"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }

    private static final int WRITE_ACTIVITY = 12345;

    private void initListener() {
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, WriteActivity.class);
                startActivityForResult(intent, WRITE_ACTIVITY);
                // startActivity(intent);
            }
        });
    }

    // startActivityForResult 를 통해 호출된 액티비티가 종료되는 순간 호출되는 함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case WRITE_ACTIVITY:
                if(resultCode == RESULT_OK) {
                    init();
                }
                break;
        }
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        btnPost = (Button) findViewById(R.id.btnPost);
    }

}
