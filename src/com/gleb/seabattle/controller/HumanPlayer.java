package com.gleb.seabattle.controller;

import com.gleb.seabattle.assets.FieldConstants;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class HumanPlayer implements Player {

    private boolean firstShot = true;
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public String getName() {
        return name;
    }

    private String name;

    public HumanPlayer() {
    }

    @Override
    public Point makeShot() {
        if (firstShot) {
            welcomePlayer();
            firstShot = false;
        }
        System.out.println("Please, enter the coordinates (e.g. c4):");
        String shotCoordinatesString = null;
        try {
            shotCoordinatesString = READER.readLine().replaceAll("\\s+", "").toLowerCase();
        } catch (IOException e) {
            //todo: implement error processing
            e.printStackTrace();
        }
        return parseCoordinatesString(shotCoordinatesString);
    }

    private void welcomePlayer() {
        System.out.println("Welcome! Please, introduce yourself");
        try {
            name = READER.readLine();
        } catch (IOException e) {
            //todo: implement error processing
            e.printStackTrace();
        }
    }

    private Point parseCoordinatesString(String coordinatesString) {
        Pattern coordinatesPattern = Pattern.compile("^([a-j])(\\d{1,2})$");
        Matcher matcher = coordinatesPattern.matcher(coordinatesString);
        int numberX = -1;
        int numberY = -1;
        if (matcher.matches()) {
            String letterX = matcher.group(1);
            numberX = FieldConstants.COORDINATE_LETTERS.indexOf(letterX);
            //Although we need a zero-based point and indexOf() is already zero-based, user input is not (should not be, according to the rules)
            numberY = Integer.parseInt(matcher.group(2)) - 1;
        }
        return new Point(numberX, numberY);
    }

}
