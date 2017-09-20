package com.example.mcbud.androidmemo_170919.domain;

import android.util.Log;

/**
 * Created by mcbud on 2017-09-19.
 */

public class Memo {
    private static final String DELIMETER = "//";

    private int no;
    private String title;
    private String author;
    private String content;
    private long datetime;

    public Memo(){

    }

    public Memo(String text){
        // 1. 문자열을 줄("\n")단위로 분해
        String lines[] = text.split("\n");
        // 2. 문자열을 행(":^:")단위로 분해
        for(String line : lines){
            String columns[] = line.split(DELIMETER);
            String key = "";
            String value = "";
            if(columns.length == 2) {
                key = columns[0];
                value = columns[1];
            }else{
                key = "";
                value = columns[0];
            }
            Log.d("Memo","line========="+line);
            Log.d("Memo","key========="+key);
            Log.d("Memo","value========="+value);
            switch(key){
                case "no":
                    setNo(Integer.parseInt(value));
                    break;
                case "title":
                    setTitle(value);
                    break;
                case "author":
                    setAuthor(value);
                    break;
                case "datetime":
                    setDatetime(Long.parseLong(value));
                    break;
                case "content":
                    setContent(value);
                    break;
                default:
                    appendContent(value);
            }
        }
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public void appendContent(String value) {
        this.content += "\n"+value;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("no").append(DELIMETER).append(no).append("\n");
        result.append("title").append(DELIMETER).append(title).append("\n");
        result.append("author").append(DELIMETER).append(author).append("\n");
        result.append("datetime").append(DELIMETER).append(datetime).append("\n");
        result.append("content").append(DELIMETER).append(content).append("\n");
        return result.toString();
    }

    public byte[] toBytes(){
        return toString().getBytes(); // 문자열에서 바이트배열로 변환
    }

}

/*
글번호 :^: 1
제목 :^: 제목입니다.
작성자 :^: 한승범
작성일자 :^: 2017/09/20 10:55
내용 :^: TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT
TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT
 */