package cn.hylstudio.android.testdatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.hylstudio.android.testdatabase.R;
import cn.hylstudio.android.testdatabase.model.Word;

/**
 * Created by HYL on 2016/9/4.
 */
public class WordListAdapter extends BaseAdapter {
    public static final String TAG = "adapter";
    private List<Word> wordList;
    private Context context;
    private ViewHolder viewHolder;

    public WordListAdapter(Context context, List<Word> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public Object getItem(int position) {
        return wordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return wordList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.word_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_word = (TextView) convertView.findViewById(R.id.tv_item_word);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_word.setText(wordList.get(position).getWord());
        return convertView;
    }

    class ViewHolder {
        private TextView tv_word;
    }
}
