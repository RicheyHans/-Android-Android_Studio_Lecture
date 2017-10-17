package com.example.mcbud.bicycle_171017;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.mcbud.bicycle_171017.Model.JsonClass;
import com.example.mcbud.bicycle_171017.Model.Row;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        load();

    }

    // Data를 가져와서 축적한다
    private void load(){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                return Remote.getData("http://openapi.seoul.go.kr:8088/58514475496d636231387a64455576/json/GeoInfoBikeConvenientFacilitiesWGS/1/100");
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                JsonClass json = gson.fromJson(s, JsonClass.class);
                rows = json.getGeoInfoBikeConvenientFacilitiesWGS().getRow();
                // 맵이 사용할 준비가 되었는지를 비동기로 확인하는 작업
                mapFragment.getMapAsync(MapsActivity.this);
                // 맵이 사용할 준비가 되었으면 OnMapReadyCallback.onMapReady를 호출
            }
        }.execute();
    }

    // 좌표 데이터를 저장하기 위한 저장소
    Row[] rows = null;

    // 데이터를 사용해서 마크를 각 좌표에 출력

    // OnMapReadyCallback 인터페이스의 구현
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng point = null;
        for(Row row : rows){
            point = new LatLng(Double.parseDouble(row.getLAT()), Double.parseDouble(row.getLNG()));
            mMap.addMarker(new MarkerOptions().position(point).title(row.getCLASS()));
        }
        // Add a marker in Sydney and move the camera
        // 마크를 찍는 역할
        // 마크로 화면을 움직이는 역할(좌표, 줌)
        point = new LatLng(37.532830, 126.983027);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 11));  // 마지막 포인트를 찍어준다
    }
}
