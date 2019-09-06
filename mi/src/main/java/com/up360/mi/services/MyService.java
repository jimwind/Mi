package com.up360.mi.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.up360.mi.R;

public class MyService extends Service {
    public static final String TAG = "MyService";
    private MyBinder mBinder = new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("jimwind", TAG + " onCreate()");
        Log.e("jimwind", TAG + " process id is "+android.os.Process.myPid());

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("jimwind", TAG + " onStartCommand()");
//        Notification.Builder notification = new Notification.Builder(this)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setContentTitle("后台正在运行服务")
//                .setContentText("MyService");
//        startForeground(1, notification.build());
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("jimwind", TAG + " onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {

        public void startDownload() {
            Log.e("jimwind", TAG + " startDownload()");
            // 执行具体的下载任务
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
        }
    }

}
