/**
 * created by hyl.
 * 1：编程项目-[上机实践]在Android Studio中创建Hello World程序-具体内容
 * 2：编程项目-[上机实践]学会使用Logcat调试程序-具体内容
 */
package cn.hylstudio.android.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HelloWorldActivity extends AppCompatActivity {
    public static String tag_test = "Practice Program by hyl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.hylstudio.android.helloworld.R.layout.activity_hello_world);
        Log.d(tag_test, "test logcat");
    }
}
