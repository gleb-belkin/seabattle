package com.gleb.seabattle.controller;

import java.awt.*;
import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class AiPlayer implements Player {


    private static final Random RANDOM = new Random();
    private final int fieldSize;
    private String name;

    public AiPlayer(int fieldSize) {
//        todo: build field matrix
        this.fieldSize = fieldSize;
    }

    @Override
    public Point makeShot() {
//todo: develop AI =)
        return new Point(RANDOM.nextInt(fieldSize), RANDOM.nextInt(fieldSize));
    }

    @Override
    public String getName() {
        return "AiPlayer";
    }

    @Override
    public boolean isManualShipPlacementEnabled() {
        return false;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void setName(String name) {

        this.name = name;
    }
}
