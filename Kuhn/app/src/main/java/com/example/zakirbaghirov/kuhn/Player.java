package com.example.zakirbaghirov.kuhn;

/**
 * Created by zakirbaghirov on 25/02/2016.
 */
public class Player {
    int playerNumber;
    int cards;
    String action;
   // double regret;
    double payoff=0;
    String history;

    public  Player(int playerNumber, int cards){
        this.playerNumber = playerNumber;
        this.cards = cards;
        //this.history = history;

    }

//    public void setAction(String action)
//    {
//        this.action = action;
//    }
//
//    public void setRegret(double regret)
//    {
//        this.regret = regret;
//    }

    public void setHistory(String history)
    {
        if (this.history==null)
           this.history = history;
        else
        this.history += history;

    }

//    public String getHistory(){
//        return history;
//    }
//
//    public int getPlayerNumber()
//    {
//        return playerNumber;
//    }
//
//    public String getAction()
//    {
//        return action;
//    }

}
