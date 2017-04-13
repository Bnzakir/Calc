package com.example.zakirbaghirov.kuhn;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import info.androidhive.androidsplashscreentimer.R;
/// PERVII MACHINE VTOROI HUMAN

public class Game extends AppCompatActivity {

  public static int[] cards;
    static String generalHistory ="";
 static    ArrayList<Info> list;
   static  ArrayList<Info> filteredList = new ArrayList<>();
   static double z = 0;
    public static boolean gameOver;

/// player[0] machine
    // player [1] human

   public static Player[] player = new Player[2];


    public static void initialize ()  {

        double p=0;


//            System.out.println("New round\n");
             // shuffleMe();

             for (int z = 0; z < player.length; z++) {
                 player[z] = new Player(z, cards[z]);
             }
             generalHistory = "";

             getAction(player[0], player[1]);
             p += player[0].payoff;


    }


   public static void shuffleMe() {
       List<Integer> cardsList = new ArrayList<Integer>();
       for (int j = 1; j <= 3; j++) {
           cardsList.add(j);
       }
           cards = new int[]{0, 0};

           Collections.shuffle(cardsList);
           cards[0] = cardsList.get(1);
           cards[1] = cardsList.get(2);


       System.out.println("\nPlayer1 has cards: " + cards[0] + "\nPlayer2 has cards:  " + cards[1] + "\n");

   }


    public static void engine(Player player0, Player player1) {
        int handsPlayed = generalHistory.length();


        if (handsPlayed > 1) {
            if (generalHistory.substring(handsPlayed - 2, handsPlayed).equals("pp")) {
                if (higherCardP1(player0.cards, player1.cards)) {
                   System .out.println("Player 1 wins with the card: " + cardName(player0.cards));
                    MainActivity.tvresult.setText("Machine wins with " + cardName(player0.cards) + ", Player loses with " + cardName(player1.cards));
                    player0.payoff +=MainActivity.ant;
                  //  player1.payoff -=MainActivity.ant;
                }
                    else {
                    System.out.println("Player 2 wins with the card: " + cardName(player1.cards));
                    MainActivity.tvresult.setText("Player wins with " + cardName(player1.cards) + ", Machine loses with " + cardName(player0.cards));
                            System.out.println("Terminal pass ");
                    player1.payoff +=MainActivity.ant;
                   // player0.payoff -=MainActivity.ant;

                }
                //+1 payoff to whoever has best card
            }

           else if (generalHistory.substring(handsPlayed - 2, handsPlayed).equals("bb")) {
                if (higherCardP1(player0.cards, player1.cards)) {
                    System.out.println("Player 1 wins with the card: " + cardName(player0.cards));
                    MainActivity.tvresult.setText("Machine wins with " + cardName(player0.cards) + ", Player loses with " + cardName(player1.cards));
                    player0.payoff +=MainActivity.ant;
                   // player1.payoff -=MainActivity.ant;



                } else {
                   System.out.println("Player 2 wins with the card: " + cardName(player1.cards));
                    MainActivity.tvresult.setText("Player wins with " + cardName(player1.cards) + ", Machine loses with " + cardName(player0.cards));
                    System.out.println("Terminal bet ");
                    player1.payoff +=MainActivity.ant;
                   // player0.payoff -=MainActivity.ant;
                    // +2 payoff to whoever has better card

                }
            }
                else if (generalHistory.substring(handsPlayed - 2, handsPlayed).equals("bp")  &&(!generalHistory.equals("pbp")) ) {

                System.out.println("Player 1 wins with the card: " + cardName(player0.cards));
                MainActivity.tvresult.setText("Machine wins with " + cardName(player0.cards) + ", Player loses with " + cardName(player1.cards));
                       player0.payoff+=MainActivity.ant;
                      // player1.payoff-=MainActivity.ant;

            }
                else if (generalHistory.equals("pbp")) {
                      System.out.println("Player 2 wins with the card: " + cardName(player1.cards));
                MainActivity.tvresult.setText("Player wins with " + cardName(player1.cards) + ", Machine loses with " + cardName(player0.cards));
                player1.payoff+=MainActivity.ant;
               // player0.payoff-=MainActivity.ant;

            }
            else if (player0.history.equals("p") ) {
                nastyAction(player0);


                engine(player0, player1);
            }
        }
        MainActivity.tw1Main.setText(generalHistory);
      System.out.println("machine muney "+  player0.payoff);
        System.out.println("human muney "+  player1.payoff);
    }


    public static void getAction(Player player1,Player player2){

        list = CFR.list;
        sameHistCards(list);

        if (player1.cards==3)
        {
            player1.action = "b";
            player1.setHistory("b");
            MainActivity.ant +=1;
            MainActivity.ante.setText("Pot is: " + MainActivity.ant);
            MainActivity.tw1Main.setText("Computer bets!");
        }

        else {

            if (Math.random() <z)
           // if (z>0.5)
            {
                player1.action = "b";
                player1.setHistory("b");
                MainActivity.ant +=1;

                MainActivity.tw1Main.setText("Computer bets!");

            } else {
                player1.action = "p";
                player1.setHistory("p");
                MainActivity.tw1Main.setText("Computer passes!");

            }

        }

            generalHistory += player1.action;
           MainActivity.tv5.setText(sTR(player1.action));
           MainActivity.tw1Main.setText(player1.history);

        engine(player1, player2);

        System.out.println("General history  after first move compa  " + generalHistory);


    }



    public static void nastyAction(Player player){
        //kakoi nibud action
        list = CFR.list;
        sameHistCards(list);
        if (player.cards ==1) {
            player.action = "p";
            player.setHistory("p");
        }

        else if ( Math.random()< z)

        {
            player.action = "b";
            player.setHistory("b");
            MainActivity.ant +=1;
        }

        else {
            player.action = "p";
            player.setHistory("p");
        }
   //       System.out.println("New round\n");

        generalHistory +=player.action;
        System.out.println(generalHistory);
    }
    public static String cardName(int card){
        if (card==1)
            return "Queen";
        else if (card==2)
            return "King";
        else if (card==3)
            return "Ace";
        else return "";
    }
    public static boolean higherCardP1(int p1Card, int p2Card)
    {
        return p1Card > p2Card;
    }

    public static String sTR(String a){
        if (a.equals("b"))
            return "Bet";
        else if(a.equals("p"))
            return "Pass";
            else return  "";

    }
    public static void sameHistCards(ArrayList<Info> list) {
       //System.out.println("General history is " + generalHistory);
        for (Info aList : list) {
            if ((aList.getHistory().equals(generalHistory)) && (aList.cardsp1 == cards[0]) && (aList.playerNumber==0)) {
                {
                    if (aList != null) {
                        filteredList.add(aList);

                    }
                }
            }

        }
        double max=filteredList.get(0).regret;
        for (Info aFilteredList : filteredList) {
            // System.out.println("regrets are: " filteredList.get(i).regret)
            if (aFilteredList.regret > 0) {
                if (aFilteredList.regret > max)
                    max = aFilteredList.regret;

                z = aFilteredList.str;
                //System.out.println("z is "+ z);

            }

        }
        //System.out.println("MAX IS : " +max);
   }

}
//
//         for (int i =0;i<filteredList.size();i++)
//         {
//           System.out.println("regret     " + filteredList.get(i).regret);
//         }
//        for (int i =0;i<list.size();i++)
//        {
//            System.out.println("regret newwww " + list.get(i).regret);
//        }
//

//                System.out.println("history is for p1 : " + player0.history);
//                System.out.println("general history is : " + generalHistory);