package com.gleb.seabattle.controller;

import com.gleb.seabattle.model.GeneralModel;
import com.gleb.seabattle.model.Playable;
import com.gleb.seabattle.view.GeneralView;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class GeneralController {
    private final GeneralModel model;
    private final GeneralView view;
    private final Logic logic = new Logic();
    private boolean lot;

    public GeneralController(GeneralModel model, GeneralView view) {

        this.model = model;
        this.view = view;
        Random random = new Random();
        lot = random.nextBoolean();
    }

    public void startGame() throws IOException {
        Playable player1 = new HumanPlayer();
        Playable player2 = new AiPlayer(model.FIELD_SIZE);
        model.reset();
//        todo: pause thread instead of infinite loop
        while (logic.endOfGame()) {
            if (lot) {
                model.fieldModel2.processShot(player1.makeShot());
            } else {
                model.fieldModel1.processShot(player2.makeShot());
            }
            lot = !lot;
        }
    }

}
