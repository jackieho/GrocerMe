package com.jackieho.grocerme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by jackieho on 2015-12-03.
 */
public class Enter extends AppCompatActivity implements View.OnClickListener {

    Button bEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        bEnter = (Button) findViewById(R.id.bEnter);
        bEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Log.e("hannah", "hai");
        if (v.getId() == R.id.bEnter) {


            System.out.print("\"Hello\"");
            Log.e("hannah", "bai");
            startActivity(new Intent(this, Login.class));

        }

    }
}
