package com.example.mcbud.taplayout_170927;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by mcbud on 2017-09-28.
 */

public class One extends FrameLayout {
    public One(Context context) {
        super(context);
        initView();
    }

    public One(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // 여기서 내가 만든 layout을 inflate하고, 나 자신에게 add한다.
    private void initView(){
        // 1. layout파일로 view를 만들고
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_one, null);

        addView(view);
    }
}
