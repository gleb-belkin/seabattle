package com.gleb.seabattle.assets;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Ship {

    private final Point feedPosition;
    private final ShipDirection direction;
    //test begin
    public Point getFeedPosition() {
        return feedPosition;
    }

    public ShipDirection getDirection() {
        return direction;
    }

    public int getSize() {
        return size;
    }
    //test end
    private final int size;
    private Point bowPosition;
    private ArrayList<Cell> cells = new ArrayList<Cell>();

    public Ship(Point feedPosition, ShipDirection direction, int size) {

        this.feedPosition = feedPosition;
        this.direction = direction;
        this.size = size;
        initBowPosition();
    }

    private void initBowPosition() {
        switch (this.direction) {
            case HORIZONTAL:
                bowPosition = new Point(feedPosition.x + size - 1, feedPosition.y);
                break;
            case VERTICAL:
                bowPosition = new Point(feedPosition.x, feedPosition.y + size - 1);
                break;
        }
    }

    public boolean checkHit(int x, int y) {
        return x >= feedPosition.x && x <= bowPosition.x && y >= feedPosition.y && y <= bowPosition.y;
    }


    private class Cell extends Point {

        private boolean hit = false;

        public Cell(int x, int y) {
            super(x, y);
        }

        public boolean isHit() {
            return hit;
        }

        public void setHit(boolean hit) {
            this.hit = hit;
        }
    }
}
