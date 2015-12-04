package com.jackieho.grocerme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by jackieho on 2015-12-03.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bLogout;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true) {
            displayMenu();
        }
    }

    private boolean authenticate() {
        return userLocalStore.isUserLoggedIn();
    }

    // change method here to display main page
    private void displayMenu (){
        User user = userLocalStore.getLoggedInUser();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bLogout) {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);

            startActivity(new Intent(this, Login.class));

        }

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}

