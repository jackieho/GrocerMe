package com.jackieho.grocerme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class Register extends AppCompatActivity implements View.OnClickListener {

    public static String currentUsername = "";
    private DBHelper mDbHelper = new DBHelper(this);

    Button bRegister;
    EditText myName, myUsername, myPass, myConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myName = (EditText) findViewById(R.id.myName);
        myUsername = (EditText) findViewById(R.id.myUsername);
        myPass = (EditText) findViewById(R.id.myPass);
        myConfirmPass = (EditText) findViewById(R.id.myConfirmPass);
        bRegister = (Button) findViewById(R.id.bRegister);

        if (bRegister != null)
            bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bRegister) {

            String name = myName.getText().toString();
            String username = myUsername.getText().toString();
            String password = myPass.getText().toString();
            String confirm_password = myConfirmPass.getText().toString();

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()
                    || confirm_password.isEmpty() || !(password.equals(confirm_password))) {

                new AlertDialog.Builder(this)
                        .setTitle("Registration Error")
                        .setMessage("All fields must be filled and passwords must match.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else if (mDbHelper.checkDupl(User.TABLE_USER, username)) {

                new AlertDialog.Builder(this)
                        .setTitle("Registration Error")
                        .setMessage("The selected username is already taken.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else
            {
                UserDatabase my_database = new UserDatabase(this);
                my_database.createUser(name, username, password);

                currentUsername = username;

                startActivity(new Intent(this, MainActivity.class));
            }
        }
    }

}
