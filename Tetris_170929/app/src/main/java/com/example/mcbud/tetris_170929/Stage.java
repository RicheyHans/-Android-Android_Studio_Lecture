package com.example.mcbud.tetris_170929;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * 게임 스테이지 전체 그려주는 클래스
 */

public class Stage extends View {
    Setting setting;

    Board board;
    Preview preview;

    public Stage(Context context, Setting setting) {
        super(context);

        this.setting = setting;
        // 보드와 프리뷰를 생성한다
        board = new Board(1, 1, 11, 16, setting.unit);
        preview = new Preview(13, 1, 4, 4, setting.unit);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        preview.onDraw(canvas);
        board.onDraw(canvas);
    }

    // 0. 화면을 최초에 그리기 전에 기본세팅
    public void init(){
        addBlockToPreview();
        addBlockToBoardFromPreview();
        addBlockToPreview();
    }

    // 1. 블럭을 생성하는 함수
    public Block newBlock(){
        Property property = new Property();
        property.x = 0;
        property.y = 0;
        property.paint = new Paint();
        property.paint.setColor(Color.RED);
        property.unit = setting.unit;
        return new Block(property);
    }
    // 2. 블럭을 preview 에 담는 함수
    public void addBlockToPreview(){
        Block block = newBlock();
        preview.addBlock(block);
    }
    // 3. 블럭을 preview 에서 Board로 옴기는 함수
    public void addBlockToBoardFromPreview(){
        Block block = preview.popBlock();
        board.addBlock(block);
    }

    // 키 패드 동작
    public void up() {
        board.up();
        invalidate();
    }
    public void down() {
        boolean check = board.down();
        // 다운시 충돌되면 map에 현재 블럭을 삽입하고
        // 새로운 블럭을 Preview에서 가져와서 담는다.
        if(!check) {
            board.addBlockToMap();
            addBlockToBoardFromPreview();
            addBlockToPreview();
        }
        invalidate();
    }
    public void left() {
        board.left();
        invalidate();
    }
    public void right() {
        board.right();
        invalidate();
    }
}

