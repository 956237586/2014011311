package cn.hylstudio.android.testdatabase.models;

import android.provider.BaseColumns;

/**
 * Created by HYL on 2016/9/2.
 */
public class DB_words {
    public DB_words() {
    }

    public static abstract class T_word implements BaseColumns {
        public static final String TABLE_NAME="words";
        public static final String COLUMN_NAME_WORD="word";//列：单词
        public static final String COLUMN_NAME_MEANING="meaning";//列：单词含义
        public static final String COLUMN_NAME_SAMPLE="sample";//单词示例
    }

}
