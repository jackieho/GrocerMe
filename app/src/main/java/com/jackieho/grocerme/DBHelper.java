package com.jackieho.grocerme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by jackieho on 2015-12-03.
 */
public abstract class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + User.TABLE_USER + "("
            + User.KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + User.KEY_name + " TEXT, "
            + User.KEY_username + " TEXT, "
            + User.KEY_password + " TEXT "
            + ");";

    private static final String CREATE_TABLE_LIST = "CREATE TABLE " + ShoppingList.TABLE_LIST + "("
            + User.KEY_id + "INTEGER PRIMARY KEY, "
            + ShoppingList.KEY_amount + " INTEGER "
            + ShoppingList.KEY_item + " TEXT, "
            + ShoppingList.KEY_coupon + " TEXT, "
            + ShoppingList.KEY_couponDescription + " TEXT, "
            + ShoppingList.KEY_price + " DECIMAL "
            + ");";

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_USERS);
        database.execSQL(CREATE_TABLE_LIST);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + ShoppingList.TABLE_LIST);

        onCreate(db);
    }

//    public DBHelper(Context context, String name, CursorFactory factory, int version) {
//        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
//    }

}