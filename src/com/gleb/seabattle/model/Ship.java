package com.gleb.seabattle.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Ship {

    private final Point feedPosition;
    private final ShipDirection direction;
    private final int size;
    private Point bowPosition;
    private ArrayList<ShipCell> shipCells = new ArrayList<>();

    public Ship(Point feedPosition, ShipDirection direction, int size) {

        this.feedPosition = feedPosition;
        this.direction = direction;
        this.size = size;
        initBowPosition();
        initCells();
    }

    public Point getFeedPosition() {
        return feedPosition;
    }

    public ShipDirection getDirection() {
        return direction;
    }

    public int getSize() {
        return size;
    }

    private void initCells() {
        for (int i = 0; i < size; i++) {
            shipCells.add(new ShipCell());
        }
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

    public boolean checkHit(Point point) {
        if (point.x >= feedPosition.x && point.x <= bowPosition.x && point.y >= feedPosition.y && point.y <= bowPosition.y) {
            shipCells.get(Math.max(point.x - feedPosition.x, point.y - feedPosition.y)).setHit();
            return true;
        }
        return false;
    }

    public boolean isHit() {
        for (ShipCell shipCell : shipCells) {
            if (!shipCell.isHit()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCellHit(int cellIndex) {
        if (cellIndex < 0 || cellIndex >= size) {
            return false;
        }
        return shipCells.get(cellIndex).isHit();
    }

    private class ShipCell {
        private boolean hit = false;

        public boolean isHit() {
            return hit;
        }

        public void setHit() {
            this.hit = true;
        }
    }
}
