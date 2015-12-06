package com.jackieho.grocerme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jackieho on 2015-12-03.
 */
public class AddItem extends AppCompatActivity implements View.OnClickListener {

    Button bAddCoupon;
    EditText itemName, description, itemPrice, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);

        itemName = (EditText) findViewById(R.id.ItemName);
        description = (EditText) findViewById(R.id.Description);
        itemPrice = (EditText) findViewById(R.id.ItemPrice);
        amount = (EditText) findViewById(R.id.Amount);

        bAddCoupon = (Button) findViewById(R.id.bAddCoupon);

        if (bAddCoupon != null)
            bAddCoupon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bAddCoupon) {
            String item_name = itemName.getText().toString();
            String descrip = description.getText().toString();
            String item_price = itemPrice.getText().toString();
            String amt = amount.getText().toString();

            if (item_name.isEmpty() || descrip.isEmpty() || item_price.isEmpty() || amt.isEmpty()) {

                new AlertDialog.Builder(this)
                        .setTitle("Form error")
                        .setMessage("All fields must be filled.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else if (Double.parseDouble(itemPrice.getText().toString()) < 0 ||
                    Integer.parseInt(amount.getText().toString()) < 0) {

                    new AlertDialog.Builder(this)
                            .setTitle("Form error")
                            .setMessage("Please enter valid, positive values.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
            }
            else
            {
                Double double_item_price = Double.parseDouble(itemPrice.getText().toString());
                int int_amt = Integer.parseInt(amount.getText().toString());

                System.out.print("hello");
                CouponDatabase my_database = new CouponDatabase(this);
                my_database.createCoupon(Register.currentUsername, item_name, descrip, double_item_price, int_amt);

                startActivity(new Intent(this, MainActivity.class));
            }

        }
    }
}
