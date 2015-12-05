package com.jackieho.grocerme;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class CouponDatabase {

    public static final String TAG = "CouponDatabase";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { Coupon.KEY_coupon_id, Coupon.KEY_username, Coupon.KEY_item_name, Coupon.KEY_description,
            Coupon.KEY_item_price, Coupon.KEY_amount };

    public CouponDatabase(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public boolean createCoupon(String username, String item_name, String description, double item_price, int amount) {
        ContentValues values = new ContentValues();
        values.put(Coupon.KEY_username, username);
        values.put(Coupon.KEY_item_name, item_name);
        values.put(Coupon.KEY_description, description);
        values.put(Coupon.KEY_item_price, item_price);
        values.put(Coupon.KEY_amount, amount);
        long insertId = mDatabase.insert(Coupon.TABLE_LIST, null, values);
        if(insertId == -1)
            return false;
        else
            return true;
    }

    public void deleteCoupon(Coupon coupon) {
        long id = coupon.getCouponId();
        mDatabase.delete(Coupon.TABLE_LIST, User.KEY_id + " = " + id, null);
    }

    public List<Coupon> getAllCoupons() {
        List<Coupon> listCoupons = new ArrayList<Coupon>();

        Cursor cursor = mDatabase.query(Coupon.TABLE_LIST, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Coupon coupon = cursorToCoupon(cursor);
            listCoupons.add(coupon);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listCoupons;
    }

    public List<Coupon> getCouponsOfUser(long userId) {
        List<Coupon> listCoupons = new ArrayList<Coupon>();

        Cursor cursor = mDatabase.query(Coupon.TABLE_LIST, mAllColumns,
                User.KEY_id + " = ?",
                new String[] { String.valueOf(userId) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Coupon cur_coupon = cursorToCoupon(cursor);
            listCoupons.add(cur_coupon);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listCoupons;
    }

    public static Coupon cursorToCoupon(Cursor cursor) {
        Coupon my_coupon = new Coupon();
        //my_coupon.setCouponId(cursor.getLong(0));
        my_coupon.setAmount(cursor.getInt(5));
        my_coupon.setItemName(cursor.getString(2));
        my_coupon.setDescription(cursor.getString(4));
        my_coupon.setItemPrice(cursor.getDouble(3));


        // get The company by id
        /*long userId = cursor.getLong(5);
        UserDatabase dao = new UserDatabase(mContext);
        User user = dao.getUserById(userId);
        if (user != null)
            my_coupon.setUser(user);*/

        return my_coupon;
    }

}
