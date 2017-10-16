package com.example.mcbud.jsondatabasic_171016;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mcbud.jsondatabasic_171016.model.User;

import java.util.List;

/**
 * Created by mcbud on 2017-10-16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder>{
    List<User> data;
    public ListAdapter(List<User> data){
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        User user = data.get(position);
        //holder.imageView.setImageURI();
        holder.textId.setText(user.getId()+"");
        holder.textLogin.setText(user.getLogin());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textId;
        TextView textLogin;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textId = itemView.findViewById(R.id.textId);
            textLogin = itemView.findViewById(R.id.textLogin);
        }
    }
}

