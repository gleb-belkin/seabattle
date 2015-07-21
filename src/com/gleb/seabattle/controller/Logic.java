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






    private static void completeGame() {
        //todo develop
    }

    /*public static boolean endOfGame() {
        for (Ship ship : ships) {
            if (!ship.isHit()) {
                return false;
            }
        }
        return true;
    }*/

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

    /*private static boolean pointIsValid(Point point) {
        return point.x >= 1 && point.x <= FIELD_SIZE && point.y >= 1 && point.y <= FIELD_SIZE;
    }*/

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



}
