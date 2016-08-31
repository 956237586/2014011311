package cn.hylstudio.android.androidlifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    private Intent intent;
    private TextView tv_param;
    private Button btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        tv_param = (TextView) findViewById(R.id.tv_param);
        tv_param.setText("获得到的参数是" + bundle.get("param"));

        btn_return = (Button) findViewById(R.id.btn_return_first1);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("returnData", "this is return data");
                setResult(0, intent);
                finish();
            }
        });
    }
}
