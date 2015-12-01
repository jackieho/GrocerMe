package com.jackieho.grocerme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText myUsername, myPass;
    TextView myRegisterLink;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myUsername = (EditText) findViewById(R.id.myUsername);
        myPass = (EditText) findViewById(R.id.myPass);
        bLogin = (Button) findViewById(R.id.bLogin);
        myRegisterLink = (TextView) findViewById(R.id.myRegisterLink);

        bLogin.setOnClickListener(this);
        myRegisterLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {

//        switch(v.getId()) {
//            case R.id.bLogin:
//                break;
//            case R.id.myRegisterLink:
//                startActivity(new Intent(this, Register.class));
//                break;
//        }

        if (v.getId() == R.id.bLogin) {

            User user = new User(null, null);
            userLocalStore.storeUserData(user);
            userLocalStore.setUserLoggedIn(true);
            startActivity(new Intent(this, MainActivity.class));

        }
        else if (v.getId() == R.id.myRegisterLink) {

            startActivity(new Intent(this, Register.class));

        }

    }

}
