package com.gleb.seabattle.controller;

import com.gleb.seabattle.model.Playable;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class HumanPlayer implements Playable {
    private static final int SHOT_ATTEMPTS_LIMIT = 5;
    private static final String COORDINATE_LETTERS = "abcdefghij";
    private boolean firstShot = true;
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private int shotAttempts;

    public HumanPlayer() {
    }

    @Override
    public Point makeShot() throws IOException {
        if (firstShot) {
            performInitialPlayerInteraction();
            firstShot = false;
        }
        shotAttempts = 0;
        while (shotAttempts < SHOT_ATTEMPTS_LIMIT) {
            System.out.println("Please, enter the coordinates (e.g. c4):");
            String shotCoordinatesString = READER.readLine().replaceAll("\\s+", "").toLowerCase();
            try {
                return parseCoordinatesString(shotCoordinatesString);
            } catch (Exception e) {
                //
            }
        }

        performGamePlayerInteraction();
        return null;
    }
    private void performGamePlayerInteraction() throws IOException {
        while (!Logic.endOfGame()) {
//            Logic.performShot(PlayerInteraction.askShotPoint());
        }
    }

    private void performInitialPlayerInteraction() throws IOException {
        PlayerInteraction.welcomePlayer();
        PlayerInteraction.askPlayerName();
    }

    private Point parseCoordinatesString(String coordinatesString) throws Exception {
        Pattern coordinatesPattern = Pattern.compile("^([a-j])(\\d{1,2})$");
        Matcher matcher = coordinatesPattern.matcher(coordinatesString);
        if (matcher.matches()) {
            String letterX = matcher.group(1);
            int numberY = Integer.parseInt(matcher.group(2));
            return new Point(getCoordinateByLetter(letterX), numberY);
        }
        throw new Exception("Coordinates string doesn't match the pattern.");
    }

    private int getCoordinateByLetter(String letterX) throws Exception {
        int i = COORDINATE_LETTERS.indexOf(letterX);
        if (i != -1) {
            return i;
        } else {
            throw new Exception("Letter out of range");
        }
    }


}
