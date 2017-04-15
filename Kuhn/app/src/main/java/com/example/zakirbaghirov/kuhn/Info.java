package com.example.zakirbaghirov.kuhn;

/**
 * Created by zakirbaghirov on 27/02/2016.
 */
public class Info {

    int playerNumber;
    int cardsp1;
    int cardsp2;
    double regret;
    double payoff; /// is expected value
    String history;
    int index;
    double str;
    int action;
    public Info(int z) {
    index= z;
    }

    public void setStr(double i)
    {
    this.str = i;
    }

    public void setRegret(double regret)
    {
    this.regret = regret;
    }

    public void setCards(int cards1,int cards2)
    {
        cardsp1=cards1;
        cardsp2=cards2;
    }

    public void setNumber(int number)
    {
    this.playerNumber = number;
    }
    public void setAction(int action){this.action=action;}

    public void setHistory(String history)
    {
        this.history = history;
    }

    public String getHistory(){
    return history;
    }


    public int getPlayerNumber()
    {
    return playerNumber;
    }


    }

