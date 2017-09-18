package com.example.mcbud.customview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.AppCompatButton;

/**
 * Created by mcbud on 2017-09-18.
 */

public class AniButton extends AppCompatButton implements View.OnTouchListener{
    boolean animation = false;

    // animation 속성이 true일 경우 scale 애니메이션을 사용해서 클릭 시 살짝 커졌다 작아지는 버튼을 만드시오.
    public AniButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 1. attrs.xml에 정의된 속성을 가져온다.
        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.AniButton);
        // 2. 해당 이름으로 정의된 속성의 개수를 가져온다.
        int size = typed.getIndexCount();
        Log.d("AniButton", "size="+size);
        // 3, 반복문을 돌면서 해당 속성에 대한 처리를 해준다.
        for(int i = 0; i < size; i++){
            // 3.1 현재 배열에 있는 속성 아이디 가져오기.
            int current_attr = typed.getIndex(i);
            switch(current_attr){
                case R.styleable.AniButton_animation:
                    String animation = typed.getString(current_attr);
                    // 애니메이션에는 true또는 false가 듣어간다.
                    // Boolean.parseBoolean과 boolean 타입을 빼고 if를 animation.equals("true")로 하늑 게 더 안정적.
                    if( "true".equals(animation) ){  // animation.equals(true)로 쓰면, animation이 null이어도 죽지 않는다.
                        String currentText = getText().toString();
                        setText("[animation]\n"+currentText);
                        this.animation = true;
                    }
                    break;

                case R.styleable.AniButton_delimiter:
                    break;
            }
        }
        setOnTouchListener(this);
    }

    private void runAnimation(){
        if(this.animation == true) {
            ObjectAnimator aniX = ObjectAnimator.ofFloat(this, "scaleX", 1.2f, 1.0f);
            ObjectAnimator aniY = ObjectAnimator.ofFloat(this, "scaleY", 1.2f, 1.0f);
            AnimatorSet aniSet = new AnimatorSet();
            aniSet.playTogether(aniX, aniY);
            aniSet.setDuration(1000);
            aniSet.start();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                runAnimation();
                break;
        }
        return false;
    }
}
