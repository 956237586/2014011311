package cn.hylstudio.android.testfragment;


import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.hylstudio.android.testfragment.word.WordContent;


public class MainActivity extends AppCompatActivity
        implements ItemFragment.OnListFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onListFragmentInteraction(WordContent.WordItem item) {
        Bundle arguments = new Bundle();
        arguments.putString("id", item.id);
        arguments.putString("content", item.content);
        Fragment fragment = new DetailFragment();
        fragment.setArguments(arguments);
        getFragmentManager().beginTransaction().replace(R.id.worddetail, fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
