package com.gleb.seabattle.controller;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 17.07.2015.
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

    public static String askShotPoint() throws IOException {
        System.out.println("Please, enter the coordinates (e.g. c4):");
        return reader.readLine().replaceAll("\\s+", "").toLowerCase();
    }
}
