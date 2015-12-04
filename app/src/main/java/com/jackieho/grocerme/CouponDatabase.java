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

    public static final String TAG = "EmployeeDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { Coupon.KEY_item_name, Coupon.KEY_description,
            Coupon.KEY_item_price, Coupon.KEY_amount };

    public CouponDatabase(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
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

    public Coupon createCoupon(String item_name, String description, double item_price, int amount) {
        ContentValues values = new ContentValues();
        values.put(Coupon.KEY_item_name, item_name);
        values.put(Coupon.KEY_description, description);
        values.put(Coupon.KEY_item_price, item_price);
        values.put(Coupon.KEY_amount, amount);
        long insertId = mDatabase
                .insert(Coupon.TABLE_LIST, null, values);
        Cursor cursor = mDatabase.query(Coupon.TABLE_LIST, mAllColumns,
                User.KEY_username + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Coupon newCoupon = cursorToEmploye(cursor);
        cursor.close();
        return newEmployee;
    }

    public void deleteEmployee(Employee employee) {
        long id = employee.getId();
        System.out.println("the deleted employee has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_EMPLOYEES, DBHelper.COLUMN_EMPLOYE_ID
                + " = " + id, null);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> listEmployees = new ArrayList<Employee>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_EMPLOYEES, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Employee employee = cursorToEmploye(cursor);
            listEmployees.add(employee);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listEmployees;
    }

    public List<Employee> getEmployeesOfCompany(long companyId) {
        List<Employee> listEmployees = new ArrayList<Employee>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_EMPLOYEES, mAllColumns,
                DBHelper.COLUMN_COMPANY_ID + " = ?",
                new String[] { String.valueOf(companyId) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Employee employee = cursorToEmploye(cursor);
            listEmployees.add(employee);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listEmployees;
    }

    private Employee cursorToEmploye(Cursor cursor) {
        Employee employee = new Employee();
        employee.setId(cursor.getLong(0));
        employee.setFirstName(cursor.getString(1));
        employee.setLastName(cursor.getString(2));
        employee.setAddress(cursor.getString(3));
        employee.setEmail(cursor.getString(4));
        employee.setPhoneNumber(cursor.getString(5));
        employee.setSalary(cursor.getDouble(6));

        // get The company by id
        long companyId = cursor.getLong(7);
        CompanyDAO dao = new CompanyDAO(mContext);
        Company company = dao.getCompanyById(companyId);
        if (company != null)
            employee.setCompany(company);

        return employee;
    }

}
