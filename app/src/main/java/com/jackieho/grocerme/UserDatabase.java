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

/**
 * Created by jackieho on 2015-12-03.
 */
public class UserDatabase {

    public static final String TAG = "CompanyDAO";

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

    public User createUser(String name, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(User.KEY_name, name);
        values.put(User.KEY_username, username);
        values.put(User.KEY_password, password);
        long insertId = mDatabase
                .insert(User.TABLE_USER, null, values);
        Cursor cursor = mDatabase.query(User.TABLE_USER, mAllColumns,
                User.KEY_id + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToCompany(cursor);
        cursor.close();
        return newUser;
    }

    public void deleteUser(User user) {
        long id = User.KEY_id.getId();
        // delete all employees of this company
        UserDatabase currentUser = new UserDatabase(mContext);
        List<User> listUsers = currentUser.getCoupons(id);
        if (listUsers != null && !listUsers.isEmpty()) {
            for (User e : listUsers) {
                currentUser.deleteUser(e);
            }
        }

        System.out.println("the deleted user has the id: " + id);
        mDatabase.delete(User.TABLE_USERS, User.KEY_id
                + " = " + id, null);
    }

    public List<Company> getAllCompanies() {
        List<Company> listCompanies = new ArrayList<Company>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_COMPANIES, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Company company = cursorToCompany(cursor);
                listCompanies.add(company);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listCompanies;
    }

    public Company getCompanyById(long id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_COMPANIES, mAllColumns,
                DBHelper.COLUMN_COMPANY_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Company company = cursorToCompany(cursor);
        return company;
    }

    protected Company cursorToCompany(Cursor cursor) {
        Company company = new Company();
        company.setId(cursor.getLong(0));
        company.setName(cursor.getString(1));
        company.setAddress(cursor.getString(2));
        company.setWebsite(cursor.getString(3));
        company.setPhoneNumber(cursor.getString(4));
        return company;
    }

}