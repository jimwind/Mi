package com.up360.mi.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.up360.mi.IAdditionService;

public class AdditionService extends Service {

    public AdditionService(){

    }

    IAdditionService.Stub mBinder = new IAdditionService.Stub() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x + y;
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
