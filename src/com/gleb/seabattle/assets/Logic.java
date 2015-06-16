package com.gleb.seabattle.assets;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Logic {

    private static ArrayList<Point> shotsHistory = new ArrayList<Point>();
    private static ArrayList<Ship> ships = new ArrayList<Ship>();
    private static final int fieldSize = 10;

    public static void newGame() {
        resetShotsHistory();
        respawnShips();
    }

    private static void respawnShips() {
        ships.clear();
        ShipInitialData[] shipsData = ShipInitialData.values();
        for (int i = 0; i < shipsData.length; i++) {
            ShipInitialData value = shipsData[i];
        }
    }

    private static void resetShotsHistory() {
        shotsHistory.clear();
    }
}
