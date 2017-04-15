package com.myone.btb.btbkreditcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.myone.btb.app.R;

/**
 * Created by Admin on 8/1/2014.
 */
public class Metro extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metro);
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.menlil,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case (R.id.aboutUs):
                Intent ab = new Intent("com.myone.btb.app.About");
                startActivity(ab);
                break;
            case (R.id.exit):
                finish();
                break;
        }
        return false;
    }

}
