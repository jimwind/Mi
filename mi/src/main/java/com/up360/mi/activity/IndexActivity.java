package com.up360.mi.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.up360.mi.R;

public class IndexActivity extends BaseActivity {
    private int i=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_index);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    i--;
//
//                    Log.e("jimwind", "i = " + i);
//                    Log.e("jimwind", "10/i = " + 10 / i);
//                }
//
//            }
//        }).start();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(IndexActivity.this, MainActivity.class));
                IndexActivity.this.finish();
            }
        }, 3000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("jimwind","Index onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("jimwind","Index onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("jimwind","Index onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("jimwind","Index onStop");
    }
}
