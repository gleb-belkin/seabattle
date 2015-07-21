package com.gleb.seabattle.model;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class GeneralModel {

    public int getFieldSize() {
        return fieldSize;
    }

    private final int fieldSize;
    public FieldModel fieldModel1;
    public FieldModel fieldModel2;

    public GeneralModel(int fieldSize) {

        this.fieldSize = fieldSize;
        fieldModel1 = new FieldModel(fieldSize);
        fieldModel2 = new FieldModel(fieldSize);
    }

    public boolean reset() {
        return fieldModel1.reset() && fieldModel2.reset();
    }
}
