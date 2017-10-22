package com.example.mcbud.subwaytsrefact_171020;


/**
 * Created by mcbud on 2017-10-23.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mcbud.subwaytsrefact_171020.DAO.StationList;

import java.util.ArrayList;
import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.Holder> {

    public List<String> data = new ArrayList<>();

    public void setData(String[] StationNum){

        for(int i = 0; i < StationNum.length; i++){
            this.data.add(StationNum[i]);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 만들어둔 item_list layout파일을 inflate한다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        // inflate된 view를 Holder의 생성자에 전달한다.
        Holder holder = new Holder(view);
        // 생성된 holder를 리턴한다.
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String str = data.get(position);
        holder.setTitle(str);
    }



    public class Holder extends RecyclerView.ViewHolder{
        private TextView textStn;

        public Holder(View itemView) {
            super(itemView);
            textStn = itemView.findViewById(R.id.textStn);
            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 역 리스트 클릭 시 디테일 액티비티로 이동
                }
            });*/
        }

        public void setTitle(String string){
            textStn.setText(string);
        }

    }
}

