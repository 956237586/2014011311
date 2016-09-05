package cn.hylstudio.android.testservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 24：编程项目-[上机实践]使用startService启动服务-具体内容
 * 25：编程项目-[上机实践]使用bindService启动服务-具体内容
 * created by hyl on 2016/9/5
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "hylTAG";
    private ServiceConnection serviceConnection;
    private MyService myService;
    private Button btn_startService;
    private Button btn_stopService;
    private Button btn_startServiceByBind;
    private Button btn_stopServiceByUnbind;
    private Button btn_useService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_startService = (Button) findViewById(R.id.btn_start_service);
        btn_stopService = (Button) findViewById(R.id.btn_stop_service);
        btn_startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("num", (int) (Math.random() * 100));
                startService(intent);
                btn_startService.setEnabled(false);
            }
        });


        btn_stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
                btn_stopService.setEnabled(false);
            }
        });


        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.v(TAG, "onServiceConnected");
                myService = ((MyService.LocalBinder) iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.v(TAG, "onServiceDisconnected");
            }
        };

        btn_startServiceByBind = (Button) findViewById(R.id.btn_start_service_by_bind);
        btn_useService = (Button) findViewById(R.id.btn_use_service);
        btn_stopServiceByUnbind = (Button) findViewById(R.id.btn_stop_service_by_unbind);
        btn_startServiceByBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
                btn_startServiceByBind.setEnabled(false);
            }
        });

        btn_stopServiceByUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
                btn_stopServiceByUnbind.setEnabled(false);
            }
        });

        btn_useService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myService != null) {
                    myService.serve();
                    btn_useService.setEnabled(false);
                }
            }
        });

    }
}
