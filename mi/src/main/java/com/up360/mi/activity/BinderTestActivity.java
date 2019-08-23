package com.up360.mi.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.up360.mi.IAdditionService;
import com.up360.mi.R;
import com.up360.mi.services.AdditionService;

public class BinderTestActivity extends BaseActivity {
    IAdditionService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_test);
        Intent intentService = new Intent(this, AdditionService.class);
        bindService(intentService, mConnection, Context.BIND_AUTO_CREATE);
    }
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IAdditionService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public void add(View v) throws RemoteException {
        EditText et1 = (EditText) findViewById(R.id.et_num1);
        EditText et2 = (EditText) findViewById(R.id.et_num2);

        final TextView tv = (TextView) findViewById(R.id.tv);

        final int x = Integer.parseInt(et1.getText().toString());
        final int y = Integer.parseInt(et2.getText().toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int result = 0;
                try {
                    result = mService.add(x, y);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                tv.setText(result +"");
            }
        });

    }
}
