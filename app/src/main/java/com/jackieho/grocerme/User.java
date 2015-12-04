package com.jackieho.grocerme;

import java.io.Serializable;

/**
 * Created by jackieho on 2015-11-30.
 */
public class User implements Serializable {

    public static final String TAG = "User";

    public static final String TABLE_USER = "User";
    public static final String KEY_id = "id";
    public static final String KEY_name = "name";
    public static final String KEY_username = "username";

    // encrypt it?
    public static final String KEY_password = "password";

    public String name, username, password;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // get and set methods
}
