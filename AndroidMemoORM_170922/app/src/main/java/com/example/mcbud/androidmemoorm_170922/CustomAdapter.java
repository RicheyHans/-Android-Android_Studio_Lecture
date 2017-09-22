package com.example.mcbud.androidmemoorm_170922;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mcbud.androidmemoorm_170922.model.PicNote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcbud on 2017-09-22.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    // 1. 데이터 저장소 생성
    private List<PicNote> data;

    public void setData(List<PicNote> data){
        this.data = data;
    }

    // 생성자
    public CustomAdapter(){

    }

    // 2. 목록의 전체 길이를 결정
    @Override
    public int getItemCount() {
        return data.size();
    }

    // 3. 홀더 생성
    // getView에 해당되는 역할(최초 호출, null 일 경우)
    // 목록에서 아이템이 최초 요청되면 View Holder를 생성해준다.
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 1. 만들어둔 layout 파일을 inflate한다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        // 2. inflate 된 View를 Holder의 생성자에 담는다.
        Holder holder = new Holder(view);
        // 3. 생성된 Holder를 리턴한다.
        return holder;
    }

    // 홀더 사용
    // getView에 해당되는 역할(두 번째 호출 시에는 얘만 호출된다)
    // 생성된 View Holder를 Recycler View에 넘겨서 그리게 한다.
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 1. 데이터 저장소에서 객체 단위로 꺼내둔다.
        PicNote picNote = data.get(position);
        // 리사이클러뷰.뷰 홀더로 전달되므로 캐스팅
        // 2. 홀더에 있는 타이틀 전달
        holder.setTitle(picNote.getTitle());
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView textTitle;
        public Holder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
        }
        public void setTitle(String title){
            textTitle.setText(title);
        }
    }


}
