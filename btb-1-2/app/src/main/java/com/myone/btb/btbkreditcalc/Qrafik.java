package com.myone.btb.btbkreditcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.myone.btb.app.R;

/**
 * Created by Admin on 8/5/2014.
 */
@SuppressWarnings("ALL")
public class Qrafik extends Activity {


    private TableLayout table_layout;
      public static Button build_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafik);


        build_btn = (Button) findViewById(R.id.build_btn_id);
        table_layout = (TableLayout) findViewById(R.id.tableLayout1);

        build_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Calc.getYear()!=0) {
                    int rows = (int)Calc.getYear();

                    table_layout.removeAllViews();
                    BuildTable(rows,1);
                } else {
                    Toast.makeText(Qrafik.this,
                            "Please Enter the row and col Numbers",
                            Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void BuildTable(int rows, int cols) {

        // outer for loop
        for (int i = 1; i <= rows; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            row.setBackgroundResource(R.drawable.cell);
            // inner for loop

                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
                tv.setTextColor(0xff000000);
                tv.setPadding(5, 5, 5, 5);

                    tv.setText("faiz borcu" + Calc.getRez() + "\nvremya " + i + "\nfaizz" + Calc.getFaizBorc(i) + "\nesas borc " + Calc.getEsasBorc(i) + "\nqaliq  " + Calc.getQaliqBorc(i));

                    row.addView(tv);

            table_layout.addView(row);

        }
    }
}
