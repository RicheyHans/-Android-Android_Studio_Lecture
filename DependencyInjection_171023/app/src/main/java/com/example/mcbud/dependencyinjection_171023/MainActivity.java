package com.example.mcbud.dependencyinjection_171023;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.w3c.dom.Text;

/*
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
*/

@Fullscreen
@EActivity(R.layout.activity_main)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class MainActivity extends AppCompatActivity {

    @ViewById
    TextView text;

    @AfterViews
    public void init() {
        text.setText("Hello AA!!");
        run();
    }

    @Background
    public void run(){
        // sub thread에서 실행된다.
        for(int i = 0; i < 10; i++){
            main(i);    // sub thread는 UI접근하지 못하므로 하단 main함수로 접근한다.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @UiThread
    public void main(int i){
        // 이 코드는 main thread에서 실행된다.
        text.setText(i+"");
    }


    /*
    @BindView(R.id.btn)
    Button btn_0;
    Button btn_1;
    ...
    버튼이 많을 경우 List처리 가능
    @BindViews({R.id.btn_0, R.id.btn_1, R.id.btn_2....})
    */

    /*
    @BindView(R.id.text)
    TextView text;
    */

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text.setText("Hello AA!");

        // DI 사용
        ButterKnife.bind(this);
        text.setText("ButterKnife Test");
        // DI를 실행해 findViewById 를 실행한 후, 이것을 전역변수 text에 넣는 과정 실행했었음.
        // 그러나 annotation을 사용하게 되면서 실행 속도를 개선하게 됨
    }
    */

    /*
    클릭 시 함수 실행
    @OnClick(R.id.btn)
    public void click(){

    }
    */
}
