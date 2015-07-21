package com.gleb.seabattle.model;

import java.awt.*;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 21.07.2015.
 */
public class DefaultShot implements Shot {
    private final boolean hit;
    private Point point;

    public DefaultShot(int x, int y, boolean hit) {
        point = new Point(x, y);
        this.hit = hit;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public boolean isHit() {
        return hit;
    }
}
