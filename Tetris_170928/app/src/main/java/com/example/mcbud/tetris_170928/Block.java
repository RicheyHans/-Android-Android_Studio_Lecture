package com.example.mcbud.tetris_170928;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by mcbud on 2017-09-28.
 */

public class Block {
    Paint paint;
    float unit;
    float x;
    float y;

    // 블럭은 기본 크기와 좌표를 가지고 생성된다.
    public Block(int x, int y, float unit){
        this.x = x;
        this.y = y;
        this.unit = unit;
        this.paint = new Paint();
        paint.setColor(Color.RED);
    }
    public void moveUp() {
        y = y - unit;
    }
    public void moveDown(){
        y = y + unit;
    }
    public void moveRight(){
        x = x + unit;
    }
    public void moveLeft(){
        x = x - unit;
    }
}
