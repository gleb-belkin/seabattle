package com.gleb.seabattle.model;

import com.gleb.seabattle.assets.FieldConstants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 19.07.2015.
 */
public class FieldModel {

    private static final int PLACE_ATTEMPTS_POINT_BY_DIRECTION_LIMIT = 15;
    private static final int PLACE_ATTEMPTS_DIRECTION_BY_SHIP_LIMIT = 10;
    private static final Random RANDOM = new Random();

    public char[] getFieldMatrix() {
        return fieldMatrix;
    }

    private char[] fieldMatrix;
    private ArrayList<Shot> shotsHistory = new ArrayList<>();
    private ArrayList<Ship> ships = new ArrayList<>();
    private final int fieldSize;


    public FieldModel(int fieldSize) {
        this.fieldSize = fieldSize;
        fieldMatrix = new char[(int) Math.pow(fieldSize, 2)];
    }

    public Shot processShot(Point point) {
        //todo develop
        Shot shot = checkHit(point);
        if (shot != null) {
            recordShot(shot);
            updateFieldMatrix();
        }
        return shot;
    }

    private Shot checkHit(Point point) {
        if (!pointIsValid(point)) {
            return null;
        }
        boolean isHit = false;
        for (Ship ship : ships) {
            if (ship.checkHit(point)) {
                isHit = true;
                break;
            }
        }
        return new DefaultShot(point.x, point.y, isHit);
    }

    private boolean pointIsValid(Point point) {
        return point.x >= 0 && point.x < fieldSize && point.y >= 0 && point.y < fieldSize;
    }

    private void recordShot(Shot shot) {
        shotsHistory.add(shot);
    }

    public void updateFieldMatrix() {
        for (Shot next : shotsHistory) {
            if (next.isHit()) {
                fieldMatrix[convertCoordinatesToCellIndex(next.getPoint())] = FieldConstants.HIT;
            } else {
                fieldMatrix[convertCoordinatesToCellIndex(next.getPoint())] = FieldConstants.MISS;
            }
        }
        //outputFieldMatrix();
    }

    public boolean allShipsAreHit() {
        for (Ship ship : ships) {
            if (!ship.isHit()) {
                return false;
            }
        }
        return true;
    }

    public boolean reset() {
        resetFieldMatrix();
        resetShotsHistory();
        return respawnShips();
    }

    private void resetFieldMatrix() {
        for (int i = 0; i < fieldMatrix.length; i++) {
//            todo: is it proper usage of Enum?
            fieldMatrix[i] = FieldConstants.HIDDEN;
        }
    }

    private void resetShotsHistory() {
        shotsHistory.clear();
    }

    private boolean respawnShips() {
        ships.clear();
        ShipInitialData[] shipsData = ShipInitialData.values();
        for (ShipInitialData data : shipsData) {
            Ship ship = placeShip(data.getSize());
            if (ship != null) {
                ships.add(ship);
                drawShip(ship);
            } else {
                return false;
            }

        }
        return true;
//        resetFieldMatrix();
    }

    private Ship placeShip(int shipSize) {
        int placeAttemptsDirectionByShip = 0;
        int placeAttemptsPointByDirection = 0;
        while (placeAttemptsDirectionByShip < PLACE_ATTEMPTS_DIRECTION_BY_SHIP_LIMIT) {
            ShipDirection direction = ShipDirection.getRandomDirection();
            Point gapsData = initGapsData(direction, shipSize);
            while (placeAttemptsPointByDirection < PLACE_ATTEMPTS_POINT_BY_DIRECTION_LIMIT) {
                Point feedPosition = new Point(RANDOM.nextInt(fieldSize - gapsData.x), RANDOM.nextInt(fieldSize - gapsData.y));
                if (positionIsValid(feedPosition, direction, shipSize)) {
                    return new Ship(feedPosition, direction, shipSize);
                }
                placeAttemptsPointByDirection++;
            }
            placeAttemptsDirectionByShip++;
        }
        return null;
    }

    private void askForRespawn() {
        //    todo:    develop
    }

    private void drawShip(Ship ship) {
        int feedPositionIndex = convertCoordinatesToCellIndex(ship.getFeedPosition());
        int index = 0;
        switch (ship.getDirection()) {
            case VERTICAL:
                index = fieldSize;
                break;
            case HORIZONTAL:
                index = 1;
                break;
        }
        for (int i = 0; i < ship.getSize(); i++) {
            fieldMatrix[feedPositionIndex + i * index] = FieldConstants.SHIP;
        }
    }

    private Point initGapsData(ShipDirection direction, int size) {
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

    private boolean positionIsValid(Point feedPosition, ShipDirection direction, int shipSize) {
        int minX = Math.max(0, feedPosition.x - 1);
        int minY = Math.max(0, feedPosition.y - 1);
        int maxX;
        int maxY;
        switch (direction) {
            case HORIZONTAL:
                maxX = Math.min(fieldSize - 1, feedPosition.x + shipSize);
                maxY = Math.min(fieldSize - 1, feedPosition.y + 1);
                break;
            case VERTICAL:
                maxX = Math.min(fieldSize - 1, feedPosition.x + 1);
                maxY = Math.min(fieldSize - 1, feedPosition.y + shipSize);
                break;
            default:
                maxX = feedPosition.x;
                maxY = feedPosition.y;
        }
        for (int i = 0; i < maxX - minX + 1; i++) {
            for (int j = 0; j < maxY - minY + 1; j++) {
                if (fieldMatrix[(minY + j) * fieldSize + minX + i] == FieldConstants.SHIP) {
                    return false;
                }
            }
        }
        return true;
    }

    private int convertCoordinatesToCellIndex(Point point) {
        return point.y * fieldSize + point.x;
    }
}
