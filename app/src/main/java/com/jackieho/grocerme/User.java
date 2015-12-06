package com.jackieho.grocerme;

import java.io.Serializable;

/**
 * Created by jackieho on 2015-11-30.
 */
public class User implements Serializable {

    public static final String TABLE_USER = "User";
    public static final String KEY_id = "User_ID";
    public static final String KEY_name = "Name";
    public static final String KEY_username = "Username";
    public static final String KEY_password = "Password";

    private long user_ID;
    public String name, username, password;

    public User() {
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public long getUserId() {
        return user_ID;
    }

    public void setUserId(long user_ID) {
        this.user_ID = user_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
