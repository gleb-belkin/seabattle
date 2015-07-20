package com.gleb.seabattle.controller;

import com.gleb.seabattle.model.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Logic {

    //    todo: symbols to enum
    private static final char HIDDEN_CELL_SYMBOL = '~';
    private static final char MISSED_SHOT_SYMBOL = '*';
    private static final char HIT_SHOT_SYMBOL = 'X';
    private static final char SHIP_SYMBOL = '$';
    private static final int FIELD_SIZE = 10;
    private static final int PLACE_ATTEMPTS_POINT_BY_DIRECTION_LIMIT = 15;
    private static final int PLACE_ATTEMPTS_DIRECTION_BY_SHIP_LIMIT = 10;
    private static final Random RANDOM = new Random();
    private static final char[] coordinateLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'j', 'h', 'i', 'j'};
//    private static ArrayList<Shot> shotsHistory = new ArrayList<Shot>();
    private static ArrayList<Ship> ships = new ArrayList<Ship>();
    private static char[] fieldMatrix = new char[(int) Math.pow(FIELD_SIZE, 2)];


    /*public static void performShot(String coordinatesString) {
        //todo develop
        try {

            Shot shot = checkHit(parseCoordinatesString(coordinatesString));
            recordShot(shot);
            redrawField();
            if (shot.isHit()) {
                System.out.println("Hit! Keep pushing!");
            } else {
                System.out.println("Miss.");
            }
        } catch (Exception e) {
            System.out.printf("%1$s\n", e.getMessage());
        }

        //display result message
        *//*if (endOfGame()) {
            completeGame();
        } else {
            redrawField();
        }*//*
    }

    private static void recordShot(Shot shot) {
        shotsHistory.add(shot);
    }*/


    private static Point parseCoordinatesString(String coordinatesString) throws Exception {
        Pattern coordinatesPattern = Pattern.compile("^([a-j])(\\d{1,2})$");
        Matcher matcher = coordinatesPattern.matcher(coordinatesString);
        if (matcher.matches()) {
            String letterX = matcher.group(1);
            int numberY = Integer.parseInt(matcher.group(2));
            return new Point(getCoordinateByLetter(letterX), numberY);
        }
        throw new Exception("Coordinates string doesn't match the pattern.");
    }

    private static int getCoordinateByLetter(String letterX) throws Exception {
//todo: replace switch with indexOf
        switch (letterX) {
            case "a":
                return 1;
            case "b":
                return 2;
            case "c":
                return 3;
            case "d":
                return 4;
            case "e":
                return 5;
            case "f":
                return 6;
            case "g":
                return 7;
            case "h":
                return 8;
            case "i":
                return 9;
            case "j":
                return 10;
        }
        throw new Exception("Letter-to-coordinate match fail");
    }

    private static void completeGame() {
        //todo develop
    }

    public static boolean endOfGame() {
        for (Ship ship : ships) {
            if (!ship.isHit()) {
                return false;
            }
        }
        return true;
    }

    /*private static Shot checkHit(Point point) throws Exception {
        if (!pointIsValid(point)) {
            throw new Exception("Point coordinates are out of range.");
        }
        Point zeroBasedPoint = convertUserPointToZeroBasedPoint(point);
        for (Ship ship : ships) {
            if (ship.checkHit(zeroBasedPoint)) {
                return new Shot(zeroBasedPoint.x, zeroBasedPoint.y, true);
            }
        }
        return new Shot(zeroBasedPoint.x, zeroBasedPoint.y, false);
    }*/

    private static Point convertUserPointToZeroBasedPoint(Point point) {

        return new Point(point.x - 1, point.y - 1);
    }

    private static boolean pointIsValid(Point point) {
        return point.x >= 1 && point.x <= FIELD_SIZE && point.y >= 1 && point.y <= FIELD_SIZE;
    }

    /*public static void redrawField() {
        for (Shot next : shotsHistory) {
            if (next.isHit()) {
                fieldMatrix[convertCoordinatesToCellIndex(next)] = HIT_SHOT_SYMBOL;
            } else {
                fieldMatrix[convertCoordinatesToCellIndex(next)] = MISSED_SHOT_SYMBOL;
            }
        }
        outputFieldMatrix();
    }*/


    private static void outputFieldMatrix() {
        for (int i = 0; i < coordinateLetters.length; i++) {
            String prefix = "  ";
            if (i == 0) {
                prefix = "    ";
            }
            System.out.printf("%1$s%2$s", prefix, coordinateLetters[i]);
        }
        System.out.print("\n");
        for (int i = 0; i < fieldMatrix.length; i++) {
            String prefix = "";
            String suffix = "  ";
            if (i % FIELD_SIZE == 0) {
                int rowIndex = i / FIELD_SIZE + 1;
                String rowIndexPrefix;
                if (rowIndex < 10) {
                    rowIndexPrefix = " ";
                } else {
                    rowIndexPrefix = "";
                }
                prefix = String.format("%1$s%2$s  ", rowIndexPrefix, rowIndex);
            }
            if ((i + 1) % FIELD_SIZE == 0) {
                suffix = "\n";
            }
            System.out.printf("%1$s%2$s%3$s", prefix, fieldMatrix[i], suffix);
        }
    }


}
