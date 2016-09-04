package cn.hylstudio.android.testdatabase.daoimpl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.hylstudio.android.testdatabase.WordsApplication;
import cn.hylstudio.android.testdatabase.dao.WordDao;
import cn.hylstudio.android.testdatabase.model.DB_words;
import cn.hylstudio.android.testdatabase.model.Word;
import cn.hylstudio.android.testdatabase.util.Util;

/**
 * Created by HYL on 2016/9/4.
 */
public class WordDaoImpl implements WordDao {
    public static final String TAG = "DB";
    private static WordsDBHelper db;
    private Context context;


    public WordDaoImpl() {
        Log.d(TAG, "WordDaoImpl: constructor 1");
        context = WordsApplication.getContext();
        db = new WordsDBHelper(context);
        Log.d(TAG, "WordDaoImpl: constructor 1 end");
    }

    public WordDaoImpl(Context context) {
        Log.d(TAG, "WordDaoImpl: constructor 2");
        this.context = context;
        db = new WordsDBHelper(context);
        Log.d(TAG, "WordDaoImpl: constructor 2 end");
    }


    @Override
    public int addWord(Word word) {
        Log.d(TAG, "addWord: " + word.getWord());
        String sql = "insert into words(word, meaning, sample) values(?,?,?)";
        SQLiteDatabase db = WordDaoImpl.db.getWritableDatabase();
        db.execSQL(sql, new String[]{word.getWord(), word.getMeaning(), word.getSample()});
        return 0;
    }

    @Override
    public int delWord(int wordId) {
        String sql = "delete from words where _id=" + wordId;
        SQLiteDatabase db = WordDaoImpl.db.getReadableDatabase();
        db.execSQL(sql);
        return 0;
    }

    @Override
    public int delWord(String wordContent) {
        SQLiteDatabase db = WordDaoImpl.db.getReadableDatabase();
        String sql = "delete from words where word='" + wordContent+"'";
        db.execSQL(sql);
        return 0;
    }

    @Override
    public int updateWord(Word word) {
        SQLiteDatabase db = WordDaoImpl.db.getReadableDatabase();
        String sql = "update words set word=?,meaning=?,sample=? where _id=" + word.getId();
        db.execSQL(sql, new String[]{word.getWord(), word.getMeaning(), word.getSample(),});
        return 0;
    }

    @Override
    public Word getWordById(int wordId) {
        Word w = null;
        SQLiteDatabase db = WordDaoImpl.db.getReadableDatabase();
        String sql = "select * from words where _ID=?";
        Cursor cursor = db.rawQuery(sql, new String[]{wordId + ""});
        if (cursor.moveToNext()) {
            w = new Word();
            w.setId(cursor.getInt(cursor.getColumnIndex(DB_words.T_word._ID)));
            w.setWord(cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_WORD)));
            w.setMeaning(cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_MEANING)));
            w.setSample(cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_SAMPLE)));
        }
        close();
        return w;
    }

    @Override
    public Word getWordByContent(String word) {
        Word w = null;
        SQLiteDatabase db = WordDaoImpl.db.getReadableDatabase();
        Log.d(TAG, "getWordByContent: " + WordDaoImpl.db);
        Log.d(TAG, "getWordByContent: " + db);
        String sql = "select * from words where word=?";
        Cursor cursor = db.rawQuery(sql, new String[]{word});
        if (cursor.moveToNext()) {
            w = new Word();
            w.setId(cursor.getInt(cursor.getColumnIndex(DB_words.T_word._ID)));
            w.setWord(cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_WORD)));
            w.setMeaning(cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_MEANING)));
            w.setSample(cursor.getString(cursor.getColumnIndex(DB_words.T_word.COLUMN_NAME_SAMPLE)));
        }
        close();
        return w;
    }

    public void close() {
        if (db != null)
            db.close();
    }

    @Override
    public List<Word> getAllWords() {
        SQLiteDatabase db = WordDaoImpl.db.getReadableDatabase();

        String[] projection = {
                DB_words.T_word._ID,
                DB_words.T_word.COLUMN_NAME_WORD,
                DB_words.T_word.COLUMN_NAME_MEANING,
                DB_words.T_word.COLUMN_NAME_SAMPLE,
        };

        //排序
        String sortOrder =
                DB_words.T_word.COLUMN_NAME_WORD + " ASC";

        Cursor c = db.query(
                DB_words.T_word.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return Util.convertCursor2WordList(c);
    }

    //使用Sql语句更新单词
    public void UpdateUseSql(String strId, String strWord, String strMeaning, String strSample) {
        SQLiteDatabase db = WordDaoImpl.db.getReadableDatabase();
        String sql = "update words set word=?,meaning=?,sample=? where _id=?";
        db.execSQL(sql, new String[]{strWord, strMeaning, strSample, strId});
    }
}
