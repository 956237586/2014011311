package cn.hylstudio.android.testdatabase.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.hylstudio.android.testdatabase.R;
import cn.hylstudio.android.testdatabase.model.Word;

/**
 * Created by HYL on 2016/9/4.
 */
public class WordDetailFragment extends Fragment {
    public static final String TAG = "word detail";
    private Bundle args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: begin");
        View view = inflater.inflate(R.layout.word_detail, container, false);
        TextView tv_word = (TextView) view.findViewById(R.id.tv_detail_word);
        TextView tv_meaning = (TextView) view.findViewById(R.id.tv_detail_word_meaning);
        TextView tv_sample = (TextView) view.findViewById(R.id.tv_detail_word_sample);
        if (args != null) {
            Word w = (Word) args.getSerializable("word");
            if(w != null){
                tv_word.setText(w.getWord());
                tv_meaning.setText(w.getMeaning());
                tv_sample.setText(w.getSample());
            }
        }
        Log.d(TAG, "onCreateView: end");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
