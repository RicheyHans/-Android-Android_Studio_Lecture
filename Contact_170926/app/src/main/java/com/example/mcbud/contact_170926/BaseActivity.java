package com.example.mcbud.contact_170926;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcbud on 2017-09-26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final int REQ_CODE = 999;
    private static final String permissions[] = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.CALL_PHONE
    };

    public abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 앱 SDK 호환성 체크
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        } else {
            init();
        }
    }

    private void checkPermission(){
        List<String> requires = new ArrayList<>();
        for(String permission : permissions){
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                requires.add(permission);
            }
        }
        // 승인이 안 된 권한이 있을 경우에 권한 승인 요청
        if(requires.size() > 0){
            String[] perms = requires.toArray(new String[requires.size()]);
            requestPermissions(perms, REQ_CODE);
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 권한 승인 여부 체크
        if(requestCode == REQ_CODE){
            boolean granted = true;
            for(int grant : grantResults){
                if(grant != PackageManager.PERMISSION_GRANTED){
                    granted = false;
                    break;
                }
            }
            if(granted){
                init();
            }
        }
    }
}
