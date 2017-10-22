package com.example.mcbud.subwayts_171019;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcbud.subwayts_171019.Model.JsonClass;
import com.example.mcbud.subwayts_171019.Model.RealtimeArrivalList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.mcbud.subwayts_171019.R.id.txtStation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Spinner spinner;
    Button searchBtn;
    String searchStn;
    TextView textViewUp, textViewDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    void initView(){
        spinner = (Spinner) findViewById(R.id.spinner);
        searchBtn = (Button) findViewById(R.id.btnSearch);
        editText = (EditText) findViewById(R.id.txtStation);
        textViewUp = (TextView) findViewById(R.id.textUp);
        textViewDown = (TextView) findViewById(R.id.txtDown);
    }

    void initListener(){
        searchBtn.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedValue = list.get(i);
                switch(selectedValue){
                    case "1호선":
                        System.out.println("선택된것이 1호선이라네~");
                        break;
                    case "2호선":
                        System.out.println("선택된것이 2호선이라네~");
                        // System.out.println(data.get(0).getSubwayId());
                        List<RealtimeArrivalList> printListUp = new ArrayList<RealtimeArrivalList>();
                        List<RealtimeArrivalList> printListDown = new ArrayList<RealtimeArrivalList>();
                        /*
                        for(int k = 0; k < data.size(); k++){
                            if((data.get(k).getSubwayId().equals("1002")) && ((data.get(k).getUpdnLine() == "상행") || ( data.get(k).getUpdnLine() == "내선")){
                                printListUp.add(data.get(k));
                            }
                        }
                        */
                        textViewUp.setText("2호선이 선택되었는데 상행이라네");
                        textViewDown.setText("2호선이 선택되었는데 하행이라네");
                        break;
                    case "3호선":
                        System.out.println("선택된것이 3호선이라네~");
                        break;
                    case "4호선":
                        System.out.println("선택된것이 4호선이라네~");
                        break;
                    case "5호선":
                        System.out.println("선택된것이 5호선이라네~");
                        break;
                    case "6호선":
                        System.out.println("선택된것이 6호선이라네~");
                        break;
                    case "7호선":
                        System.out.println("선택된것이 7호선이라네~");
                        break;
                    case "8호선":
                        System.out.println("선택된것이 8호선이라네~");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        searchStn = editText.getText().toString();
        switch(view.getId()){
            case R.id.btnSearch:
                search();
                break;
        }
    }

    void search(){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                String str = Remote.getData("http://swopenapi.seoul.go.kr/api/subway/66795758776d6362373442475a666e/json/realtimeStationArrival/1/10/"+searchStn);
                System.out.println(searchStn);
                System.out.println("str================================"+str);
                return str;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                System.out.println("================================"+jsonString);
                data = parse(jsonString);
                System.out.println("!!!!!!!!!!!"+data);
                setSpinner();
            }
        }.execute();
    }

    private List<RealtimeArrivalList> parse(String string){
        Gson gson = new Gson();
        JsonClass jsonClass = gson.fromJson(string, JsonClass.class);

        RealtimeArrivalList[] realtimeArrivalLists = jsonClass.getRealtimeArrivalList();

        List<RealtimeArrivalList> result = Arrays.asList(realtimeArrivalLists);
        return result;
    }

    List<String> list = null;
    List<RealtimeArrivalList> data;

    private void setSpinner(){
        String temp = data.get(0).getSubwayList();
        System.out.println("#####################"+temp+"###################");
        String[] temp2 = temp.split(",");
        list = new ArrayList<>();
        for(int i =0 ; i< temp2.length; i++) {
            if("1001".equals(temp2[i])) {
                list.add("1호선");
            } else if("1002".equals(temp2[i])) {
                list.add("2호선");
            }else if("1003".equals(temp2[i])) {
                list.add("3호선");
            }else if("1004".equals(temp2[i])) {
                list.add("4호선");
            }else if("1005".equals(temp2[i])) {
                list.add("5호선");
            }else if("1006".equals(temp2[i])) {
                list.add("6호선");
            }else if("1007".equals(temp2[i])) {
                list.add("7호선");
            }else if("1008".equals(temp2[i])) {
                list.add("8호선");
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        spinner.setAdapter(adapter);
    }
}