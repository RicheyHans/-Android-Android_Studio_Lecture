package com.example.mcbud.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by pc on 9/18/2017.
 */

public class DrawView extends View {
    Paint paint;
    Path currentPath;
    List<PathTool> paths = new ArrayList<>();

    // 원의 크기
    // float r = 10;
    // 좌표값을 저장하는 저장소
    // ArrayList<Float> xs = new ArrayList<>();
    // ArrayList<Float> ys = new ArrayList<>();
    // 소스코드에서 사용하기 때문에 생성자 파라미터는 context만 필요
    public DrawView(Context context) {
        super(context);
        paint = new Paint();
        init();
    }

    private void init(){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        setColor(Color.BLACK);
    }

    public void setColor(int color){
        PathTool tool = new PathTool();
        tool.setColor(color);
        currentPath = tool;
        paths.add(tool);
    }

    // 화면을 그려주는 onDraw 오버라이드
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 1. 화면에 터치가 되면...
        // 2. 연속해서 그림을 그려준다
        // 2.1. 터치된 좌표에 작은 동그라미를 그려준다.
        for(PathTool tool:paths){
            paint.setColor(tool.getColor());
            canvas.drawPath(tool, paint);
        }
        // if(x > -1 && y > -1){
        //    for(int i=0 ; i<xs.size() ; i++) {
        //        canvas.drawCircle(xs.get(i), ys.get(i), r, paint);
        //    }
        // }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 최초 터치 시 해당 좌표로 이동(중간을 그리지 않는다)
                // 이전 점과 현재 점 사이를 그리지 않는다.
                currentPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                // 이전 점과 현재 점 사이를 그린다.
                // 터치가 일어나면 패스를 해당 좌표까지 선을 긋는다.
                currentPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        // 터치가 일어나면 좌표값을 세팅해준다.
        // xs.add(event.getX());
        // ys.add(event.getY());

        // onDraw 를 호출하는 메서드를 호출
        invalidate(); // <- 다른 언어에서는 대부분 그림을 그려주는 함수를 호출하는 메서드는
        //    기존 그림을 유지하는데, 안드로드는 지운다.
        // 리턴이 false 일 경우는 touch 이벤트를 연속해서 발생시키지 않는다
        // 즉, 드래그를 하면 onTouchEvent가 재 호출되지 않는다.
        return true;
    }
}

class PathTool extends Path {
    int color;
    public PathTool(){

    }

    public void setColor(int color){
        this.color = color;
    }
    public int getColor(){
        return this.color;
    }

}