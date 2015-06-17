package com.gleb.seabattle.assets;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Logic {

    private static ArrayList<Point> shotsHistory = new ArrayList<Point>();
    private static ArrayList<Ship> ships = new ArrayList<Ship>();
    private static final int fieldSize = 10;
    private static final int placeAttemptsPoinByDirectionLimit= 15;
    private static final int placeAttemptsDirectionByShipLimit = 10;
    private static final Random random = new Random();

    public static void newGame() {
        resetShotsHistory();
        respawnShips();
    }

    private static void respawnShips() {
        ships.clear();
        ShipInitialData[] shipsData = ShipInitialData.values();
        for (int i = 0; i < shipsData.length; i++) {
            ShipInitialData data = shipsData[i];
            Ship ship = placeShip(data.getSize());

            ships.add(ship);
        }
    }

    private static Ship placeShip(int size) {
        int placeAttemptsDirectionByShip = 0;
        while (placeAttemptsDirectionByShip < placeAttemptsDirectionByShipLimit)
        {
            placeAttemptsDirectionByShip++;
        }
        return null;
    }

    private static void resetShotsHistory() {
        shotsHistory.clear();
    }
}
