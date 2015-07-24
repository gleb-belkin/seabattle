package com.gleb.seabattle.view;

import com.gleb.seabattle.assets.FieldId;
import com.gleb.seabattle.model.GeneralModel;

import javax.swing.*;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 25.07.2015.
 */
public class SwingView extends JFrame implements View {
    public SwingView(GeneralModel model) {
        setSize(300,200);
        setVisible(true);
    }

    @Override
    public void outputField(FieldId fieldId, char[] fieldMatrix1) {

    }

    @Override
    public void showServiceMessage(String message) {

    }

    @Override
    public String welcomePlayer() {
        return null;
    }

    @Override
    public void declareWinner(String name) {

    }

    @Override
    public void startManualShipPlacement(FieldId fieldId) {

    }

    @Override
    public void showPlayerStatistics(String name, int succeededShotsNumber, int missedShotsNumber, int totalShipCellsNumber, int totalCellsNumber) {

    }
}
