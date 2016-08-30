/**
 * created by hyl
 * 2016.8.30
 */
package cn.hylstudio.android.changetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeTextActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text);
        Button btn_changeText = (Button) findViewById(R.id.btn_changeText);
        tv = (TextView) findViewById(R.id.tv);

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
}
