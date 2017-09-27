package com.example.mcbud.fragmentbasic_170928;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor(코드 작성 금지)
    }

    @Override
    public void onAttach(Context context) {     // <- 이 context가 나를 삽입한 액티비티이다.
        // 1. 나를 사용한 액티비티가 내가 제공한 인터페이스를 구현했는지 확인
        if(context instanceof Callback){
            callback = (Callback) context;  // 구현했다면 인터페이스로 캐스팅해서 사용
        }
        super.onAttach(context);
        Log.d("Fragment", "==============onAttach()");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment", "==============onDetach()");

    }

    Callback callback = null;
    Button goDtail;
    // 액티비티에 부착되는 순간 동작한다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        goDtail = (Button) view.findViewById(R.id.button);
        goDtail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 어떤 액티비티에 붙을 지 모르지만 그 액티비티의 함수를 불러와야 한다.
                callback.goDetail();        // 내가 설계해 놓은 인터페이스를 호출한다.
                                            // 나를 사용하는 측은 이 인터페이스를 강제로 구현해야 한다.
            }
        });

        // 프래그먼트 화면에 값을 세팅하는 로직은 이 사이에서
        Log.d("Fragment", "==============onCreateView()");

        return view;
    }

    public interface Callback {
        public void goDetail();
    }

    @Override
    public void onStart() {
        Log.d("Fragment", "==============onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("Fragment", "==============onResume()");
        super.onResume();
    }
}
