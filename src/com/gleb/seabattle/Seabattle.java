package com.gleb.seabattle;

import com.gleb.seabattle.assets.Logic;
import com.gleb.seabattle.assets.PlayerInteraction;

import java.io.IOException;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Seabattle {
    public static void main(String[] args) throws IOException {
        Logic.newGame();
        startPlayerInteraction();
    }

    private static void startPlayerInteraction() throws IOException {
        PlayerInteraction.welcomePlayer();
        PlayerInteraction.askPlayerName();
    }
}
