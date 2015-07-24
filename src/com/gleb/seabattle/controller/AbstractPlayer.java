package com.gleb.seabattle.controller;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 25.07.2015.
 */
public abstract class AbstractPlayer {
    protected String name = "AiPlayer";
    private boolean winner = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void makeWinner() {
        this.winner = true;
    }

    public boolean isWinner() {
        return winner;
    }
}
