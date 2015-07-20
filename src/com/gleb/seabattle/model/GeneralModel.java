package com.gleb.seabattle.model;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class GeneralModel {
    public static final int FIELD_SIZE = 10;
    public FieldModel fieldModel1 = new FieldModel(FIELD_SIZE);
    public FieldModel fieldModel2 = new FieldModel(FIELD_SIZE);

    public void reset() {
        try {
            fieldModel1.reset();
            fieldModel2.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
