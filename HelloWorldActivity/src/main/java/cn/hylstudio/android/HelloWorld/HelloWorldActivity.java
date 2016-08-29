/**
 * created by hyl.
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
