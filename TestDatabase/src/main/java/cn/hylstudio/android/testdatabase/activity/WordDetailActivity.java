package cn.hylstudio.android.testdatabase.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.hylstudio.android.testdatabase.R;
import cn.hylstudio.android.testdatabase.fragment.WordDetailFragment;

/**
 * Created by HYL on 2016/9/4.
 */
public class WordDetailActivity extends AppCompatActivity {
    public static final String TAG = "word Detail activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WordDetailFragment detailFragment = new WordDetailFragment();
        detailFragment.setArguments(getIntent().getExtras());
        getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, detailFragment)
                .commit();
    }
}
