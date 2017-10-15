package com.example.mcbud.servicebasic_171010;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    // 컴포넌트는 바인더를 통해 서비스에 접근할 수 있다
    class CustomBinder extends Binder {
        public CustomBinder(){

        }
        public MyService getService(){
            return MyService.this;
        }
    }

    IBinder binder = new CustomBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService","========onBind()");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MyService","========onUnbind()");
        return super.onUnbind(intent);
    }

    public int getTotal(){
        return total;
    }

    private int total = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            String action = intent.getAction();
            switch(action){
                case "START":
                    setNotification("PAUSE");
                    break;
                case "PAUSE":
                    setNotification("START");      // 항상 체크한다.
                    break;
                case "DELETE":
                    stopForeground(true);           // 서비스는 유지하되 노티만 사라짐
                    break;
            }
        }
        /*
        startForeground();
        Log.d("MyService","========onStartCommand()");
        for(int i=0 ; i<1000 ; i++){
            total += i;
            System.out.println("서비스에서 동작중입니다."+i);
        }*/
        return super.onStartCommand(intent, flags, startId);
    }

    // 포어그라운드 서비스하기
    // 포어그라운드 서비스 번호
    public static final int FLAG = 17465;
    private void setNotification(String cmd){
        /* 포어그라운드 서비스에서 보여질 노티바 만들기 (빌더로 만든다)
         노티피케이션에 노래 제목, 가수 등을 세팅해두면 의존성을 가지고 값을 변경해줄 수 없다.
         그래서 스레드가 1초에 한 번씩 체크하려면(재생시간) 항상 날려줘야한다.
         그래서 저 위 FLAG를 보고 지우고 중첩하는 구조다.
         즉, 변경 사항이 생기면 노티피케이션은 항상 다시 쏴주는 것 */

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.mipmap.ic_launcher) // 최상단 스테이터스 바에 나타나는 아이콘
                .setContentTitle("음악 제목")
                .setContentText("가수명");

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(icon);     // 노티바에 나타나는 큰 아이콘. 향후 앨범아트로 변경(bitmap factory로)
        // icon.release()필요


        // 노티바 전체를 클릭했을 때 발생하는 액션의 처리(노티바 삭제를 위함)
        Intent deleteIntent = new Intent(getBaseContext(), MyService.class);
        deleteIntent.setAction("DELETE");     // 이걸 실행하면 무조건 PAUSE가 날아간다 intent.getAction에서 취하는 명령어
        PendingIntent mainIntent = PendingIntent.getService(getBaseContext(), 1, deleteIntent, 0);
        builder.setContentIntent(mainIntent);

        /*
           노티에 나타나는 버튼의 처리
         */

        /* 클릭 했을 때 노티를 PAUSE 시키는 명령어를, 서비스에서 다시 받아서 처리한다
         지연 인텐트를 사용함(액티비티를 띄우거나 서비스를 띄울 땐 intent를 안드로이드가 처리하지만
         notification은 context가 없으므로 pending intent를 지원한다) 펜딩 인텐트에 넣어서 보내면
         context정보가 펜딩 인텐트에 남아 있다. 즉, 액티비티에서 클릭한 것 처럼 처리된다. */

        Intent pauseIntent = new Intent(getBaseContext(), MyService.class);
        pauseIntent.setAction(cmd);     // 이걸 실행하면 무조건 PAUSE가 날아간다 intent.getAction에서 취하는 명령어
        PendingIntent pendingIntent = PendingIntent.getService(getBaseContext(), 1, pauseIntent, 0);

        // 노티피케이션에 들어가는 버튼에 세팅(3개까지만 가능)
        int iconId = android.R.drawable.ic_media_pause;// 아이콘의 리소스 아이디
        if(cmd.equals("START")){
            iconId = android.R.drawable.ic_media_play;
        }
        String btnTitle = cmd;
        NotificationCompat.Action pauseAction
                = new NotificationCompat.Action.Builder(iconId, btnTitle, pendingIntent).build();
        builder.addAction(pauseAction);

        Notification notification = builder.build();
        startForeground(FLAG, notification);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","========onCreate()");
    }

    @Override
    public void onDestroy() {
        // foreground 상태에서 해제 되며, 서비스는 그대로
        //stopForeground(true);     // true면 노티 바를 제거, false면 노티 바를 제거하지 않는다
        super.onDestroy();
        Log.d("MyService","========onDestroy()");
    }
}

