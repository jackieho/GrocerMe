package com.jackieho.grocerme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jackieho on 2015-12-03.
 */
public class Enter extends AppCompatActivity implements View.OnClickListener {

    Button bEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bEnter = (Button) findViewById(R.id.bEnter);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bEnter) {

            startActivity(new Intent(this, Login.class));

        }

    }
}
