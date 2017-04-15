package com.example.zakirbaghirov.kuhn;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by zakirbaghirov on 17/10/2015.
 */
class Strategy {

    public static  TreeMap<String, Strategy> nodeMap = new TreeMap<>();
     double[] counterFactualRegret = new double[2]; // sum of all positive regrets of the following action a
      double[] strategy = new double[2]; // is probability of action a
    public static String infoSet1;
    public double normalizingSum;

    public double[] getStrategy() {
         normalizingSum = 0;
        for (int a = 0; a < 2; a++) {
            if (counterFactualRegret[a]>0 ) {
                strategy[a] = counterFactualRegret[a];

            }
            else
               // strategy[a]= 0.5;
             strategy[a]=0;
            normalizingSum = normalizingSum+ strategy[a];
           // System.out.println("THE strategy  is: 1    " + strategy[a] + " the normal sum iss " + normalizingSum +"action is " +a+ "\n");

        }
        for (int a = 0; a < 2; a++) {
            if (normalizingSum >0 ) {
                strategy[a] = (strategy[a] )/ (normalizingSum);
            }
            else
                strategy[a] = 0.5;
            //System.out.println("THE strategy  is11111: "+strategy[a]);
           //System.out.println("THE strategy  is: 1111   " + strategy[a] + " the normal sum iss " + normalizingSum  +"action is " +a+ "\n");

        }


        return strategy;
    }


}