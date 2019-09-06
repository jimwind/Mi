package com.up360.mi.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.up360.mi.R;
import com.up360.mi.activity.hencoder.ui_1_1_activity;
import com.up360.mi.character.WriteActivity;
import com.up360.mi.services.MyService;

public class MainActivity extends BaseActivity implements Model.OnLoginListener, View.OnClickListener {
    private TextView tvLogin;
    private Model mModel;

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;

    private Button contentProvider;

    private MyService.MyBinder myBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("jimwind", "MainActivity process id is "+android.os.Process.myPid());
        tvLogin = findViewById(R.id.login);
        mModel = new Model(this);
        mModel.setOnLoginListener(this);

        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

        contentProvider = findViewById(R.id.content_provider);
        contentProvider.setOnClickListener(this);

    }

    public void login(View v){
        tvLogin.setText("login...");
        mModel.login("13611985800", "123456");
    }
    public void write(View v){
        Intent intent = new Intent(MainActivity.this, WriteActivity.class);
        startActivity(intent);
    }
    public void hencoder(View v){
        Intent intent = new Intent(MainActivity.this, ui_1_1_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void binder_test(View v){
        Intent intent = new Intent(MainActivity.this, BinderTestActivity.class);
        startActivity(intent);
    }
    @Override
    public void onLogin() {
        tvLogin.setText("success!");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;

            case R.id.content_provider:{
                Intent intent = new Intent(context, ProviderActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }

    }
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }
    };

}
