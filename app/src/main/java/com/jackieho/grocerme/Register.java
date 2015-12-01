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

public class Register extends AppCompatActivity implements View.OnClickListener {

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

        // check confirm = password and open pop up window

        if (v.getId() == R.id.bRegister) {

            String name = myName.getText().toString();
            String username = myUsername.getText().toString();
            String password = myPass.getText().toString();

            User user = new User(name, username, password);

            startActivity(new Intent(this, MainActivity.class));

        }

    }

}
