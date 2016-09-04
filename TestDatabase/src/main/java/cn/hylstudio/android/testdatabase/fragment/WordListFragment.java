package cn.hylstudio.android.testdatabase.fragment;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hylstudio.android.testdatabase.R;
import cn.hylstudio.android.testdatabase.adapter.WordListAdapter;
import cn.hylstudio.android.testdatabase.dao.WordDao;
import cn.hylstudio.android.testdatabase.daoimpl.WordDaoImpl;
import cn.hylstudio.android.testdatabase.daoimpl.WordsDBHelper;
import cn.hylstudio.android.testdatabase.model.Word;
import cn.hylstudio.android.testdatabase.util.Util;

/**
 * Created by HYL on 2016/9/4.
 */
public class WordListFragment extends ListFragment {
    public static final String TAG = "fragment_word";
    private itemListener itemListener;
    private List<Word> words;
    private WordDao db;

    private EditText text_word;
    private EditText text_meaning;
    private EditText text_sample;

    private Word w;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: begin");
        db = new WordDaoImpl(getActivity());
        words = db.getAllWords();
//        for (int i = 0; i < 20; i++) {
//            words.add(new Word(i, "apple" + i, "苹果" + i, "this is an apple" + i));
//        }
        Log.d(TAG, "onCreate: end");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: begin");
        View view = inflater.inflate(R.layout.word_list, container, false);
        if (view instanceof ListView) {
            setListAdapter(new WordListAdapter(getActivity(), words));
            ListView list_words = (ListView) view;
            registerForContextMenu(list_words);
        }
        Log.d(TAG, "onCreateView: end");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: begin");
        if (context instanceof itemListener) {
            itemListener = (itemListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement itemListener");
        }
        Log.d(TAG, "onAttach: end");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        itemListener = null;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.word_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        View clickedItem = info.targetView;
        TextView tv_word = (TextView) clickedItem.findViewById(R.id.tv_item_word);
        switch (item.getItemId()) {
            case R.id.menu_item_update_word:
                updateWord(tv_word.getText() + "");
                Log.d(TAG, "onContextItemSelected:updateWord: " + tv_word.getText());
                break;
            case R.id.menu_item_delete_word:
                db.delWord(tv_word.getText() + "");
                refreshList();
                Log.d(TAG, "onContextItemSelected:delWord: " + tv_word.getText());
                break;
        }
        return true;
    }


    private void updateWord(final String word) {
        w = db.getWordByContent(word);
        View updateWordDialog = getActivity().getLayoutInflater().inflate(R.layout.word_dialog, null);
        text_word = (EditText) updateWordDialog.findViewById(R.id.text_dialog_word);
        text_meaning = (EditText) updateWordDialog.findViewById(R.id.text_dialog_meaning);
        text_sample = (EditText) updateWordDialog.findViewById(R.id.text_dialog_sample);
        text_word.setText(w.getWord());
        text_meaning.setText(w.getMeaning());
        text_sample.setText(w.getSample());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("修改单词");//标题
        builder.setView(updateWordDialog);//设置视图
        //确定按钮及其动作
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Word newWord = db.getWordByContent(w.getWord());
                newWord.setWord(text_word.getText() + "");
                newWord.setMeaning(text_meaning.getText() + "");
                newWord.setSample(text_sample.getText() + "");
                db.updateWord(newWord);
                //单词已经插入到数据库，更新显示列表
                refreshList(w.getWord());
                Log.d(TAG, "onClick: update word" + text_word.getText());
            }
        });
        //取消按钮及其动作
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: update word cancel");
            }
        });
        builder.create();//创建对话框
        builder.show();//显示对话框
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        itemListener.onItemClick(((TextView) v).getText() + "");
    }

    public void refreshList() {
        words = db.getAllWords();
        setListAdapter(new WordListAdapter(getActivity(), words));
    }

    public void refreshList(String wordContent) {
        refreshList();
        if (Util.isLand(getActivity())) {
            itemListener.onItemClick(wordContent);
        }
    }

    public interface itemListener {
        void onItemClick(String wordContent);
    }
}
