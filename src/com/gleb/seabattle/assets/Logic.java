package com.gleb.seabattle.assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Logic {

    private static final char HIDDEN_CELL_SYMBOL = '~';
    private static final char MISSED_SHOT_SYMBOL = '*';
    private static final char HIT_SHOT_SYMBOL = 'X';
    private static final char SHIP_SYMBOL = '8';
    private static final int FIELD_SIZE = 10;
    private static final int PLACE_ATTEMPTS_POINT_BY_DIRECTION_LIMIT = 15;
    private static final int PLACE_ATTEMPTS_DIRECTION_BY_SHIP_LIMIT = 10;
    private static final Random RANDOM = new Random();
    private static ArrayList<Shot> shotsHistory = new ArrayList<Shot>();
    private static ArrayList<Ship> ships = new ArrayList<Ship>();
    private static char[] fieldMatrix = new char[(int) Math.pow(FIELD_SIZE, 2)];

    public static void newGame() {
        resetShotsHistory();
        respawnShips();
        drawField();
    }

    public static void performShot(int x, int y) {
        //todo develop
        checkHit(x, y);
        //display result message
        if (endOfGame()) {
            completeGame();
        } else {
            drawField();
        }
    }

    private static void completeGame() {
        //todo develop
    }

    private static boolean endOfGame() {
        for (Ship ship : ships) {
            if (!ship.isHit()) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkHit(int x, int y) {
        for (Iterator<Ship> iterator = ships.iterator(); iterator.hasNext(); ) {
            Ship ship = iterator.next();
            if (ship.checkHit(x, y)) {
                return true;
            }
        }
        return false;
    }

    public static void drawField() {
        resetFieldMatrix();
        for (Iterator<Shot> iterator = shotsHistory.iterator(); iterator.hasNext(); ) {
            Shot next = iterator.next();
            if (next.isHit()) {
                fieldMatrix[convertCoordinatesToCellIndex(next)] = HIT_SHOT_SYMBOL;
            } else {
                fieldMatrix[convertCoordinatesToCellIndex(next)] = MISSED_SHOT_SYMBOL;
            }
        }
        //test begin
        /*for (Iterator<Ship> iterator = ships.iterator(); iterator.hasNext(); ) {
            Ship next = iterator.next();
            int feedPositionIndex = convertCoordinatesToCellIndex(next.getFeedPosition());
            int index = 0;
            switch (next.getDirection()) {
                case VERTICAL:
                    index = FIELD_SIZE;
                    break;
                case HORIZONTAL:
                    index = 1;
                    break;
            }
            for (int i = 0; i < next.getSize(); i++) {
                fieldMatrix[feedPositionIndex + i * index] = SHIP_SYMBOL;
            }
        }*/
        //test end
        outputFieldMatrix();
    }

    private static int convertCoordinatesToCellIndex(Point point) {
        return (point.x - 1) * FIELD_SIZE + point.y - 1;
    }

    private static void outputFieldMatrix() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            System.out.printf("%1$s%2$s", fieldMatrix[i], (i + 1) % FIELD_SIZE == 0 ? "\n" : "  ");
        }
    }

    private static void resetFieldMatrix() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            fieldMatrix[i] = HIDDEN_CELL_SYMBOL;
        }
    }

    private static void respawnShips() {
        ships.clear();
        ShipInitialData[] shipsData = ShipInitialData.values();
        for (int i = 0; i < shipsData.length; i++) {
            ShipInitialData data = shipsData[i];
            Ship ship = placeShip(data.getSize());
//            rethink it
            if (ship == null) {
                askForRespawn();
                return;
            }
            ships.add(ship);
            //drawShip(ship);
        }
    }

    private static void drawShip(Ship ship) {
        int feedPositionIndex = convertCoordinatesToCellIndex(ship.getFeedPosition());
        int index = 0;
        switch (ship.getDirection()) {
            case VERTICAL:
                index = FIELD_SIZE;
                break;
            case HORIZONTAL:
                index = 1;
                break;
        }
        for (int i = 0; i < ship.getSize(); i++) {
            fieldMatrix[feedPositionIndex + i * index] = SHIP_SYMBOL;
        }
    }

    private static void askForRespawn() {
        //    todo:    develop
    }

    private static Ship placeShip(int shipSize) {
        int placeAttemptsDirectionByShip = 0;
        int placeAttemptsPointByDirection = 0;
        while (placeAttemptsDirectionByShip < PLACE_ATTEMPTS_DIRECTION_BY_SHIP_LIMIT) {
            ShipDirection direction = ShipDirection.getRandomDirection();
            Point gapsData = initGapsData(direction, shipSize);
            while (placeAttemptsPointByDirection < PLACE_ATTEMPTS_POINT_BY_DIRECTION_LIMIT) {
                Point feedPosition = new Point(RANDOM.nextInt(FIELD_SIZE - gapsData.x), RANDOM.nextInt(FIELD_SIZE - gapsData.y));
                if (positionIsValid(feedPosition, direction, shipSize)) {
                    return new Ship(feedPosition, direction, shipSize);
                }
                placeAttemptsPointByDirection++;
            }
            placeAttemptsDirectionByShip++;
        }
        return null;
    }

    private static boolean positionIsValid(Point feedPosition, ShipDirection direction, int shipSize) {
        /*int minX = Math.max(0, feedPosition.x - 1);
        int minY = Math.max(0, feedPosition.y - 1);
        int maxX;
        int maxY;
        switch (direction) {
            case HORIZONTAL:
                maxX = Math.min(FIELD_SIZE, feedPosition.x + shipSize);
                maxY = Math.min(FIELD_SIZE, feedPosition.y + 1);
                break;
            case VERTICAL:
                maxX = Math.min(FIELD_SIZE, feedPosition.x + 1);
                maxY = Math.min(FIELD_SIZE, feedPosition.y + shipSize);
                break;
            default:
                maxX = feedPosition.x;
                maxY = feedPosition.y;
        }
        for (int i = 0; i < maxX - minX + 1; i++) {
            for (int j = 0; j < maxY - minY + 1; j++) {
                if (fieldMatrix[(minY + j - 1) * FIELD_SIZE + minX + i] == SHIP_SYMBOL) {
                    return false;
                }
            }
        }*/
        return true;
    }

    private static Point initGapsData(ShipDirection direction, int size) {
        int gapX = size;
        int gapY = size;
        switch (direction) {
            case HORIZONTAL:
                gapX = size;
                gapY = 0;
                break;
            case VERTICAL:
                gapX = 0;
                gapY = size;
                break;
        }
        return new Point(gapX, gapY);
    }

    private static void resetShotsHistory() {
        shotsHistory.clear();
    }
}
