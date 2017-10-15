package com.example.mcbud.musicplayer_prototype_171013;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcbud on 2017-10-13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final int REQ_CODE = 999;
    private static final String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 미디어 볼륨 조절 기능 활성화
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // 앱 버전 체크(호환성 처리)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        } else {
            init();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkPermission() {
        // 권한 승인이 되지 않은 내역
        List<String> requires = new ArrayList<>();

        // 권한 승인이 되지 않은 내역은 requires에 add
        for(String permission : permissions){
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                requires.add(permission);
            }
        }

        // 승인이 되지 않은 내역이 있을 경우에만 권한 승인 요청
        if(requires.size() > 0){
            String[] perms = requires.toArray(new String[requires.size()]);
            requestPermissions(perms, REQ_CODE);
        } else {
            init();
        }
    }

    // 유저가 권한 요청 팝업을 닫았을 경우 호출
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
