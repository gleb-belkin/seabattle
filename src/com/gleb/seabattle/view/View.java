package com.gleb.seabattle.view;

import com.gleb.seabattle.assets.FieldId;

/**
 * Created by glebb_000 on 24.07.2015.
 */
public interface View {
    void outputField(FieldId fieldId, char[] fieldMatrix1);

    void showServiceMessage(String message);

    String welcomePlayer();

    void declareWinner(String name);

    void startManualShipPlacement(FieldId fieldId);

    void showPlayerStatistics(String name, int succeededShotsNumber, int missedShotsNumber, int totalShipCellsNumber, int totalCellsNumber);
}
