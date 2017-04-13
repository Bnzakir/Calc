package com.example.zakirbaghirov.kuhn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.Math.round;

/**
 * Created by zakirbaghirov on 18/10/2015.
 * http://webdocs.cs.ualberta.ca/~games/poker/publications/johanson.msc.pdf
 * osnova
 *
 */


public class CFR {
                                // key   // value
    private static final TreeMap<String, Strategy> nodeMap = new TreeMap<>();
    public static ArrayList<Info> list = new ArrayList<>();
     static int counter =0;
    public static String infoSet1;
    public  static double p0=0.5,p1=0.5;
    // probabilities of player actions - p0,p1
    public static double cfr( int[] cards,  String history, double p0, double p1) {


        int rounds = history.length();
        int player = rounds % 2;
        int opponent = 1 - player;

      Info infoSet = new Info(counter);

        if (rounds > 1) {
            boolean terminalPass = history.charAt(rounds - 1) == 'p';
            boolean doubleBet = history.substring(rounds - 2, rounds).equals("bb");
            boolean isPlayerCardHigher = cards[player] > cards[opponent];
            if (terminalPass) {
                if (history.equals("pp")) {
                    if (isPlayerCardHigher) {
                        return 1;
                    } else return -1;
                } else
                    return 1;
            } else if (doubleBet)

            {
                if (isPlayerCardHigher) {
                    return 2;
                } else return -2;
            }
        }

        infoSet1 = cards[player] + history;

        Strategy node = nodeMap.get(infoSet1);

        if (node == null) {
            node = new Strategy();
            Strategy.infoSet1 = infoSet1;
            nodeMap.put(infoSet1, node);

        }
        double[] strategy = node.getStrategy();
//
//                for (int a = 0; a < 2; a++) {
//
//          System.out.println("xux" + node.strategy[a]);
//        }
        double[] payoff = new double[2]; //payoff for action

        double nodePayoff = 0; // payoff  if that action is taken expected value in other words
        double regret;

        for (int a = 0; a < 2; a++) {

            String nextHistory;
            if (a==0)
            nextHistory = history +  "p";
            else
                nextHistory = history +  "b";

             infoSet.setCards(cards[0],cards[1]);
            if (player == 0) {
                 p0 = p0*strategy[a];
                payoff[a] = cfr(cards, nextHistory,p0 , p1);
                infoSet.setHistory(history);
                infoSet.setNumber(0);
            }
                else {
                p1 = p1*strategy[a];

                payoff[a] = cfr(cards, nextHistory, p0, p1 );

                infoSet.setHistory(history);
                infoSet.setNumber(1);

            }
            nodePayoff += strategy[a] * payoff[a];

            counter++;
        }


        for (int a = 0; a < 2; a++)
        {
            // the regret calculation is the difference for the payoff of the action and the expected value

            regret = payoff[a] - nodePayoff;

            infoSet.setRegret(regret);
            //p1 and p0 are oponents proabiblities of reaching this infoset


            if (player == 0)
            {
             node.counterFactualRegret[a] += p1 * regret;
             infoSet.setStr(node.normalizingSum);
                infoSet.setAction(a);
            }
            else { node.counterFactualRegret[a] += p0 * regret;}

        }

        takeAndSave( infoSet);
        return nodePayoff;

    }

    public static void takeAndSave(Info infoSet){ list.add(infoSet); }


}


//System.out.println("*********************************************");
//        System.out.println("Player is " + infoSet.playerNumber  + " player action is: " +infoSet.action  + " player cards are "+ infoSet.cards+" \n Player history is: "+
//                        infoSet.history+" Expected Payoff " + infoSet.payoff+ " Player regret is: "+ infoSet.regret);

//            System.out.println("*********************************************");
//
//            System.out.println("player is " + player);
//            System.out.println("history is " + history + " next history is " + nextHistory);
//      System.out.println("Player has cards: " + cards[player] + " Oponent has cards: " + cards[opponent] + "\n");
//            System.out.println("*********************************************");
//                System.out.println("player regret is: " +infoSet.regret  + " player cards are "+ infoSet.cards+" Player history is: "+
//                        infoSet.history+ " Player number is: "+ infoSet.playerNumber);
//System.out.println(node.regretSum[a]);
// System.out.println("\n "+ "is: " + node.regretSum[a] + " for action " + a + " and payoff is " + payoff[a] + "\n");

//System.out.println("CFR IS : "+node.counterFactualRegret[a] +"action a : " +a);
//System.out.println("CFR IS2: "+node.counterFactualRegret[a]);
// System.out.println("CFR IS2 : " +p0 * regret );
//System.out.println("CFR IS1: "+p1 * regret);
//System.out.println("initial regrets  " +regret);

//System.out.println("regret" +regret);
// System.out.println("infoset\n" + infoSet1);
//  System.out.println("Player has cards " +cards[0]);
