package com.example.mcbud.musicplayer_prototype_171013.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcbud on 2017-10-13.
 */

public class Music {
    private static Music music = null;
    public List<Item> data = new ArrayList();

    // new 생성자 사용 방지를 위한 private 생성자
    private Music(){

    }

    public static Music getInstance(){
        if(music == null){
            music = new Music();
        }
        return music;
    }

    // 음원 데이터 로드 메서드
    public void load(Context context){
        ContentResolver resolver = context.getContentResolver();
        // 1. 테이블 명 정의
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // 2. 불러올 컬럼명 정의
        String[] proj = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST
        };
        // 3. 쿼리
        Cursor cursor = resolver.query(uri, proj, null, null, proj[2]+" ASC");
        // 4. 쿼리 결과가 담긴 cursor를 통해 음원들의 데이터를 꺼낸다
        data.clear();
        if(cursor != null){
            while(cursor.moveToNext()){
                Item item = new Item();

                item.id = getValue(cursor, proj[0]);
                item.albumId = getValue(cursor, proj[1]);
                item.artist = getValue(cursor, proj[2]);
                item.title = getValue(cursor, proj[3]);

                item.musicUri = makeMusicUri(item.id);
                item.albumUri = makeAlbumUri(item.albumId);

                data.add(item);
            }
            cursor.close();
        }

    }

    private String getValue(Cursor cursor, String name){
        int index = cursor.getColumnIndex(name);
        return cursor.getString(index);
    }

    private Uri makeMusicUri(String musicId){
        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        return Uri.withAppendedPath(contentUri, musicId);
    }

    private Uri makeAlbumUri(String albumId){
        String contentUri = "content://media/external/audio/albumart/";
        return Uri.parse(contentUri + albumId);
    }

    // 실제 음원 데이터
    public class Item{
        public String id;       // 음원 id
        public String albumId;  // 앨범 아이디
        public String artist;
        public String title;

        public Uri musicUri;    // 음원의 주소값
        public Uri albumUri;    // 앨범아트 주소값

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Item){
                return id.equals( ((Item)obj).id );
            }
            return false;
        }
    }
}


