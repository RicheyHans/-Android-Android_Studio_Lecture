package com.example.mcbud.viewpager_170928;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// ViewPager사용하기
public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        // 가. 가상 데이터 작성(데이터 로드)
        List<String> data = new ArrayList<>();
        for(int i = 0; i<100; i++){
            data.add("Temp Data="+i);
        }

        // 나. adapter를 생성(View Pager는 생성과 함께 데이터를 넘겨야 한다)
        CustomAdapter adapter  = new CustomAdapter(this, data);
        adapter.setView(viewPager);

        // 다. adapter 연결
        viewPager.setAdapter(adapter);
        // MVC 패턴 개념이라면 adapter(controller)가 view를 갖고 있어야 하지만, 반대로 사용된다.
    }
}

class CustomAdapter extends PagerAdapter{

    Context context;
    List<String> data;

    public CustomAdapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
    }

    // 관계 정리를 위해 삽입하는 관계
    public void setView(ViewPager viewPager){
        viewPager.setAdapter(this);
    }

    // 전체 개수
    @Override
    public int getCount() {
        return data.size();
    }

    // getView와 동일한 역할. **즉 xml을 view로 만들고 data 삽입 후 리턴해준다.**
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // inflate할 레이아웃 파일이 필요. 어제는 프래그먼트로 만들었지만 이번엔 View로

        // 1.여기서 Layout 파일을 inflate해서 view로 만든다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_viewpager, null);

        // 2.data를 화면에 세팅
        String value = data.get(position);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(value);

        // 3.View Group에 만들어진 view를 add한다.
        container.addView(view);

        // 4.내가 만든 view를 return 한다.
        return view;
    }

    // InstantiateItem에서 return 받은 object가 View인지 검증
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 현재 사용하지 않는 View는 제거함
    // container : 뷰 페이저
    // object : 뷰 페이저 안에 있는 item
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView( (View)object);
        // super.destroyItem(container, position, object);
    }

}
