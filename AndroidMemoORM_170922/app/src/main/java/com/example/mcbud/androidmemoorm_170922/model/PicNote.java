package com.example.mcbud.androidmemoorm_170922.model;

import android.graphics.Bitmap;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 데이터 모델링 - 도메인 추출 - 개념 모델링
 * Created by mcbud on 2017-09-22.
 */

@DatabaseTable(tableName = "picnote")
public class PicNote {
    // 식별자
    @DatabaseField
    private long id;
    // 제목
    @DatabaseField
    private String title;
    // 그림
    @DatabaseField
    private String bitmap_path;
    // 날짜
    @DatabaseField
    private long datetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBitmap() {
        return bitmap_path;
    }

    public void setBitmap(String bitmap_path) {
        this.bitmap_path = bitmap_path;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
