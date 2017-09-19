package com.example.mcbud.basiclist_170919;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 1. 데이터를 선언
    List<String> data = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 데이터 정의(100개 더미 값)
        for(int i = 0; i < 100; i++){
            data.add("임시값"+i);
        }
        // 2, 데이터와 리스트뷰를 연결하는 아답터 생성
        CustomAdapter adapter = new CustomAdapter(this, data);
        // 3. 아답터와 리스트뷰를 연결
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}

// 2.기본 아답터 클래스를 상속받아서 구현
class CustomAdapter extends BaseAdapter{
    // 데이터 저장소를 아답터 내부에 두는 것이 컨트롤에 용이함
    List<String> data;
    Context context;

    public CustomAdapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
    }
    // 해당 메서드는 대부분 자동으로 호출된다.
    @Override
    public int getCount() { // 현재 데이터의 총 개수
        return data.size();
    }

    // 현재 뿌려줄 데이터를 리턴해준다.
    @Override
    public Object getItem(int position) {   // 호출되는 목록 아이템의 데이터 순서
        return data.get(position);
    }

    // 뷰의 아이디를 리턴해준다.
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 목록에 나타나는 아이템 하나하나를 그려준다. 리스트뷰의 아이템 각각을 호출한다.
    // 화면에 보이는 것들이 호출되고(동시에 5개, 이런식) 나머지는 화면에 숨어있다.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // 레이아웃 인플레이터로 mxl파일을 View 객체로 변환
        Holder holder = null;
        if(view == null) {  // 아이템 view를 재사용하기 위해 null 체크를 해준다.
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            // 아이템이 최초 호출될 경우는 Holder에 위젯들을 담고
            holder = new Holder(view);

            // 홀더를 View에 붙여놓는다.
            view.setTag(holder);

        } else {
            // View에 붙어있는 홀더를 가져온다.
            holder = (Holder)view.getTag();
        }
        // 한 화면에 7개가 보인다면 뷰 7개, 홀더 7개 생성
        // 이 뷰 자체가 list_item이라고 생각해라.

        // 뷰 안에 있는 텍스트뷰 위젯에 값을 입력한다.
        // findviewbyid 사용을 위해서는 아이디를 찾을 대상을 설정해줘야 함. 평소에는 기본설정이 되어있다.

        // findviewbyid도 역시 textView 변수를 위한 메모리가 사용된다. 즉, 내렸다 올렸다 하게 되면
        // 이미지 파일이면 엄청 메모리 낭비가 심해진다. -> 지금까지는 view 전체를 재사용했는데, 일부 요소요소를
        // 재사용하기 위해 Holder를 사용하게 된다. 홀더 사용을 위해는 클래스 별도 생성이 필요하다.
        holder.textview.setText(data.get(position));

        return view;
    }
}

class Holder{

    TextView textview;

    public Holder(View view){
        textview = (TextView)view.findViewById(R.id.textView);
        setClickListener();
    }

    public void setClickListener(){
        textview.setOnClickListener(new View.OnClickListener() {
            // 화면에 보여지는 View는 기본적으로 자신이 속한 컴포넌트의 context를 그대로 가지고 있다.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("valueKey", textview.getText());
                view.getContext().startActivity(intent);
            }
        });
    }
}