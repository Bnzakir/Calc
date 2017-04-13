package com.example.zakirbaghirov.kuhn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.androidsplashscreentimer.R;
// package="info.androidhive.androidsplashscreentimer"
//import com.example.zakirbaghirov.kuhn1.R;

@SuppressWarnings("ALL")
public class MainActivity extends Activity {

    public static TextView tw1Main,tvresult,ante,tv5;

    TextView tw1,tw2;
    Button btn3, btn2 ,btn1;
    public static  ImageView  img;
    boolean gamePlayed= false;
    public static int ant=0;
    double util=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new PrefetchData().execute();

        img = (ImageView)findViewById(R.id.img1);


        btn3 = (Button)findViewById(R.id.button3);
        btn2 = (Button)findViewById(R.id.btn2);  //bet
        btn1 = (Button)findViewById(R.id.btn1); // fold

        tw1 =(TextView)findViewById(R.id.textView2);
        tw2 =(TextView)findViewById(R.id.muneytv);
        tv5 =(TextView)findViewById(R.id.tv5);

        tvresult =(TextView)findViewById(R.id.result);
        tw1Main =(TextView)findViewById(R.id.maintxt);
        ante = (TextView)findViewById(R.id.ante);
        util=10;


        // eto deal
        btn3.setOnClickListener(new View.OnClickListener() {
            //Intent pop = new Intent(MainActivity.this,PopMeUp.class);

            @Override
            public void onClick(View v) {
                tvresult.setText("");
                Game.generalHistory ="";
                ant=2;
                ante.setText("Pot is: "+ant);
                Toast.makeText(getApplicationContext(), "New game started", Toast.LENGTH_SHORT).show();

                util--;
                Game.shuffleMe();
                Game.initialize();
                Game.player[1].payoff=util;
                tw2.setText("" + Game.player[1].payoff);

                karty();
                ante.setText("Pot is: " + ant);
                gamePlayed =false;
            }
        });

        //bet
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!gamePlayed) {
                Game.player[1].action = "b";
                Game.player[1].history = "b";
                ant +=1;
                ante.setText("Pot is: " +ant);
                Game.generalHistory += Game.player[1].action;

                Game.engine(Game.player[0], Game.player[1]);
                gamePlayed = true;
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Game finished");
                alertDialog.setMessage(tvresult.getText());

                alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Game over", Toast.LENGTH_SHORT).show();
                        //btn3.callOnClick();
                    }
                });
                alertDialog.show();
                tw2.setText("" + Game.player[1].payoff);
                util=Game.player[1].payoff;
            }
                else {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Error!");
                alertDialog.setMessage("You can not bet more than once");

                alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Game over", Toast.LENGTH_SHORT).show();
                    //    btn3.callOnClick();

                    }
                });
                alertDialog.show();
            }
              }
        });

    //fold
             btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gamePlayed) {
                    Game.player[1].action = "p";
                    Game.player[1].history = "p";
                    Game.generalHistory += Game.player[1].action;
                    Game.engine(Game.player[0], Game.player[1]);
                    gamePlayed=true;

                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Game finished");
                    alertDialog.setMessage(tvresult.getText());

                    alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Game over", Toast.LENGTH_SHORT).show();
                            //  btn3.callOnClick();
                        }
                    });


                    alertDialog.show();
                    tw2.setText("" + Game.player[1].payoff);
                    util =Game.player[1].payoff;

                }  else {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Error!");
                    alertDialog.setMessage("You can not fold more than once");

                    alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Game over", Toast.LENGTH_SHORT).show();
                           // btn3.callOnClick();
                        }
                    });
                    alertDialog.show();
                }

            }
        });



     }


    public  void karty(){
        Resources res = getResources(); //resource handle

        if (Game.player[1].cards==1)
        {
            @SuppressWarnings("deprecation") Drawable drawable = res.getDrawable(R.drawable.queen); //new Image that was added to the res folder
            ImageView img1 =   (ImageView)findViewById(R.id.img1);
            //noinspection deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation
            img1.setBackgroundDrawable(drawable);
        }
        else if (Game.player[1].cards==2)
        {
            Drawable drawable = res.getDrawable(R.drawable.king); //new Image that was added to the res folder
            ImageView img1 =   (ImageView)findViewById(R.id.img1);
            img1.setBackgroundDrawable(drawable);
        }
        else if (Game.player[1].cards==3)
        {
            Drawable drawable = res.getDrawable(R.drawable.ace); //new Image that was added to the res folder
            ImageView img1 =   (ImageView)findViewById(R.id.img1);
            img1.setBackgroundDrawable(drawable);

        }

    }

    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Main.work();

            // before making http calls

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_main ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
