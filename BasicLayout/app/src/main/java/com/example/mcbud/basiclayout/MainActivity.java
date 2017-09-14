package com.example.mcbud.basiclayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btnFrame, btnLinear, btnGrid, btnRealtive, btnCalc;
    // 레이아웃에 정의된 위젯의 아이디로, 해당 객체를 변수에 저장해둔다.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 레이아웃xml 파일을 메모리에 로드
        setContentView(R.layout.activity_main);

        // 위에서 가져온 변수를 통해 사용한다.
        // 이벤트(클릭, 터치) 캐치가 필요할 경우 리스너를 달아준다.
        btnFrame = (Button)findViewById(R.id.btnFrame);
        btnLinear = (Button)findViewById(R.id.btnLinear);
        btnGrid = (Button)findViewById(R.id.btnGrid);
        btnRealtive = (Button)findViewById(R.id.btnRelative);
        btnCalc = (Button)findViewById(R.id.btnCalc);

        // 아래에 선언한 실행 객체를 리스너에 던져준다.
        btnFrame.setOnClickListener(onClickListener);
        btnLinear.setOnClickListener(onClickListener);
        btnGrid.setOnClickListener(onClickListener);
        btnRealtive.setOnClickListener(onClickListener);
        btnCalc.setOnClickListener(onClickListener);
    }
    // 리스너를 전역 변수로 선언할 수 있다.
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            // 액티비티(메이저 컴포넌트 실행)
            // 1. 인텐트(시스템에 전달되는 메시지 객체) 생성

            Intent intent = null;
            switch(v.getId()){
                case R.id.btnFrame:
                    intent = new Intent(MainActivity.this, FrameActivity.class);  // 지금 this를 넣으면 암거도 없는 인터페이스인 View.OnclickckListenr가 의미
                    startActivity(intent);
                    // "Frame액티비티를 실행";
                    break;
                case R.id.btnLinear:
                    intent = new Intent(MainActivity.this, LinearActivity.class);  // 지금 this를 넣으면 암거도 없는 인터페이스인 View.OnclickckListenr가 의미
                    startActivity(intent);
                    // "Linear액티비티를 실행";
                    break;
                case R.id.btnGrid:
                    intent = new Intent(MainActivity.this, GridActivity.class);  // 지금 this를 넣으면 암거도 없는 인터페이스인 View.OnclickckListenr가 의미
                    startActivity(intent);
                    // "Gird액비티비를 실행";
                    break;
                case R.id.btnRelative:
                    intent = new Intent(MainActivity.this, RelativeActivity.class);  // 지금 this를 넣으면 암거도 없는 인터페이스인 View.OnclickckListenr가 의미
                    startActivity(intent);
                    // "Relative액티비티를 실행";
                    break;
                case R.id.btnCalc:
                    intent = new Intent(MainActivity.this, Calculator.class);
                    startActivity(intent);
                    break;
            }
            startActivity(intent);
        }
    };
}
