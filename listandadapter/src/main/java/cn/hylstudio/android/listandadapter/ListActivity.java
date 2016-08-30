/**
 * created by hyl
 * 4：编程项目-[上机实践]学会使用ListView-具体内容
 * 5：编程项目-[上机实践，选做]使用RecyclerView显示数据。-具体内容（没写
 *
 * 2016.8.30
 */
package cn.hylstudio.android.listandadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private ListView list;
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String DETAIL = "detail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list = (ListView) findViewById(R.id.list_test);
        String[] array = new String[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + "";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_list, array);
        //list.setAdapter(adapter);

        int size = 10;
        String[] names = new String[size];
        String[] prices = new String[size];
        String[] details = new String[size];
        List<Map<String, String>> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            names[i] = NAME + i;
            prices[i] = PRICE + i;
            details[i] = DETAIL + i + DETAIL + i + DETAIL + i + DETAIL + i;

            HashMap<String, String> map = new HashMap<>();
            map.put(NAME, names[i]);
            map.put(PRICE, prices[i]);
            map.put(DETAIL, details[i]);

            items.add(map);
        }

        SimpleAdapter adapter1 = new SimpleAdapter(this,
                items,
                R.layout.item_detail,
                new String[]{NAME, PRICE, DETAIL},
                new int[]{R.id.tv_name, R.id.tv_price, R.id.tv_detail}
        );
        list.setAdapter(adapter1);
    }
}

