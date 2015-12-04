package com.jackieho.grocerme;

import java.io.Serializable;

/**
 * Created by jackieho on 2015-12-04.
 */
public class Coupon implements Serializable {

    // public static final String TAG = "User";
    public static final String TABLE_LIST = "List";
    public static final String KEY_item_name = "Item Name";
    public static final String KEY_description = "Description";
    public static final double KEY_item_price = "Item Price";
    public static final int KEY_amount = "Amount";


    // encrypt it?
    public static final String KEY_password = "password";

    public String item_name, description;
    public double item_price;
    public int amount;

    public Coupon(String item_name, String description, double item_price, int amount) {
        this.item_name = item_name;
        this.description = description;
        this.item_price = item_price;
        this.amount = amount;
    }

    // get and set methods
}