package com.jackieho.grocerme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import java.util.List;
import android.graphics.Color;


/**
 * Created by jackieho on 2015-12-03.
 */

public class ShoppingList extends AppCompatActivity implements View.OnClickListener {

    private DBHelper mDbHelper = new DBHelper(this);

    TextView bReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_table);

        bReturn = (TextView) findViewById(R.id.bReturn);
        bReturn.setOnClickListener(this);

        TableLayout myTable = (TableLayout) findViewById(R.id.ListTable);

        List<Coupon> allCoupons = mDbHelper.getCouponsOfUser(Coupon.TABLE_LIST, Register.currentUsername);

        double total = 0.0;

        for(int i = 0; i < allCoupons.size(); i++) {

            TableRow items = new TableRow(this);

            TextView list_amount = new TextView(this);
            list_amount.setText(Integer.toString(allCoupons.get(i).getAmount()));
            list_amount.setTextColor(Color.BLACK);
            list_amount.setGravity(Gravity.CENTER_HORIZONTAL);
            list_amount.setTextSize(14);

            TextView list_item = new TextView(this);
            list_item.setText(allCoupons.get(i).getItemName());
            list_item.setTypeface(Typeface.SERIF);
            list_item.setTextColor(Color.BLACK);
            list_item.setTextSize(14);

            TextView list_description = new TextView(this);
            list_description.setText(allCoupons.get(i).getDescription());
            list_description.setTypeface(Typeface.SERIF);
            list_description.setTextColor(Color.BLACK);
            list_description.setTextSize(14);

            TextView list_price = new TextView(this);
            list_price.setText(Double.toString(allCoupons.get(i).getItemPrice()));
            list_price.setTypeface(Typeface.SERIF);
            list_price.setTextColor(Color.BLACK);
            list_price.setGravity(Gravity.CENTER_HORIZONTAL);
            list_price.setTextSize(14);

            items.addView(list_amount);
            items.addView(list_item);
            items.addView(list_description);
            items.addView(list_price);

            myTable.addView(items);

            total += allCoupons.get(i).getItemPrice() * allCoupons.get(i).getAmount();

        }

        TableRow rowTotal= new TableRow(this);

        TextView labelTotal = new TextView(this);
        labelTotal.setText("Total");
        labelTotal.setTextSize(18);
        labelTotal.setTextColor(Color.BLACK);
        labelTotal.setPadding(10, 0, 0, 0);
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 3;

        TextView DisplayTotal = new TextView(this);
        double number = Math.round(total * 100);
        number = number/100;
        DisplayTotal.setText(Double.toString(number));
        DisplayTotal.setTextSize(18);
        DisplayTotal.setGravity(Gravity.CENTER_HORIZONTAL);
        DisplayTotal.setTextColor(Color.BLACK);

        rowTotal.addView(labelTotal, params);
        rowTotal.addView(DisplayTotal);
        myTable.addView(rowTotal);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bReturn) {

            startActivity(new Intent(this, MainActivity.class));

        }
    }
}

