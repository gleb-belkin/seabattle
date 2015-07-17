package com.gleb.seabattle;

import com.gleb.seabattle.assets.Logic;
import com.gleb.seabattle.assets.PlayerInteraction;

import java.io.IOException;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Seabattle {
    public static void main(String[] args) throws IOException {
        performInitialPlayerInteraction();
        Logic.newGame();
        performGamePlayerInteraction();
    }

    private static void performGamePlayerInteraction() throws IOException {
        while (!Logic.endOfGame()) {
            Logic.performShot(PlayerInteraction.askShotPoint());
        }
    }

    private static void performInitialPlayerInteraction() throws IOException {
        PlayerInteraction.welcomePlayer();
        PlayerInteraction.askPlayerName();
    }
}
