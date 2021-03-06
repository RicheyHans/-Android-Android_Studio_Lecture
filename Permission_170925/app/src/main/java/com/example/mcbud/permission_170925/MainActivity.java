package com.example.mcbud.permission_170925;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 안드로이드 권한 요청
 *
 * 가. 일반적인 권한 요청 -> Manifest요청
 */


public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // App 버전 체크(마시멜로보다 클 경우만 권한을 체크한다)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        } else{
            init();
        }

    }


    @TargetApi(Build.VERSION_CODES.M)      // 본 메서드는 마시멜로 이상에서만 사용한다고 가정함
    private void checkPermission(){
        //1. 권한 있는지 여부 확인
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            // 이미 승인 되었으면 진행 허가
            init();
        // 2. 권한이 없으면 권한을 요청
        } else {
            // 2.1 요청할 권한을 정의
            String permissions[] = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            // 2.2 권한 요청
            requestPermissions(permissions, REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 3.권한 승인 여부 체크
        switch(requestCode){
            case REQ_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    // 진행 허가
                    init();
                    break;
                 }
         }

    }


    private void init(){

        }

    }

