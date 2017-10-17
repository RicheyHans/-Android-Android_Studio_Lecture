package com.example.mcbud.transferstation_171017;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mcbud.transferstation_171017.Model.Row;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mcbud on 2017-10-17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {
    Context context;
    List<Row> data;

    public ListAdapter(List<Row> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Row row = data.get(position);
        holder.textSn.setText(row.getSN());
        holder.textStatnNm.setText(row.getSTATN_NM());
        holder.textWkday.setText(row.getWKDAY());
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class Holder extends RecyclerView.ViewHolder{
        TextView textSn;
        TextView textStatnNm;
        TextView textWkday;

        public Holder(View itemView) {
            super(itemView);
            textSn = itemView.findViewById(R.id.textSn);
            textStatnNm = itemView.findViewById(R.id.textStatnNm);
            textWkday = itemView.findViewById(R.id.textWkday);
        }
    }
}
