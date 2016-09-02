/**
 * created by HYL
 * 17：编程项目-[上机实践]SharedPreferences-具体内容
 * 18：编程项目-[上机实践]内部存储文件操作-具体内容
 * 19：编程项目-[上机实践]外部存储文件操作-具体内容
 * 2016.9.2
 */
package cn.hylstudio.android.testsharedpreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import cn.hylstudio.android.testsharedpreference.Utils.Util;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences s;
    private EditText t;
    public static final String FILE_NAME = "testfile";
    public static final String KEY_NAME = "name";
    public static final String KEY_TEST = "test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = (EditText) findViewById(R.id.text_test);
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

                    String data = t.getText() + "";
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


        Button btn_readFileFromSD = (Button) findViewById(R.id.btn_read_file_from_sd);
        Button btn_writeFileToSD = (Button) findViewById(R.id.btn_write_file_to_sd);

        btn_readFileFromSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try {
                    if (Util.isExternalStorageReadable()) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/HYL.txt");
                        FileInputStream fileInputStream = new FileInputStream(myfile);
                        in = new BufferedInputStream(fileInputStream);
                        int c;
                        StringBuilder stringBuilder = new StringBuilder("");
                        while ((c = in.read()) != -1) {
                            stringBuilder.append((char) c);
                        }
                        Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                        in.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_writeFileToSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try {
                    if (Util.isExternalStorageWritable()) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/HYL.txt");
                        FileOutputStream fileOutputStream = new FileOutputStream(myfile);
                        out = new BufferedOutputStream(fileOutputStream);
                        String content = t.getText() + "";
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

