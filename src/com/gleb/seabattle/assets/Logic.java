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
    private static ArrayList<Shot> shotsHistory = new ArrayList<Shot>();
    private static ArrayList<Ship> ships = new ArrayList<Ship>();
    private static final int FIELD_SIZE = 10;
    private static final int PLACE_ATTEMPTS_POINT_BY_DIRECTION_LIMIT = 15;
    private static final int PLACE_ATTEMPTS_DIRECTION_BY_SHIP_LIMIT = 10;
    private static final Random RANDOM = new Random();
    private static char[][] fieldMatrix = new char[FIELD_SIZE][FIELD_SIZE];

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
        //todo develop
        return false;
    }

    private static void checkHit(int x, int y) {
        for (Iterator<Ship> iterator = ships.iterator(); iterator.hasNext(); ) {
            Ship ship = iterator.next();
            if (ship.checkHit(x, y)) {
                return;
            }
        }
        //todo develop
    }

    public static void drawField() {
        resetFieldMatrix();
        for (Iterator<Shot> iterator = shotsHistory.iterator(); iterator.hasNext(); ) {
            Shot next = iterator.next();
            if (next.hit) {
                fieldMatrix[next.x][next.y] = HIT_SHOT_SYMBOL;
            } else {
                fieldMatrix[next.x][next.y] = MISSED_SHOT_SYMBOL;
            }
        }
        //test begin
        for (Iterator<Ship> iterator = ships.iterator(); iterator.hasNext(); ) {
            Ship next = iterator.next();
            fieldMatrix[next.getFeedPosition().x][next.getFeedPosition().y] = SHIP_SYMBOL;
            for (int i = 0; i < next.getSize(); i++) {
                switch (next.getDirection()) {
                    case VERTICAL:
                        fieldMatrix[next.getFeedPosition().x][next.getFeedPosition().y+i+1] = SHIP_SYMBOL;
                        break;
                    case HORIZONTAL:
                        fieldMatrix[next.getFeedPosition().x+i+1][next.getFeedPosition().y] = SHIP_SYMBOL;
                        break;
                }
            }
        }
        //test end
        outputFieldMatrix();
    }

    private static void outputFieldMatrix() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            String after = " ";
            for (int j = 0; j < fieldMatrix[i].length; j++) {

                if (j == fieldMatrix[i].length) {
                    after = "";
                }
                System.out.printf("%1$s%2$s", fieldMatrix[i][j], after);
            }
            System.out.print("\n");
        }
    }

    private static void resetFieldMatrix() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                fieldMatrix[i][j] = HIDDEN_CELL_SYMBOL;
            }
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
            GapsData gapsData = initGapsData(direction, shipSize);
            while (placeAttemptsPointByDirection < PLACE_ATTEMPTS_POINT_BY_DIRECTION_LIMIT) {
                Point feedPosition = new Point(RANDOM.nextInt(FIELD_SIZE - gapsData.gapX), RANDOM.nextInt(FIELD_SIZE - gapsData.gapY));
                if (positionIsValid(feedPosition, shipSize)) {
                    return new Ship(feedPosition, direction, shipSize);
                }
                placeAttemptsPointByDirection++;
            }
            placeAttemptsDirectionByShip++;
        }
        return null;
    }

    private static boolean positionIsValid(Point feedPosition, int shipSize) {
//    todo:    develop
        return true;
    }

    private static GapsData initGapsData(ShipDirection direction, int size) {
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
        return new GapsData(gapX, gapY);
    }

    private static void resetShotsHistory() {
        shotsHistory.clear();
    }

    private static class GapsData {
        private final int gapX;
        private final int gapY;

        public GapsData(int gapX, int gapY) {
            this.gapX = gapX;
            this.gapY = gapY;
        }
    }

    private static class Shot extends Point {

        private final boolean hit;

        public Shot(int x, int y, boolean hit) {
            super(x, y);
            this.hit = hit;
        }
    }
}
