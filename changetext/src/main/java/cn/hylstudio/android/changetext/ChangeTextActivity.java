/**
 * created by hyl
 * 3：编程项目-[上机实践]学会使用常用组件-具体内容
 *
 * 2016.8.30
 */
package cn.hylstudio.android.changetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeTextActivity extends AppCompatActivity {
    private TextView tv;
    private EditText text_test;
    private RadioButton rbtn_1;
    private RadioButton rbtn_2;
    private RadioButton rbtn_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text);

        Button btn_changeText = (Button) findViewById(R.id.btn_changeText);
        tv = (TextView) findViewById(R.id.tv);
        text_test = (EditText) findViewById(R.id.text_test);
        rbtn_1 = (RadioButton) findViewById(R.id.rbtn_1);
        rbtn_2 = (RadioButton) findViewById(R.id.rbtn_2);
        rbtn_3 = (RadioButton) findViewById(R.id.rbtn_3);
        rbtn_1.setOnCheckedChangeListener(new CustomListener());
        rbtn_2.setOnCheckedChangeListener(new CustomListener());
        rbtn_3.setOnCheckedChangeListener(new CustomListener());


        btn_changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(Integer.parseInt(tv.getText() + "") + 1 + "");
            }
        });

    }

    public void customMethod(View view) {
        CheckBox cb = ((CheckBox) view);
        if (cb.isChecked()) {
            text_test.setText(cb.getText());
            switch (cb.getId()) {
                case R.id.cb_1:
                    Toast.makeText(this, "11111已选中", Toast.LENGTH_LONG).show();
                    break;
                case R.id.cb_2:
                    Toast.makeText(this, "22222已选中", Toast.LENGTH_LONG).show();
                    break;
                case R.id.cb_3:
                    Toast.makeText(this, "33333已选中", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }


    private class CustomListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(compoundButton.isChecked()){
                text_test.setText(compoundButton.getText());
            }
        }
    }
}
