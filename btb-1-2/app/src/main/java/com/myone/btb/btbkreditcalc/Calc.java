package com.myone.btb.btbkreditcalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myone.btb.app.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Admin on 7/2/14.
 */
@SuppressWarnings("ALL")
public class Calc extends Activity implements View.OnClickListener {

    private EditText price1;
    private EditText interest1;
    private EditText year1;
    private EditText predop1;
    private TextView result1;
    private TextView perep1;
    private Button knpk;
    private Button qrbut;
    private String price;
    private String interest;
    private String year;
    private String predop;
    private static double rez;
    private static int n=0;
    private static final double[] esas= new double[320];

private static final double[] faizborcFirst = new double[320];
    private static final double[] qaliq= new double[320];

    private void hideSoftKeyBoardOnTabClicked(View v) {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) Calc.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public void onClick(View v) throws NullPointerException {


        boolean worked1 = true;
        double a = 0;
        double i =0;
        double p = 0;
        double f = 0;
        double prp;

        price = price1.getText().toString();
        interest = interest1.getText().toString();
        year = year1.getText().toString();
        predop = predop1.getText().toString();

         try {

             n = Integer.parseInt(year);//muddat
             i = Double.parseDouble(interest) / (12 * 100); //faiz
             f = Double.parseDouble(interest); //faizz
             a = Double.parseDouble(price); //mablag
             if (!predop.equals("")) {
                 p = Double.parseDouble(predop);
             }
         } catch (NumberFormatException x){
             worked1 = false;
                 Toast t = Toast.makeText(Calc.this, "Empty fields!", Toast.LENGTH_SHORT);
                 t.show();
                 x.printStackTrace();
         } finally {
             if (worked1) {
                 if (p != 0)
                     a = a - p;
                 double m = a * ((i * Math.pow((1 + i), n))) / ((Math.pow((1 + i), n) - 1));
                 rez = Math.round(m * Math.pow(10, 2)) / Math.pow(10, 2);

                 prp = (rez*n)-a;
                 prp = Math.round(prp*100)/100.0d;
                 String prs = String.valueOf(prp);
                 perep1.setText(prs);
                 String rezik = String.valueOf(rez);
                 result1.setText(rezik);
                 qrbut.setVisibility(View.VISIBLE);
                 qrbut.setOnClickListener(new  View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                         Intent newFrag = new Intent(Calc.this, Qrafik.class);
                         startActivity(newFrag);
                         Qrafik.build_btn.performClick();

                     }
                 });
                 //sdad
                 // graph calculation

                 faizborcFirst[1] = (a * f) / n;
                 double d = faizborcFirst[1];



                 d = Math.floor(d* 100) / 10000.0;
                 d = new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
                 faizborcFirst[1]=d;

                 esas[1] = m - faizborcFirst[1];
                 esas[1]= Math.round(esas[1]*100.0)/100.0d;


                 qaliq[1] = a - esas[1];
                 qaliq[1]= Math.round(qaliq[1]*100.0)/100.0d;

                 double d1;
                 for (int z = 1; z <= n; z++) {

                     //faiz
                     faizborcFirst[z + 1] = (qaliq[z] * f) / n;
                     d1 = faizborcFirst[z+1];
                     d1 = Math.floor(d1* 100) / 10000.0;
                     d1 = new BigDecimal(d1).setScale(2, RoundingMode.HALF_UP).doubleValue();
                     faizborcFirst[z+1]=d1;

                     //esas

                     esas[z + 1] = m - faizborcFirst[z + 1];
                     double ss=esas[z+1];
                     double zl= new BigDecimal(ss).setScale(2, RoundingMode.UP).doubleValue();
                     esas[z+1]=zl;

                     //qaliq
                     qaliq[z+1] = qaliq[z] - esas[z+1];
                     qaliq[z+1]= new BigDecimal(qaliq[z+1]).setScale(2, RoundingMode.UP).doubleValue();


                 }//for
                 //qaliq[n]= 0;
             }
             hideSoftKeyBoardOnTabClicked(v);

        }
      }

    public void createGraph(View v)
    {



    }

    public static double getFaizBorc(int z)
    {

     return faizborcFirst[z];

    }
    public static double getEsasBorc(int z){
     return esas[z];
    }



    public static double getQaliqBorc(int z){
        return qaliq[z];
    }

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kalk);
        initialize();
        knpk.setOnClickListener(this);

    }


    private void initialize () {
     price1 = (EditText)findViewById(R.id.et1);
     interest1 = (EditText)findViewById(R.id.et2);
     year1 = (EditText)findViewById(R.id.et3);
     result1 = (TextView)findViewById(R.id.tv6);
     knpk = (Button)findViewById(R.id.btn);
     predop1 = (EditText)findViewById(R.id.et4);
     perep1 =(TextView)findViewById(R.id.pr);
     qrbut = (Button)findViewById(R.id.qrr);

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

     public static double getYear(){
        return n;
     }

    public static double getRez()
    {
        return  rez;
    }



}