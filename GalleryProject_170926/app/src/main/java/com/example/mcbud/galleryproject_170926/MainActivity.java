package com.example.mcbud.galleryproject_170926;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends BaseActivity {

    private static final int REQ_GALLERY = 333;
    private static final int REQ_CAMERA = 222;

    ImageView imageView;

    @Override
    public void init(){
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);;

        // 카메라 스타트
    }


    // 저장된 파일의 경로를 갖는 컨텐츠 Uri.
    Uri fileUri = null;

    public void onCamera(View view){
        // 카메라 앱 실행 후 결과 이미지 저장하기
        // 1. Intent 생성
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 2. 호환성 처리 버전 체크
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            try {
                // 3, 실제 이미지 파일이 저장되는 파일 객체(단, 이 저장 객체가 Uri로 변경한 후 Uri가 안드로이드 시스템 DB에 저장되어야 함) < 빈 파일을 생성
                // 안드로이드 단말 연결 후 임의로 파일 복사해 넣으면, 파일과 함께 Uri가 자동으로 생성된다.
                // 파일은 공용으로 사용할 수 없으며 보안 상 Uri를 통해 사용해야 한다.
                // 3.1. 실제 파일이 저장되는 곳에 (쓰기)권한이 부여되어 있어야 한다.
                //      롤리팝부터는 File Provider를 선언해주어야만 한다.(Manifest)
                File photoFile = createFile();
                // 만일 촬영 후 갤러리에 저장되지 않을 때
                // refreshMedia(photoFile);
                fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".providr", photoFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, REQ_CAMERA);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {    // 롤리팝 미만일 경우
            startActivityForResult(intent, REQ_CAMERA);
        }
    }

    // 이미지를 저장하기 위해 쓰기 권한이 있는 빈 파일을 생성해두는 함수
    private File createFile() throws IOException{
        //임시 파일명을 생성
        String tempFileName = "Temp_"+System.currentTimeMillis();
        // 임시 파일 저장용 디렉토리 생성(CameraN디렉토리의 생성. 매니페스트 생성은 허용만 한 것이므로 실제 사용은 여기서)
        File tempDir = new File(Environment.getExternalStorageDirectory() + "/CameraN/");
        // 생성 체크
        if(!tempDir.exists()){
            tempDir.mkdirs();
        }
        // 실제 임시 파일을 생성
        File tempFile = File.createTempFile(
                tempFileName,  // 파일명
                ".jpg",  // 확장자
                tempDir   // 저장될 디렉토리
        );

        return tempFile;
    }

    public void onGallery(View view){
        // 새로운 액티비티 실행되며 이미지를 보유하고 있다.
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_GALLERY);
    }

    // 갤러리에서 나오지 않을 경우 미디어 파일 갱신
    private void refreshMedia(File file){
        String files[] = { file.getAbsolutePath()};
        MediaScannerConnection.scanFile(this,
                files,
                null,
                new MediaScannerConnection.OnScanCompletedListener(){
                    public void onScanCompleted(String path, Uri uri){
                        // Log.i("ExternalStorage", "Scanned " + path + ":");
                        // Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == RESULT_OK) {
                Uri imageUri = null;
                switch(requestCode){
                    case REQ_GALLERY:
                        if(data != null) {
                            imageUri = data.getData();      // 내 디스크에서 선택한 이미지가 전달된다.
                            imageView.setImageURI(imageUri);
                        }
                        break;
                case REQ_CAMERA:
                    // 롤리팝 미만 버전 체크
                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){   // 롤리팝보다 작을 경우
                        imageUri = data.getData();
                    } else {
                        imageUri = fileUri;
                    }
                    imageView.setImageURI(imageUri);
                    break;
            }
        }
    }
}
