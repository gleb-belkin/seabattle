package com.gleb.seabattle.controller;

import com.gleb.seabattle.assets.FieldId;
import com.gleb.seabattle.assets.ServiceMessages;
import com.gleb.seabattle.model.GeneralModel;
import com.gleb.seabattle.view.View;

import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class GeneralController {
    private static final int SHOT_ATTEMPTS_LIMIT = 5;
    private final GeneralModel model;
    private final View view;
    private boolean lot;

    public GeneralController(GeneralModel model, View view) {

        this.model = model;
        this.view = view;
        Random random = new Random();
        lot = random.nextBoolean();
    }

    public void startGame() {
//        Player player1 = new HumanPlayer();
        Player player1 = new AiPlayer(model.getFieldSize());
        if (player1.isHuman()) {
            player1.setName(view.welcomePlayer());
        }
        Player player2 = new AiPlayer(model.getFieldSize());
        if (!model.reset()) {
            view.showServiceMessage(ServiceMessages.NEW_GAME_FAILED_TO_START);
            return;
        }
        view.outputField(FieldId.FIELD_1, model.fieldModel1.getFieldMatrix());
        view.outputField(FieldId.FIELD_2, model.fieldModel2.getFieldMatrix());
//        todo: pause thread instead of infinite loop
        boolean endOfGame = false;
        while (!endOfGame) {
            if (model.fieldModel1.allShipsAreHit()) {
                view.declareWinner(player2.getName());
                endOfGame = true;
            } else if (model.fieldModel2.allShipsAreHit()) {
                view.declareWinner(player1.getName());
                endOfGame = true;
            } else {
                int shotAttempts = 0;
                while (shotAttempts < SHOT_ATTEMPTS_LIMIT) {
                    boolean shotProcessed = lot ? model.fieldModel2.processShot(player1.makeShot()) : model.fieldModel1.processShot(player2.makeShot());
                    if (shotProcessed) {
                        lot = !lot;
                        break;
                    }
                    shotAttempts++;
                }
                if (shotAttempts == SHOT_ATTEMPTS_LIMIT) {
                    view.showServiceMessage(ServiceMessages.SHOT_ATTEMPTS_LIMIT_EXCEEDED);
                    break;
                }
                view.outputField(FieldId.FIELD_1, model.fieldModel1.getFieldMatrix());
                view.outputField(FieldId.FIELD_2, model.fieldModel2.getFieldMatrix());
            }
        }
    }

}
