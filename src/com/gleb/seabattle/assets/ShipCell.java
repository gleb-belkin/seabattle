package com.gleb.seabattle.assets;

import java.awt.*;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 17.07.2015.
 */

public class ShipCell extends Point {

    private boolean hit = false;

    public ShipCell() {
        super(0, 0);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit() {
        this.hit = true;
    }
}
