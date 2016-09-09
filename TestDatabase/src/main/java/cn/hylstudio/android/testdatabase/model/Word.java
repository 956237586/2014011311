package cn.hylstudio.android.testdatabase.model;

import java.io.Serializable;

/**
 * Created by HYL on 2016/9/4.
 */
public class Word implements Serializable {
    private int id;
    private String word;
    private String meaning;
    private String sample;

    public Word() {
    }

    public Word(int id, String word, String meaning, String sample) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.sample = sample;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
