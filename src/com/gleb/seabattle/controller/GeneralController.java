package com.gleb.seabattle.controller;

import com.gleb.seabattle.assets.FieldId;
import com.gleb.seabattle.assets.ServiceMessages;
import com.gleb.seabattle.model.GeneralModel;
import com.gleb.seabattle.model.Shot;
import com.gleb.seabattle.view.GeneralView;

import java.util.Random;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class GeneralController {
    private static final int SHOT_ATTEMPTS_LIMIT = 5;
    private final GeneralModel model;
    private final GeneralView view;
    private boolean lot;

    public GeneralController(GeneralModel model, GeneralView view) {

        this.model = model;
        this.view = view;
        Random random = new Random();
        lot = random.nextBoolean();
    }

    public void startGame() {
        Player player1 = new HumanPlayer();
        Player player2 = new AiPlayer(model.getFieldSize());
        if (!model.reset()) {
            view.showServiceMessage(ServiceMessages.NEW_GAME_FAILED_TO_START);
            return;
        }
//        todo: pause thread instead of infinite loop
        while (model.fieldModel1.allShipsAreHit() || model.fieldModel2.allShipsAreHit()) {
            int shotAttempts = 0;
            while (shotAttempts < SHOT_ATTEMPTS_LIMIT) {
                Shot shot = lot ? model.fieldModel2.processShot(player1.makeShot()) : model.fieldModel1.processShot(player2.makeShot());
                if (shot != null) {
                    view.updateField(shot, lot ? FieldId.FIELD_2 : FieldId.FIELD_1);
                    lot = !lot;
                    break;
                }
                shotAttempts++;
            }
            if (shotAttempts == SHOT_ATTEMPTS_LIMIT) {
                view.showServiceMessage(ServiceMessages.SHOT_ATTEMPTS_LIMIT_EXCEEDED);
                break;
            }
        }
    }

}
