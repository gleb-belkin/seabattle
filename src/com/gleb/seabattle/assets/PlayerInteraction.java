package com.gleb.seabattle.assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: User.
 */
public class PlayerInteraction {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String playerName;

    public static void welcomePlayer() {
        System.out.println("Welcome!");
    }

    public static void askPlayerName() throws IOException {
        System.out.println("Please, introduce yourself");
        playerName = reader.readLine();
    }
}
