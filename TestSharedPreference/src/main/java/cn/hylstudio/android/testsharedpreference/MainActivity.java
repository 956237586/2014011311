/**
 * created by HYL
 * 2016.9.2
 */
package cn.hylstudio.android.testsharedpreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences s;
    public static final String FILE_NAME = "testfile";
    public static final String KEY_NAME = "name";
    public static final String KEY_TEST = "test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_read = (Button) findViewById(R.id.btn_read);
        Button btn_write = (Button) findViewById(R.id.btn_write);

        s = getSharedPreferences("test", Activity.MODE_PRIVATE);
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = s.getString(KEY_NAME, "default name");
                String test = s.getString(KEY_TEST, "default test");
                Toast.makeText(MainActivity.this, name + ":" + test, Toast.LENGTH_SHORT).show();
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t = (TextView) findViewById(R.id.text_test);
                SharedPreferences.Editor e = s.edit();
                e.putString(KEY_NAME, "hyl");
                e.putString(KEY_TEST, t.getText() + "");
                e.apply();
                e.commit();
            }
        });


        Button btn_readFile = (Button) findViewById(R.id.btn_read_file);
        Button btn_writeFile = (Button) findViewById(R.id.btn_write_file);
        btn_readFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = openFileInput(FILE_NAME);
                    byte[] b = new byte[50];
                    fis.read(b);
                    fis.close();
                    Toast.makeText(MainActivity.this, new String(b), Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_writeFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = openFileOutput(FILE_NAME, MODE_ENABLE_WRITE_AHEAD_LOGGING);
                    String data = "hyl 2014011311";
                    fos.write(data.getBytes());
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
