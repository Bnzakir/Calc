package com.myone.btb.btbkreditcalc;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.myone.btb.app.R;

/**
 * Created by Admin on 7/21/2014.
 */
public class Melumat extends  ListActivity {
    private final String[] items = {"Mikrokreditler", "404 və 606 Kredit kartları","Müsbət tarixçəli müştərilərə kredit","Ipoteka","Avtomobil krediti","Dövlət müəssisələrinin əməkdaşları üçün kredit","Metropoliten əməkdaşları üçün kredit","Özəl müəssisələrin əməkdaşları üçün kredit","Təqaüdçülər üçün kredit","Tibb və təhsil işçiləri üçün kredit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setListAdapter(new ArrayAdapter<String>(Melumat.this, R.layout.mytextview, items));
       // getWindow().getDecorView().setBackground(R.drawable.ik);
     }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch (position) {
            case 0:

                Intent mk = new Intent(Melumat.this, Mikrokreditler.class);
                startActivity(mk);
                break;

            case 1:
                Intent kart = new Intent(Melumat.this, Kart.class);
                startActivity(kart);
                break;

            case 2:
                Intent  Mt = new Intent(Melumat.this, Mtm.class);
                startActivity(Mt);
            break;

            case 3:
                Intent ip = new Intent(Melumat.this,Ipoteka.class);
                startActivity(ip);
                break;
            case 4:
                Intent av = new Intent(Melumat.this,Auto.class);
                startActivity(av);
                break;
            case 5:
                Intent dv = new Intent(Melumat.this,DovletKredit.class);
                startActivity(dv);
                break;
            case 6:
                Intent metr = new Intent(Melumat.this,Metro.class);
                startActivity(metr);
                break;
            case 7:
                Intent oz = new Intent (Melumat.this,Ozal.class);
                startActivity(oz);
                break;
            case 8:
                Intent tq = new Intent(Melumat.this,Taq.class);
                startActivity(tq);
                break;
            case 9:
                Intent ti = new Intent (Melumat.this,Tibb.class);
                startActivity(ti);
                break;
    }

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


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.menlil,menu);
        return true;
    }
//    private void hideSoftKeyBoardOnTabClicked(View v) {
//        if (v != null && Melumat.this!=null) {
//            InputMethodManager imm = (InputMethodManager) Melumat.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }

}

