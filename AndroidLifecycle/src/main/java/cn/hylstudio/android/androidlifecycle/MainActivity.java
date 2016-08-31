/**
 * created by hyl
 * 8：编程项目-[上机实践]Activity生命周期-具体内容
 * 9：编程项目-[上机实践]生成并调用第二个Activity-具体内容
 * 10：编程项目-[上机实践]Activity间单向传递数据-具体内容
 * 11：编程项目-[上机实践]Activity间来回传递数据-具体内容
 * 2016.8.31
 */
package cn.hylstudio.android.androidlifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "lifecycle first";
    private Button btn_startSecond;
    private Button btn_startThird;
    private EditText text_param;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        btn_startSecond = (Button) findViewById(R.id.btn_startSecond);
        btn_startSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


        btn_startThird = (Button) findViewById(R.id.btn_startThirdWithParam);
        text_param = (EditText) findViewById(R.id.text_param);
        btn_startThird.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("param", text_param.getText());
                startActivityForResult(intent, 0);
            }
        });

        tv_result = (TextView) findViewById(R.id.tv_result);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tv_result.setText(data.getExtras().get("returnData") + "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
