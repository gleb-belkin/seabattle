package com.gleb.seabattle.assets;

import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public enum ShipDirection {
    HORIZONTAL,
    VERTICAL;

    private static final Random RANDOM = new Random();
    private static final int SIZE = values().length;


    public static ShipDirection getRandomDirection() {
        return values()[RANDOM.nextInt(SIZE)];
    }
}
