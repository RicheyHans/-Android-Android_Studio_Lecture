package com.example.mcbud.contact;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * Content Resolver 사용하기
     * 1. Content Resolver 불러오기(startActivity와 유사)
     * 2. 데이터 URI정의 <- DB에서의 table 이름과 유사
     * 3. 데이터 URI에서 가져올 column명 정의
     *      > 조건절을 정의할 수도 있다(이름, 직장 등...)
     * 4. 불러온 CR로 쿼리한 데이터를 Cursor에 담는다.
     * 5. Cursor에 담긴 데이터를 반복문을 사용해 처리한다.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @TargetApi(Build.VERSION_CODES.M)
    private List<Contact> load(){

        List<Contact> contacts = new ArrayList<>();

        // 1. Content Resolver 불러오기(Context에 포함됨)
        ContentResolver resolver = getContentResolver();
        // 2. 데이터 URI정의
        // 전화번호 URI : .....Phone.CONTENT_URI
        // 주소록 URI : ....Contacts.CONTENT_URI
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        // 3. 가져올 컬럼의 정의
        String projection[] = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        // 4. 쿼리 결과가 Cursor형태 데이터 타입으로 넘어온다.
        Cursor cursor = resolver.query(uri, projection, null, null null);
        // 5. cursor 반복처리
        if(cursor != null){
            while(cursor.moveToNext()){
                Contact contact = new Contact();
                int index = cursor.getColumnIndex(projection[1]);   //DISPLAY_NAME
                String name = cursor.getString(index);

                index = cursor.getColumnIndex(projection[2]);
                String number = cursor.getString(index);

                index = cursor.getColumnIndex(projection[0]);
                int id = cursor.getInt(index);

                contacts.add(contact);
            }
        }
        return contacts;
    }

    private void init(){
        setContentView(R.layout.activity_main);
        CustomAdapter adapter = new CustomAdapter();
        adapter.setData(data);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutParams(new LinearLayoutManager(this));
    }
}

class Contact {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String number;

}