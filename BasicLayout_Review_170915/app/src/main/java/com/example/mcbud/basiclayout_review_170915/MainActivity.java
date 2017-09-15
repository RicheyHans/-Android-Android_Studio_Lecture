package com.example.mcbud.basiclayout_review_170915;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private ConstraintLayout stage;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btnGoal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    @Override
    public void onClick(View view){
        // 클릭된 버튼을 사용하기 위해 시스템에서 넘겨받은 뷰를 원래의 버튼으로 캐스팅해둔다.
        if(view instanceof Button ) {
            Button original = (Button)view;    // view 변수가 Button 클래스의 인스턴스인지를 체크. 버튼이 아닌게 눌렸을 경우에는 예외처리.
            // 오리지널 버튼의 속성값 처리

            // 단, 레이아웃 달라서 못날린다. 그래서 실제 날아갈 더미를 생성해서 상위 레이아웃에 담은 후 처리
            final Button dummy = new Button(this);

            // 생성된 더미에 클릭한 버튼의 속성값을 적용
            dummy.setText(original.getText().toString());  // 텍스트 카피
            dummy.setWidth(original.getWidth());
            dummy.setHeight(original.getHeight());
            dummy.setBackgroundColor(Color.RED);

            // 부모 레이아웃을 가져와서 원래 클래스로 캐스팅해준다.
            LinearLayout parent = (LinearLayout)original.getParent();

            // 부모 레이아웃의 위치값과 원래 버튼의 위치값을 더해서 좌표를 정한다.
            dummy.setX( original.getX() + parent.getX());
            dummy.setY( original.getY() + parent.getY());

            // 더미 위치를 모르니, 상위 레이아웃에 담아준다.
            stage.addView(dummy);


            ObjectAnimator aniY = ObjectAnimator.ofFloat(
                    dummy, "y", btnGoal.getY()
            );
            ObjectAnimator aniX = ObjectAnimator.ofFloat(
                    dummy, "x", btnGoal.getX()
            );
            ObjectAnimator aniR = ObjectAnimator.ofFloat(
                    dummy, "rotation", 720
            );

            AnimatorSet aniSet = new AnimatorSet();
            aniSet.playTogether(aniY, aniX, aniR);
            aniSet.setDuration(5000);

            // 애니메이션 종료 체크를 위한 리스너
            aniSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    stage.removeView(dummy);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            aniSet.start();
            // 스타트되면 서브스레드로 돌아감. 삭제하려면 리스너 설정할 필요 있음.
        }
    }

    private void initView(){
        // 스테이지 가져오기
        stage = (ConstraintLayout)findViewById(R.id.stage);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btnGoal = (Button) findViewById(R.id.btnGoal);
    }

    private void initListener(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
}
