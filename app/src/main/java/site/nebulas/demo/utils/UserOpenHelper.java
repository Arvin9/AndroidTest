package site.nebulas.demo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nebula on 2017/10/5.
 */

public class UserOpenHelper extends SQLiteOpenHelper {

    /**
     * @param context
     * name 数据库名
     * factory 目的创建cursor对象
     * version 数据库版本 从1开始
     * */
    public UserOpenHelper(Context context) {
        super(context, "nebulas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user_info(_id integer primary key autoincrement, name varchar(20)," +
                " password varchar(20))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "alter table user add desc varchar(20)";
        db.execSQL(sql);
    }
}
