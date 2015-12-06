package com.jackieho.grocerme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackieho on 2015-12-03.
 */
public class DBHelper extends SQLiteOpenHelper {

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

    private static final String CREATE_TABLE_LIST = "CREATE TABLE " + Coupon.TABLE_LIST + "("
            + Coupon.KEY_coupon_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Coupon.KEY_username + " TEXT, "
            + Coupon.KEY_item_name + " TEXT, "
            + Coupon.KEY_item_price + " DECIMAL, "
            + Coupon.KEY_description + " TEXT, "
            + Coupon.KEY_amount + " INTEGER "
            + ");";

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_USERS);
        database.execSQL(CREATE_TABLE_LIST);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + Coupon.TABLE_LIST);

        onCreate(db);
    }

    public boolean deleteCoupons (String table_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int deleted = db.delete(table_name, null, null);
        if (deleted == 0)
        {
            return false;
        }
        return true;
    }

    public DBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public boolean inDatabase(String table_name, String username, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + table_name + " WHERE Username = ? AND password = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username, pass});

        if (cursor.getCount() == 0)
        {
            return false;
        }
        return true;
    }

    public boolean checkDupl(String table_name, String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + table_name + " WHERE Username = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor.getCount() == 0)
        {
            // there is a duplicate
            return false;
        }
        else
        {
            return true;
        }
    }

    public List<Coupon> getCouponsOfUser(String table_name, String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Coupon> listCoupons = new ArrayList<Coupon>();

        String query = "SELECT * FROM " + table_name + " WHERE USERNAME = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username});

        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            Coupon cur_coupon = CouponDatabase.cursorToCoupon(cursor);
            listCoupons.add(cur_coupon);
            cursor.moveToNext();
        }
        cursor.close();
        return listCoupons;
    }

}