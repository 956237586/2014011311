/**
 * created by hyl
 * 14：编程项目-[上机实践]使用Intent隐式启动-具体内容
 * 15：编程项目-[上机实践]使用Intent拨打电话-具体内容
 * 16：编程项目-[上机实践]进一步熟悉Intent Filter-具体内容
 * 2016.9.1
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
        Button btn_startSecond = (Button) findViewById(R.id.btn_startSecond);
        btn_startSecond.setOnClickListener(new View.OnClickListener() {
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
                String phoneNumber = "10010";
                String encodedPhoneNumber = null;
                try {
                    encodedPhoneNumber = URLEncoder.encode(phoneNumber, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + encodedPhoneNumber)));
            }
        });

        Button btn_startThird = (Button) findViewById(R.id.btn_startThird);
        btn_startThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("schemodemo://edu.bistu/path"));
                startActivity(intent);
            }
        });


    }
}
