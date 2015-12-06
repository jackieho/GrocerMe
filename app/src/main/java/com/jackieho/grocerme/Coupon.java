package com.jackieho.grocerme;

import java.io.Serializable;

/**
 * Created by jackieho on 2015-12-04.
 */
public class Coupon implements Serializable {

    public static final String TABLE_LIST = "List";

    public static final String KEY_coupon_id = "ID";
    public static final String KEY_username = "Username";
    public static final String KEY_item_name = "Item_Name";
    public static final String KEY_description = "Description";
    public static final String KEY_item_price = "Item_Price";
    public static final String KEY_amount = "Amount";

    public long coupon_ID;
    public String username, item_name, description;
    public double item_price;
    public int amount;
    public User user;

    public Coupon() {
    }

    public Coupon(String username, String item_name, String description, double item_price, int amount) {
        this.username = username;
        this.item_name = item_name;
        this.description = description;
        this.item_price = item_price;
        this.amount = amount;
    }

    public void setCouponId(long coupon_ID) {
        this.coupon_ID = coupon_ID;
    }

    public String getItemName() {
        return item_name;
    }
    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getItemPrice() {
        return item_price;
    }
    public void setItemPrice(double item_price) {
        this.item_price = item_price;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}