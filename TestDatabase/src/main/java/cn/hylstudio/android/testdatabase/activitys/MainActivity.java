/**
 * created by hyl on 2016/9/2
 */
package cn.hylstudio.android.testdatabase.activitys;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import cn.hylstudio.android.testdatabase.R;
import cn.hylstudio.android.testdatabase.WordsDBHelper;

public class MainActivity extends AppCompatActivity {
    WordsDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //为ListView注册上下文菜单
        ListView list = (ListView) findViewById(R.id.list_words);
        registerForContextMenu(list);
        //创建SQLiteOpenHelper对象，注意第一次运行时，此时数据库并没有被创建
        mDbHelper = new WordsDBHelper(this);
        Log.d("123123123123", "onCreate: mainactivity");
        SQLiteDatabase db_words = mDbHelper.getReadableDatabase();
    }

}
