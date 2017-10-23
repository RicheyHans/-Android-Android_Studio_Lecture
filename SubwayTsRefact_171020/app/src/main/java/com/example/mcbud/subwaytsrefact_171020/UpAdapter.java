package com.example.mcbud.subwaytsrefact_171020;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mcbud on 2017-10-24.
 */

public class UpAdapter extends RecyclerView.Adapter<StationAdapter.Holder> {
    @Override
    public StationAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(StationAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
