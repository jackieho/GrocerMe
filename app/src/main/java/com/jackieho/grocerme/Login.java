package com.jackieho.grocerme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private DBHelper mDbHelper = new DBHelper(this);

    Button bLogin;
    EditText myUsername, myPass;
    TextView myRegisterLink;

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
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bLogin) {

            String username = myUsername.getText().toString();
            String password = myPass.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Login error")
                        .setMessage("All fields must be filled.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else if (!(mDbHelper.inDatabase(User.TABLE_USER, username, password))) {
                new AlertDialog.Builder(this)
                        .setTitle("Login error")
                        .setMessage("Username and/or password not found.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else {
                Register.currentUsername = username;
                startActivity(new Intent(this, MainActivity.class));
            }

        }
        else if (v.getId() == R.id.myRegisterLink) {
            startActivity(new Intent(this, Register.class));
        }

    }

}
