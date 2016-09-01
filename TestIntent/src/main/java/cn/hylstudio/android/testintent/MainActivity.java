/**
 *
 */

package cn.hylstudio.android.testintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_startScond = (Button) findViewById(R.id.btn_startSecond);
        btn_startScond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("lalalalalalala");
                startActivity(intent);
            }
        });


        Button btn_call = (Button) findViewById(R.id.btn_Call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission("android.permission.CALL_PHONE") !=
                            PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "缺少打电话权限", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                String phonenumber = "13800138000";
                String encodedPhonenumber = null;
                try {
                    encodedPhonenumber = URLEncoder.encode(phonenumber, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + encodedPhonenumber)));
            }
        });
    }
}
