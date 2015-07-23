package com.gleb.seabattle.model;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public enum ShipInitialData {
//    BATTLESHIP_1(4),
//    CRUISER_1(3),
//    CRUISER_2(3),
//    DESTROYER_1(2),
//    DESTROYER_2(2),
//    DESTROYER_3(2),
//    SUBMARINE_1(1),
//    SUBMARINE_2(1),
//    SUBMARINE_3(1),
    SUBMARINE_4(1);

    private int size;

    ShipInitialData(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
