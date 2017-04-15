package com.example.zakirbaghirov.kuhn;

import java.util.*;

/**
 * Created by zakirbaghirov on 17/10/2015.
 * monte carlo chance sampling
 * cfr is called after cards are dealt
 */
public class Main {
   public static double z;

    public static void work() {
        System.out.println("Welcome to the Kuhn poker! ");
        z = 0;
        int iterations = 10000;
        train(iterations);
// android:src="@drawable/poker"

    }

    private static void train(int iterations) {
        //cards 1 - jack
        // 2 - queen
        // 3 - king
        //cards creation
        List<Integer> cardsList = new ArrayList<>();
        for (int j = 1; j <= 3; j++) {
            cardsList.add(j);
        }
        int[] cards = {0, 0};


        double util = 0;
        for (int i = 0; i < iterations; i++) {
            // cards shuffling
            Collections.shuffle(cardsList);
            cards[0] = cardsList.get(1);
            cards[1] = cardsList.get(2);


            //System.out.println("\n New ROUND\n");

            util += CFR.cfr(cards, "", CFR.p0, CFR.p1);
            // System.out.println(util);


        }

    }

}
