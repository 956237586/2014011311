package cn.hylstudio.android.testdatabase.dao;

import java.util.ArrayList;
import java.util.List;

import cn.hylstudio.android.testdatabase.model.Word;

/**
 * Created by HYL on 2016/9/4.
 */
public interface WordDao {
    /**
     * 增加一个单词
     * @param word 新增的单词
     * @return 0成功 1失败
     */
    int addWord(Word word);

    /**
     * 删除一个单词
     * @param wordId 单词id
     * @return 0成功 1失败
     */
    int delWord(int wordId);

    /**
     * 删除一个单词
     * @param wordContent 单词
     * @return 0成功 1失败
     */
    int delWord(String wordContent);


    /**
     * 更新单词
     * @param word 要更新的单词
     * @return 0成功 1失败
     */
    int updateWord(Word word);

    /**
     * 获得一个单词
     * @param wordId id
     * @return 单词
     */
    Word getWordById(int wordId);

    /**
     * 获得一个单词
     * @param word 单词内容
     * @return 单词
     */
    Word getWordByContent(String word);

    /**
     * 获得所有单词
     * @return 单词列表
     */
    List<Word> getAllWords();
}
