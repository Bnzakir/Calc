package com.myone.btb.btbkreditcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.myone.btb.app.R;

/**
 * Created by Admin on 7/31/2014.
 */
public class Auto extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avto);
        TableLayout stol =(TableLayout)findViewById(R.id.table);

        stol.setColumnShrinkable(1,true);
        stol.setColumnShrinkable(2,true);
        stol.setColumnShrinkable(3,true);

        TableLayout stol1 =(TableLayout)findViewById(R.id.stol1);

        stol1.setColumnShrinkable(1,true);
        stol1.setColumnShrinkable(2,true);
        stol1.setColumnShrinkable(3,true);
        stol1.setColumnShrinkable(4,true);
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
