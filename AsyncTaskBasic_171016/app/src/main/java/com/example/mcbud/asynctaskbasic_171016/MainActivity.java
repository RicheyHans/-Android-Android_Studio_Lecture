package com.example.mcbud.asynctaskbasic_171016;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.net.HttpURLConnection;

/*
   Async Task는 세 개의 기본 함수를 지원하는 Thread라고 보면 된다.

   1. onPreExecute : doInBackgournd() 함수가 실행되기 전에 실행됨
        : 선 처리. 프로그레스바를 돌려주는 등
   2. doInBackground 백그라운드(sub Thread)에서 코드를 실행하는 함수 >> 이 함수만 서브스레드에서 실행
        : ↓ onPostExecture는 doInBackground로부터 모든 데이터를 받을 수 있다.
   3. onPostExecture : doInBackgournd() 함수가 실행된 후에 실행되는 함수
        : 즉, 1/3은 UI를 컨트롤 할 수 있다.
 */

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        getServer("http://google.com");
    }

    private void getServer(String url){
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>(){
            // 1번 제네릭 : doInBackgournd 함수의 파라미터로 사용된다. Void면 DIB에 입력값을 없도록 하겠다는 의미
            // String으로 한다면 스레드의 start에 인자를 주는 것과 유사한 효과. 즉, "값을 바로 넣을 수 있는 스레드"

            // 2번 제네릭 : onProgressUpdate 함수의 파라미터로 사용
            // 주로 진행상태의 percent 값(int)으로 사용됨

            // 3번 제네릭 : doInBackground의 리턴값이면서 onPostExecute의 파라미터이다.

            @Override
            protected String doInBackground(String... args) {      // 다수의 String값 입력 가능
                // 사용할 때
                String param1 = args[0];
                String result = Remote.getData(param1);

                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                // super.onPostExecute(result);
                // String regex =  "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";

                textView.setText(result);
                // textView.setText(result.replaceAll(regex, ""));
            }
        };

        task.execute(url);
    }
}
