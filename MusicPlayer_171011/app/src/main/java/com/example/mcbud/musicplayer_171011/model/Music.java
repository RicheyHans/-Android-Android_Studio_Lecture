package com.example.mcbud.musicplayer_171011.model;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcbud on 2017-10-11.
 */

// 리스트에 뿌려질 데이터의 최소 단위
public class Music {

    private static Music music = null;

    // 음악을 로드해서 담아두는 데이터
    public List<Item> data = new ArrayList<>();

    private Music(){ }  // new 사용 방지를 위해 생성자를 private화

    public static Music getInstance(){
        if(music == null){
            music = new Music();
        }
        return music;
    }

    // 음악 데이터를 불러오는 메서드
    public void load(Context context){
        ContentResolver resolver = context.getContentResolver();
        // 1. 테이블 명 정의
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // 2. 불러올 컬럼 명 정의
        String[] proj = { MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
        };

        // 3. 쿼리
        Cursor cursor = resolver.query(uri, proj, null, null, proj[2]+" ASC");       // 오름차순으로 proj[2]인 TITLE 기준으로 order by됨
        // 4. 쿼리 결과가 담긴 커서를 통해 데이터 꺼내기
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
        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;       // 저장되어 있는 문자열 로드
        return Uri.withAppendedPath(contentUri, musicId);      // 두 문자열을 합쳐 음원의 URI를 리턴
    }

    private Uri makeAlbumUri(String albumId){
        String contentUri = "content://media/external/audio/albumart/";
        return Uri.parse(contentUri + albumId);
    }

    // 실제 뮤직 데이터 클래스
    public class Item {

        public String id;               // 음악 id
        public String albumId;          // 앨범 id(앨범아트는 앨범의 리소스)
        public String artist;
        public String title;

        // id는 숫자로 되어 있으며, 이를 실제 로드할 때는 URI타입으로 변환해 사용
        public Uri musicUri;    // 음악 URI(음악 주소)
        public Uri albumUri;    // 앨범 이미지 URI(주소)
    }
}
