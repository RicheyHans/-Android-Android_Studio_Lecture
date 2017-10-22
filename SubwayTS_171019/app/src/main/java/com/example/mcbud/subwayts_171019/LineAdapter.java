package com.example.mcbud.subwayts_171019;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mcbud on 2017-10-19.
 */

public class LineAdapter extends BaseAdapter implements AdapterView.OnItemSelectedListener{
    List<String> data;
    TextView textView;

    public LineAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        textView = view2.findViewById(R.id.textView);
        textView.setText(data.get(i));

        return view2;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(textView.getText().equals("1호선")) {
            Log.d("safsf", "==================================선택!" + "선택!!!"+textView.getText());
        } else if(textView.getText().equals("2호선")) {
            Log.d("safsf", "==================================선택!" + "선택!!!"+textView.getText());
        } else if(textView.getText().equals("3호선")) {
            Log.d("safsf", "==================================선택!" + textView.getText());
        } else if(textView.getText().equals("4호선")) {
            Log.d("safsf", "==================================선택!" + textView.getText());
        } else if(textView.getText().equals("5호선")) {
            Log.d("safsf", "==================================선택!" + textView.getText());
        } else if(textView.getText().equals("6호선")) {
            Log.d("safsf", "==================================선택!" + "선택!!!"+textView.getText());
        } else if(textView.getText().equals("7호선")) {
            Log.d("safsf", "==================================선택!" + textView.getText());
        } else if(textView.getText().equals("8호선")) {
            Log.d("safsf", "==================================선택!" + textView.getText());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
