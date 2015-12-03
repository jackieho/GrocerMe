package com.jackieho.grocerme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jackieho on 2015-12-03.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // add all tables here
        String CREATE_TABLE_USERS = "CREATE TABLE " + User.TABLE + "("
                + User.KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + User.KEY_name + " TEXT, "
                + User.KEY_username + "TEXT"
                + User.KEY_password + "TEXT";

        db.execSQL(CREATE_TABLE_USERS);

    }
}