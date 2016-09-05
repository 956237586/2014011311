/***
 * 22：编程项目-访问“联系人”信息-具体内容
 * 23：编程项目-使用ContentObserver-具体内容
 * <p/>
 * created by hyl on 2016/9/5
 */
package cn.hylstudio.android.testcontactcontentprovider;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv_log;
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = getContentResolver();

        getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI,
                true, new ContactsObserver(new Handler()));

        tv_log = (TextView) findViewById(R.id.tv_log);

        Button btn_getAllContact = (Button) findViewById(R.id.btn_get_all);
        btn_getAllContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                String msg = "";
                if (cursor == null) return;
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    msg += "id:" + id + " name:" + name + "\n";
                }
                cursor.close();
                tv_log.setText(msg);
            }
        });

    }

    private final class ContactsObserver extends ContentObserver {
        public ContactsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            Log.v("HYL tag", "Contact data is changed.");
            Toast.makeText(MainActivity.this, "Contact data is changed.", Toast.LENGTH_SHORT).show();
        }


    }

}
