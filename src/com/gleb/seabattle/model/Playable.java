package com.gleb.seabattle.model;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 17.07.2015.
 */
public interface Playable {
    Point makeShot() throws IOException;
}
