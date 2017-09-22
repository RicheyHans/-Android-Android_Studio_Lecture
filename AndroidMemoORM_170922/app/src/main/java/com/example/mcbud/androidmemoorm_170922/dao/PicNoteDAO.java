package com.example.mcbud.androidmemoorm_170922.dao;

import android.content.Context;

import com.example.mcbud.androidmemoorm_170922.DBHelper;
import com.example.mcbud.androidmemoorm_170922.model.PicNote;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcbud on 2017-09-22.
 */

public class PicNoteDAO {
    DBHelper helper;
    Dao<PicNote, Long>  dao = null;

    public PicNoteDAO(Context context){
        helper = new DBHelper(context);

        // 어떤 테이블을 쓸 것인지 명시한다.
        try {
            dao = helper.getDao(PicNote.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 생성
    public void create(PicNote picNote){
        try {
            dao.create(picNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PicNote> readAll(/*쿼리를 할 수 있는 조건*/){

        List<PicNote> result = null;
        try {
            result = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public PicNote readOneByID(long id){

        PicNote result = null;
        try {
            result = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<PicNote> search(String word){ // ex) '그림' 입력하면

        String query = String.format("select * from picnote where title like '%%%s%%'", word);
                  // ex) select * from picnote where title like '%그림%'
                  // ex) 그림판입니다, 좋은그림입니다, 그림좋아요 중 앞 뒤를 어떤 글자가 들어와도 검색해준다.
        List<PicNote> result = null;
        try {
            GenericRawResults<PicNote> temp = dao.queryRaw(query, dao.getRawRowMapper() );
            result = temp.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update(PicNote picNote){
        try {
            dao.update(picNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(PicNote picNote){
        try {
            dao.delete(picNote);
        } catch (SQLException e){
            e.printStackTrace();;
        }
    }

}
