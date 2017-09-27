package com.example.mcbud.fragmentbasic_170928;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements ListFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }

    public void initFragment(){
        // 1. 프래그먼트 매니저 호출
        FragmentManager manager = getSupportFragmentManager();
        // 2. 트랜젝션 처리자 호출
        FragmentTransaction transaction = manager.beginTransaction();     // 프래그먼트 처리 위한 트랜잭션 시작
        // 3. 액티비티 레이아웃에 프래그먼트를 부착하고
        Log.d("Activity", "==============beforeAdd()");
        transaction.add(R.id.container, new ListFragment());
        Log.d("Activity", "==============afterAdd()");
        // 4. commit 해서 완료
        transaction.commit();
        Log.d("Activity", "==============beforeCommit()");
    }

    // Detail 프래그먼트를 덮어씌운다
    @Override
    public void goDetail(){
        Log.d("Activity", "==============beforeAdd()");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new DetailFragment())
                .addToBackStack(null)
                .commit();
        Log.d("Activity", "==============beforeCommit()");
    }

    @Override
    protected void onStart() {
        Log.d("Activity", "==============onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("Activity", "==============onResume()");
        super.onResume();
    }

}
