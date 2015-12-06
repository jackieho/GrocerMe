package com.jackieho.grocerme;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by jackieho on 2015-12-03.
 */
public class UserDatabase {

    public static final String TAG = "UserDatabase";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = { User.KEY_id,
            User.KEY_name, User.KEY_username };

    public UserDatabase(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public boolean createUser(String name, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(User.KEY_name, name);
        values.put(User.KEY_username, username);
        values.put(User.KEY_password, password);
        long insertId = mDatabase.insert(User.TABLE_USER, null, values);
        if(insertId == -1)
            return false;
        else
            return true;
    }

    public List<User> getAllUsers() {
        List<User> listCompanies = new ArrayList<User>();

        Cursor cursor = mDatabase.query(User.TABLE_USER, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User my_user = cursorToUser(cursor);
                listCompanies.add(my_user);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listCompanies;
    }

    public User getUserById(long id) {
        Cursor cursor = mDatabase.query(User.TABLE_USER, mAllColumns,
                User.KEY_id + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = cursorToUser(cursor);
        return user;
    }

    protected User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setUserId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        user.setUsername(cursor.getString(2));
        user.setPassword(cursor.getString(3));
        return user;
    }

}