package cn.hylstudio.android.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by HYL on 2016/9/5.
 */
public class MyService extends Service {
    private static final String TAG = "hylTAG";
    //onBind返回的IBinder接口对象
    private LocalBinder myBinder = new LocalBinder();

    //计算器binder
    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand()");
        int n = intent.getIntExtra("num", 0);
        Log.v(TAG, "random int is:" + n);
        Toast.makeText(getApplicationContext(), "random int is:" + n, Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind()");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    public void serve() {
        Toast.makeText(getApplicationContext(), "service 2333333", Toast.LENGTH_SHORT).show();
    }
}
