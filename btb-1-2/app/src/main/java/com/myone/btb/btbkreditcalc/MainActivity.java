package com.myone.btb.btbkreditcalc;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

import com.myone.btb.app.R;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

  //TabHost th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.La youtParams.FLAG_FULLSCREEN,
        //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tab);
        TabHost th = getTabHost();


        TabHost.TabSpec  specs = th.newTabSpec("tag1");
       specs.setContent(new Intent(this,Melumat.class));
        specs.setIndicator("Kreditlər");
        th.addTab(specs);


        specs = th.newTabSpec("tag2");
        specs.setContent(new Intent(this,Calc.class));
        specs.setIndicator("Kredit kalkulyatoru");
        th.addTab(specs);

        specs = th.newTabSpec("tag3");
        specs.setContent(new Intent(this,Qrafik.class));
        specs.setIndicator("Qrafik");
        th.addTab(specs);

    }


}
