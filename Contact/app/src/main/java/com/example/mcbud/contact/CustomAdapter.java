package com.example.mcbud.contact;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mcbud on 2017-09-26.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    private List<Contact> data;

    public void setData(List<Contact> data) { this.data = data;}

    @Override
    public CustomAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CustomAdapter.Holder holder, int position) {
        Contact contact = data.get(position);
        holder.setName(contact.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private TextView textName;
        public Holder(View itemView){
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
        }
        public void setName(String name){
            textName.setText(name);
        }

    }
}
