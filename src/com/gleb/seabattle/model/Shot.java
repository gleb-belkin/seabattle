package com.gleb.seabattle.model;

import java.awt.*;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 21.07.2015.
 */
public class Shot {
    private final boolean hit;
    private Point point;

    public Shot(int x, int y, boolean hit) {
        point = new Point(x, y);
        this.hit = hit;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isHit() {
        return hit;
    }
}
