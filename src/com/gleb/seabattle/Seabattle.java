package com.gleb.seabattle;

import com.gleb.seabattle.controller.GeneralController;
import com.gleb.seabattle.model.GeneralModel;
import com.gleb.seabattle.view.GeneralView;
import com.gleb.seabattle.view.SwingView;
import com.gleb.seabattle.view.View;

import java.io.IOException;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 16.06.2015.
 */
public class Seabattle {
    public static final int FIELD_SIZE = 10;

    public static void main(String[] args) throws IOException {
        GeneralModel model = new GeneralModel(FIELD_SIZE);
        View view = new GeneralView(model);
        GeneralController controller = new GeneralController(model, view);
        controller.startGame();
    }
}
