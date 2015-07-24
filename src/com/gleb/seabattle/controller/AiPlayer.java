package com.gleb.seabattle.controller;

import java.awt.*;
import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class AiPlayer extends AbstractPlayer implements Player {


    private static final Random RANDOM = new Random();
    private final int fieldSize;

    public AiPlayer(int fieldSize) {
//        todo: build field matrix
        this.fieldSize = fieldSize;
    }

    public AiPlayer(int fieldSize, String name) {
//        todo: build field matrix
        this.fieldSize = fieldSize;
        this.name = name;
    }

    @Override
    public Point makeShot() {
//todo: develop AI =)
        return new Point(RANDOM.nextInt(fieldSize), RANDOM.nextInt(fieldSize));
    }

    @Override
    public boolean isManualShipPlacementEnabled() {
        return false;
    }

    @Override
    public boolean isHuman() {
        return false;
    }


}
