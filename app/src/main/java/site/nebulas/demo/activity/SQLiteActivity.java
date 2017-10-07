package site.nebulas.demo.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import site.nebulas.demo.R;
import site.nebulas.demo.adapter.UserAdapter;
import site.nebulas.demo.entity.UserInfo;
import site.nebulas.demo.utils.ToastUtil;
import site.nebulas.demo.utils.UserOpenHelper;

/**
 * Created by Nebula on 2017/10/6.
 */

public class SQLiteActivity extends Activity implements View.OnClickListener{

    private final String TABLE_NAME = "user_info";
    private String TAG = "SQLite";

    private UserOpenHelper userOpenHelper;

    private EditText mUsernameAdd;
    private EditText mPasswordAdd;
    private EditText mUsernameUpdate;
    private EditText mPasswordUpdate;
    private EditText mUsernameDelete;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        createSqlite();
        initView();

        mListView = (ListView) findViewById(R.id.show_list);

    }

    /**
     * 创建数据库
     * */
    private void createSqlite() {
        userOpenHelper = new UserOpenHelper(this);
    }

    /**
     * 用户信息增删改查
     * 1、增加:用户名、密码
     * 2、修改:根据用户名修改密码
     * 3、删除:根据用户名删除
     * 4、查询:通过listview显示
     * */
    private void initView() {
        mUsernameAdd = (EditText) findViewById(R.id.username_add);
        mPasswordAdd = (EditText) findViewById(R.id.password_add);
        mUsernameUpdate = (EditText) findViewById(R.id.username_update);
        mPasswordUpdate = (EditText) findViewById(R.id.password_update);
        mUsernameDelete = (EditText) findViewById(R.id.username_delete);

        findViewById(R.id.butn_add).setOnClickListener(this);
        findViewById(R.id.butn_update).setOnClickListener(this);
        findViewById(R.id.butn_delete).setOnClickListener(this);
        findViewById(R.id.butn_query).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butn_add: {
                ContentValues values = new ContentValues();
                String username = mUsernameAdd.getText().toString().trim();
                String passwprd = mPasswordAdd.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(passwprd)) {
                    ToastUtil.toast("用户名/密码不能为空!");
                    return;
                }
                values.put("name", username);
                values.put("password", passwprd);
                SQLiteDatabase sqlite = userOpenHelper.getWritableDatabase();
                long result = sqlite.insert(TABLE_NAME, null, values);
                sqlite.close();
                if (-1 == result) {
                    ToastUtil.toast("添加失败");
                } else {
                    ToastUtil.toast("添加成功");
                }
                break;
            }
            case R.id.butn_update: {
                SQLiteDatabase sqlite = userOpenHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String username = mUsernameUpdate.getText().toString().trim();
                String passwprd = mPasswordUpdate.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(passwprd)) {
                    ToastUtil.toast("用户名/密码不能为空!");
                    return;
                }
                values.put("password", passwprd);
                long result = sqlite.update(TABLE_NAME, values, "name=?", new String[]{username});
                if (0 == result) {
                    ToastUtil.toast("更新失败");
                } else {
                    ToastUtil.toast("更新成功");
                }
                sqlite.close();
                break;
            }
            case R.id.butn_delete: {
                SQLiteDatabase sqlite = userOpenHelper.getWritableDatabase();
                String username = mUsernameDelete.getText().toString().trim();
                int result = sqlite.delete(TABLE_NAME, "name=?", new String[]{username});
                if (0 == result) {
                    ToastUtil.toast("删除失败");
                } else {
                    ToastUtil.toast("删除成功");
                }
                sqlite.close();
                break;
            }
            case R.id.butn_query: {
                SQLiteDatabase sqlite = userOpenHelper.getWritableDatabase();
                Cursor cursor = sqlite.query(TABLE_NAME, new String[]{"name", "password"}, null, null, null, null, null);
                Log.i(TAG, ""+ cursor.getCount());
                String name, password;
                cursor.move(0);
                ArrayList<UserInfo> userInfos = new ArrayList<>();
                while (cursor.moveToNext()) {
                    name = cursor.getString(0);
                    password = cursor.getString(1);
                    Log.i(TAG, "name: " + name + " passord: " + password);
                    userInfos.add(new UserInfo(name, password));
                }
                mListView.setAdapter(new UserAdapter(this, userInfos));
                cursor.close();
                sqlite.close();
                break;
            }
        }
    }
}
