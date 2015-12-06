package com.jackieho.grocerme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by jackieho on 2015-12-03.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bAddItem, bShoppingList, bDelete, bLogout;
    private DBHelper mDbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAddItem = (Button) findViewById(R.id.bAddItem);
        bAddItem.setOnClickListener(this);

        bShoppingList = (Button) findViewById(R.id.bShoppingList);
        bShoppingList.setOnClickListener(this);

        bDelete = (Button) findViewById(R.id.bDelete);
        bDelete.setOnClickListener(this);

        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bLogout) {
            Register.currentUsername = "";
            startActivity(new Intent(this, Login.class));
        }
        else if (v.getId() == R.id.bAddItem) {
            startActivity(new Intent(this, AddItem.class));
        }
        else if (v.getId() == R.id.bDelete) {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Table")
                    .setMessage("Are you sure you want to delete all items?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            mDbHelper.deleteCoupons(Coupon.TABLE_LIST);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else if (v.getId() == R.id.bShoppingList) {
            startActivity(new Intent(this, ShoppingList.class));
        }

    }

}

