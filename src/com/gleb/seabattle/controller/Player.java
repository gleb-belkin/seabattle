package com.gleb.seabattle.controller;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 17.07.2015.
 */
public interface Player {
    Point makeShot();
    String getName();
    void setName(String name);
    boolean isManualShipPlacementEnabled();
    boolean isHuman();
    boolean isWinner();
    void makeWinner();
}
