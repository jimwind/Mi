package com.up360.mi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.TextView;

import com.up360.mi.R;
import com.up360.mi.activity.hencoder.ui_1_1_activity;
import com.up360.mi.character.WriteActivity;

public class MainActivity extends BaseActivity implements Model.OnLoginListener {
    private TextView tvLogin;
    private Model mModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogin = findViewById(R.id.login);
        mModel = new Model(this);
        mModel.setOnLoginListener(this);
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
        startActivity(intent);
    }

    @Override
    public void onLogin() {
        tvLogin.setText("success!");
    }
}
