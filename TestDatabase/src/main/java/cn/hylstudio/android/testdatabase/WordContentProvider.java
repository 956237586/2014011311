package cn.hylstudio.android.testdatabase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.UserDictionary;
import android.support.annotation.NonNull;

import cn.hylstudio.android.testdatabase.dao.WordDao;
import cn.hylstudio.android.testdatabase.daoimpl.WordDaoImpl;
import cn.hylstudio.android.testdatabase.daoimpl.WordsDBHelper;
import cn.hylstudio.android.testdatabase.model.DB_words;

public class WordContentProvider extends ContentProvider {
    //UriMathcher匹配结果码
    private static final int MULTIPLE_WORDS = 1;//
    private static final int SINGLE_WORD = 2;
    private WordsDBHelper dbHelper;
    private SQLiteDatabase db;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(DB_words.AUTHORITY, DB_words.T_word.PATH_SINGLE, SINGLE_WORD);
        uriMatcher.addURI(DB_words.AUTHORITY, DB_words.T_word.PATH_MULTIPLE, MULTIPLE_WORDS);
    }


    public WordContentProvider() {

    }

    //删除数据
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                count = db.delete(DB_words.T_word.TABLE_NAME, selection, selectionArgs);
                break;
            case SINGLE_WORD:
                String whereClause = DB_words.T_word._ID + "=" + uri.getPathSegments().get(1);
                count = db.delete(DB_words.T_word.TABLE_NAME, whereClause, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }
        //通知ContentResolver,数据已经发生改变
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                return DB_words.T_word.MINE_TYPE_MULTIPLE;
            case SINGLE_WORD://单条数据记录
                return DB_words.T_word.MINE_TYPE_SINGLE;
            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(DB_words.T_word.TABLE_NAME, null, values);
        if (id > 0) {
            //在已有的Uri后面添加id
            Uri newUri = ContentUris.withAppendedId(DB_words.T_word.CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new WordsDBHelper(getContext());
        db = dbHelper.getReadableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DB_words.T_word.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                return db.query(DB_words.T_word.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

            case SINGLE_WORD:
                qb.appendWhere(DB_words.T_word._ID + "=" + uri.getPathSegments().get(1));
                return qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);

            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                count = db.update(DB_words.T_word.TABLE_NAME, values, selection, selectionArgs);
                break;
            case SINGLE_WORD:
                String segment = uri.getPathSegments().get(1);
                count = db.update(DB_words.T_word.TABLE_NAME, values, DB_words.T_word._ID + "=" + segment, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }

        //通知ContentResolver,数据已经发生改变
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
