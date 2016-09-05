/**
 * 21：编程项目-[上机实践]ContentProvider-具体内容
 * created by hyl on 2015/9/5
 */
package cn.hylstudio.android.testcontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyWordsTag";
    private ContentResolver resolver;
    private TextView tv_log;
    private EditText text_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = this.getContentResolver();

        Button buttonAll = (Button) findViewById(R.id.btn_get_all);
        Button buttonInsert = (Button) findViewById(R.id.buttonInsert);
        Button buttonDelete = (Button) findViewById(R.id.btn_del);
        Button buttonDeleteAll = (Button) findViewById(R.id.btn_del_all);
        Button buttonUpdate = (Button) findViewById(R.id.btn_update);
        Button buttonQuery = (Button) findViewById(R.id.btn_query);
        tv_log = (TextView) findViewById(R.id.tv_log);
        text_id = (EditText) findViewById(R.id.text_id);
        //得到全部
        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = resolver.query(DB_words.T_word.CONTENT_URI,
                        new String[]{DB_words.T_word._ID, DB_words.T_word.COLUMN_NAME_WORD,
                                DB_words.T_word.COLUMN_NAME_MEANING, DB_words.T_word.COLUMN_NAME_SAMPLE},
                        null, null, null);
                if (cursor == null) {
                    Toast.makeText(MainActivity.this, "没有找到记录", Toast.LENGTH_LONG).show();
                    return;
                }

                //找到记录，这里简单起见，使用Log输出

                String msg = "";
                int flag = 1;
                if (cursor.moveToFirst()) {
                    do {
                        if (flag == 1) {
                            flag = 0;
                            text_id.setText(cursor.getInt(cursor.getColumnIndex(DB_words.T_word._ID)) + "");
                        }
                        msg += "ID:" + cursor.getInt(cursor.getColumnIndex(DB_words.T_word._ID)) + ",";
                        msg += "单词：" + cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_WORD)) + ",";
                        msg += "含义：" + cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_MEANING)) + ",";
                        msg += "示例" + cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_SAMPLE)) + "\n";
                    } while (cursor.moveToNext());
                }

                Log.v(TAG, msg);
                tv_log.setText(msg);

            }
        });

        //增加
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strWord = "Banana";
                String strMeaning = "banana";
                String strSample = "This banana is very nice.";
                ContentValues values = new ContentValues();

                values.put(DB_words.T_word.COLUMN_NAME_WORD, strWord);
                values.put(DB_words.T_word.COLUMN_NAME_MEANING, strMeaning);
                values.put(DB_words.T_word.COLUMN_NAME_SAMPLE, strSample);

                Uri newUri = resolver.insert(DB_words.T_word.CONTENT_URI, values);
                Toast.makeText(MainActivity.this, "add word banana", Toast.LENGTH_SHORT).show();
            }
        });

        //删除
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = text_id.getText() + "";//简单起见，这里指定ID，用户可在程序中设置id的实际值
                Uri uri = Uri.parse(DB_words.T_word.CONTENT_URI_STRING + "/" + id);
                int result = resolver.delete(uri, null, null);
                Toast.makeText(MainActivity.this, "del word " + id, Toast.LENGTH_SHORT).show();
            }
        });

        //删除全部
        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resolver.delete(DB_words.T_word.CONTENT_URI, null, null);
                Toast.makeText(MainActivity.this, "del all", Toast.LENGTH_SHORT).show();
            }
        });

        //修改
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = text_id.getText() + "";
                String strWord = "Banana";
                String strMeaning = "banana123";
                String strSample = "This banana is very nice.";
                ContentValues values = new ContentValues();

                values.put(DB_words.T_word.COLUMN_NAME_WORD, strWord);
                values.put(DB_words.T_word.COLUMN_NAME_MEANING, strMeaning);
                values.put(DB_words.T_word.COLUMN_NAME_SAMPLE, strSample);

                Uri uri = Uri.parse(DB_words.T_word.CONTENT_URI_STRING + "/" + id);
                int result = resolver.update(uri, values, null, null);
                Toast.makeText(MainActivity.this, "update words" + id, Toast.LENGTH_SHORT).show();
            }
        });

        //根据id查询
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = text_id.getText() + "";
                Uri uri = Uri.parse(DB_words.T_word.CONTENT_URI_STRING + "/" + id);
                Cursor cursor = resolver.query(DB_words.T_word.CONTENT_URI,
                        new String[]{DB_words.T_word._ID, DB_words.T_word.COLUMN_NAME_WORD,
                                DB_words.T_word.COLUMN_NAME_MEANING, DB_words.T_word.COLUMN_NAME_SAMPLE},
                        "_ID=?", new String[]{id}, null);
                if (cursor == null) {
                    Toast.makeText(MainActivity.this, "没有找到记录", Toast.LENGTH_LONG).show();
                    return;
                }

                //找到记录，这里简单起见，使用Log输出

                String msg = "";
                if (cursor.moveToFirst()) {
                    do {
                        msg += "ID:" + cursor.getInt(cursor.getColumnIndex(DB_words.T_word._ID)) + ",";
                        msg += "单词：" + cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_WORD)) + ",";
                        msg += "含义：" + cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_MEANING)) + ",";
                        msg += "示例" + cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_SAMPLE)) + "\n";
                    } while (cursor.moveToNext());
                }

                Log.v(TAG, msg);
                tv_log.setText(msg);
            }
        });
    }

}
