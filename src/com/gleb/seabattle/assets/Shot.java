package com.gleb.seabattle.assets;

import java.awt.*;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 17.07.2015.
 */

public class Shot extends Point {

    public boolean isHit() {
        return hit;
    }

    private final boolean hit;

    public Shot(int x, int y, boolean hit) {
        super(x, y);
        this.hit = hit;
    }
}
