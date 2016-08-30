/**
 * created by hyl
 * 6：编程项目-[上机实践]菜单-具体内容
 * 7：编程项目-[上机实践]对话框-具体内容
 * 2016.8.30
 */
package cn.hylstudio.android.menuanddialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class TestMenuAndDialogActivity extends AppCompatActivity
        implements PopupMenu.OnMenuItemClickListener {
    private Button btn_testContextMenu;
    private Button btn_testPopMenu;
    private Button btn_dialog;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu_and_dialog);
        btn_testContextMenu = (Button) findViewById(R.id.btn_test_context_menu);
        registerForContextMenu(btn_testContextMenu);

        btn_testPopMenu = (Button) findViewById(R.id.btn_test_pop_menu);
        btn_testPopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popMenu = new PopupMenu(TestMenuAndDialogActivity.this, view);
                popMenu.setOnMenuItemClickListener(TestMenuAndDialogActivity.this);
                popMenu.inflate(R.menu.pop_menu);
                popMenu.show();
            }
        });


        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestMenuAndDialogActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.dialog_alert, null));
                builder.setTitle("alert");
                builder.show();
            }
        });


        btn_login = (Button) findViewById(R.id.btn_login_dialog);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestMenuAndDialogActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_login, null);
                builder.setView(dialogView);
                builder.setTitle("login");

                builder.setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText text_username = (EditText) dialogView.findViewById(R.id.text_username);
                        EditText text_password = (EditText) dialogView.findViewById(R.id.text_password);
                        String username = text_username.getText() + "";
                        String password = text_password.getText() + "";
                        if (username.equals("abc") && password.equals("123")) {
                            Toast.makeText(TestMenuAndDialogActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TestMenuAndDialogActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                        }
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TestMenuAndDialogActivity.this, "取消登陆", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
                builder.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
