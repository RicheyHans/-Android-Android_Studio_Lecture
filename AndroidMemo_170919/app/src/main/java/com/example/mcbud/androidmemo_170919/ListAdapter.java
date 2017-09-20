package com.example.mcbud.androidmemo_170919;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mcbud.androidmemo_170919.domain.Memo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by mcbud on 2017-09-20.
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    // 1. 저장소
    ArrayList<Memo> data;
    // 2. 생성자 정의
    public ListAdapter(Context context, ArrayList<Memo> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if(view == null){ // 뷰가 최초 생성될경우
            // 1. 뷰 생성
            view = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            // 2. 홀더생성
            holder = new Holder(view);
            // 3. 뷰에 홀더를 붙인다
            view.setTag(holder);
        }else{ // 뷰가 두번째 호출되면 (즉 한화면이 넘어가면)
            // 1. 뷰에 붙인 홀더를 꺼낸다
            holder = (Holder) view.getTag();
        }
        // 값을 세팅
        // 1. 컬렉션 구조의 저장소로부터 객체 단위로 꺼내두는게 사용하기 편하다.
        Memo memo = data.get(position);
        // 2. 홀더의 위젯에 데이터를 입력한다
        holder.setNo(memo.getNo());
        holder.setTitle(memo.getTitle());
        holder.setDate(memo.getDatetime());
        holder.setPosition(position);
        holder.setAuthor(memo.getAuthor());
        holder.setContent(memo.getContent());
        return view;
    }
}

class Holder {
    private int position;
    private String author;
    private String content;
    private TextView textNo;
    private TextView textTitle;
    private TextView textDate;

    public Holder(View view){
        textNo = view.findViewById(R.id.textNo);
        textTitle = view.findViewById(R.id.textTitle);
        textDate = view.findViewById(R.id.textDate);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);

                intent.putExtra("position", position);
                intent.putExtra("title",textTitle.getText());
                intent.putExtra("author", author);
                intent.putExtra("content", content);
                intent.putExtra("datetime", textDate.getText());

                view.getContext().startActivity(intent);
            }
        });
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setPosition(int position){
        this.position = position;
    }
    public void setNo(int no) {
        textNo.setText(no + "");
    }
    public void setTitle(String title) {
        textTitle.setText(title);
    }
    public void setDate(Long datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String result = sdf.format(datetime);
        textDate.setText(result);
    }


}