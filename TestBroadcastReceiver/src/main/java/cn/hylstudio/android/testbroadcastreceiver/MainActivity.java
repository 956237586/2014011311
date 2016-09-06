package cn.hylstudio.android.testbroadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 27：编程项目-[上机实践]广播-具体内容
 * created by hyl on 2016/9/6
 */
public class MainActivity extends AppCompatActivity {
    private Button btn_broadcast;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_broadcast = (Button) findViewById(R.id.btn_broadcast);
        receiver = new MyReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");

        registerReceiver(receiver, filter);

        btn_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.MY_BROADCAST");
                intent.putExtra("broadcastmsg", "hello receiver.");
                sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
