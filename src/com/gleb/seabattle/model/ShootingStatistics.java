package com.gleb.seabattle.model;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 25.07.2015.
 */
public class ShootingStatistics {
    public int getSucceededShotsNumber() {
        return succeeded;
    }

    public int getMissedShotsNumber() {
        return missedShotsNumber;
    }

    public int getTotalShipCellsNumber() {
        return totalShipCells;
    }

    public int getTotalCellsNumber() {
        return totalCells;
    }

    private final int succeeded;
    private final int missedShotsNumber;
    private final int totalShipCells;
    private final int totalCells;

    public ShootingStatistics(int succeeded, int missedShotsNumber, int totalShipCells, int totalCells) {

        this.succeeded = succeeded;
        this.missedShotsNumber = missedShotsNumber;
        this.totalShipCells = totalShipCells;
        this.totalCells = totalCells;
    }
}
