package com.gleb.seabattle.assets;

import java.awt.*;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Ship {
    private final Point feedPosition;
    private final ShipDirection direction;
    private final int size;

    public Ship(Point feedPosition, ShipDirection direction, int size) {

        this.feedPosition = feedPosition;
        this.direction = direction;
        this.size = size;
    }
}
