/**
 * 20：编程项目-[上机实践]SQLite数据库使用-具体内容
 * 21：编程项目-[上机实践]ContentProvider-具体内容
 * created by hyl on 2016/9/2
 */
package cn.hylstudio.android.testdatabase.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import cn.hylstudio.android.testdatabase.R;
import cn.hylstudio.android.testdatabase.dao.WordDao;
import cn.hylstudio.android.testdatabase.daoimpl.WordDaoImpl;
import cn.hylstudio.android.testdatabase.daoimpl.WordsDBHelper;
import cn.hylstudio.android.testdatabase.fragment.WordDetailFragment;
import cn.hylstudio.android.testdatabase.fragment.WordListFragment;
import cn.hylstudio.android.testdatabase.model.Word;
import cn.hylstudio.android.testdatabase.util.Util;

public class MainActivity extends AppCompatActivity implements WordListFragment.itemListener {
    public static final String TAG = "mainActivity";
    private WordDao db;
    private View addWordDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new WordDaoImpl(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemClick(String wordContent) {
        Word w = db.getWordByContent(wordContent);
        if (Util.isLand(this)) {
            updateDetailFragment(w);
        } else {
            Intent intent = new Intent(MainActivity.this, WordDetailActivity.class);
            intent.putExtra("word", w);
            startActivity(intent);
        }
        Log.d(TAG, "onItemClick: " + wordContent);
    }

    private void updateDetailFragment(Word w) {
        Bundle args = new Bundle();
        args.putSerializable("word", w);
        WordDetailFragment fragment = new WordDetailFragment();
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.frag_word_detail, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean ret = false;
        if (item.getItemId() == R.id.menu_item_add_word) {
            Log.d(TAG, "onOptionsItemSelected: add word");
            showAddWordDialog();
            ret = true;
        }
        return ret;
    }

    private void showAddWordDialog() {
        addWordDialog = getLayoutInflater().inflate(R.layout.word_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新增单词");//标题

        builder.setView(addWordDialog);//设置视图
        //确定按钮及其动作
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText text_word = (EditText) addWordDialog.findViewById(R.id.text_dialog_word);
                EditText text_meaning = (EditText) addWordDialog.findViewById(R.id.text_dialog_meaning);
                EditText text_sample = (EditText) addWordDialog.findViewById(R.id.text_dialog_sample);
                db.addWord(new Word(0, text_word.getText() + "", text_meaning.getText() + "", text_sample.getText() + ""));
                //单词已经插入到数据库，更新显示列表
                refreshWordListFragment();
                Log.d(TAG, "onClick: add word" + text_word.getText());
            }
        });
        //取消按钮及其动作
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: add word cancel");
            }
        });
        builder.create();//创建对话框
        builder.show();//显示对话框
    }


    /**
     * 更新单词列表
     */
    private void refreshWordListFragment() {
        WordListFragment wordListFragment = (WordListFragment) getFragmentManager().findFragmentById(R.id.frag_word_list);
        wordListFragment.refreshList();
    }

    /**
     * 更新单词列表
     */
    private void refreshWordListFragment(String wordContent) {
        WordListFragment wordListFragment = (WordListFragment) getFragmentManager().findFragmentById(R.id.frag_word_list);
        wordListFragment.refreshList(wordContent);
    }
}
