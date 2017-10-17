package com.example.mcbud.transferstation_171017;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mcbud.transferstation_171017.Model.JsonClass;
import com.example.mcbud.transferstation_171017.Model.Row;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        load();
    }

    private void load(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String str = Remote.getData("http://openapi.seoul.go.kr:8088/58514475496d636231387a64455576/json/StationDayTrnsitNmpr/1/100");
                System.out.println("str================================"+str);
                return str;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                System.out.println("================================"+jsonString);
                data = parse(jsonString);
                setList();
            }
        }.execute();
    }

    private List<Row> parse(String string){
        Gson gson = new Gson();
        JsonClass jsonClass = gson.fromJson(string, JsonClass.class);

        Row[] rows = jsonClass.getStationDayTrnsitNmpr().getRow();

        List<Row> result = Arrays.asList(rows);
        return result;
    }

    List<Row> data;
    private void setList(){
        ListAdapter adapter = new ListAdapter(data, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
