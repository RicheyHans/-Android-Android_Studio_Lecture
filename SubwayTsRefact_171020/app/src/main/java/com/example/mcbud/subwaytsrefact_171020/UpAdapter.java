package com.example.mcbud.subwaytsrefact_171020;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mcbud.subwaytsrefact_171020.Model.RealtimeArrivalList;

import java.util.List;

/**
 * Created by mcbud on 2017-10-24.
 */

public class UpAdapter extends RecyclerView.Adapter<UpAdapter.Holder> {
    Context context;
    List<RealtimeArrivalList> data;

    public UpAdapter(List<RealtimeArrivalList> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public UpAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_up, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        RealtimeArrivalList ra = data.get(position);
        holder.textTime.setText(ra.getArvlMsg2());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView textTime;
        public Holder(View itemView) {
            super(itemView);
            textTime = itemView.findViewById(R.id.textTime);
        }
    }
}
