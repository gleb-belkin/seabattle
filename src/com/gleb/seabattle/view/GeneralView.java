package com.gleb.seabattle.view;

import com.gleb.seabattle.assets.FieldConstants;
import com.gleb.seabattle.assets.FieldId;
import com.gleb.seabattle.model.GeneralModel;

/**
 * Created by Gleb Belkin (gleb.belkin@outlook.com) on 18.07.2015.
 */
public class GeneralView {
    private final GeneralModel model;

    public GeneralView(GeneralModel model) {
        this.model = model;
    }

    public void outputField(FieldId fieldId, char[] fieldMatrix1) {
        System.out.println(fieldId);
        for (int i = 0; i < FieldConstants.COORDINATE_LETTERS.length(); i++) {
            String prefix = "  ";
            if (i == 0) {
                prefix = "    ";
            }
            System.out.printf("%1$s%2$s", prefix, FieldConstants.COORDINATE_LETTERS.charAt(i));
        }
        System.out.print("\n");
        for (int i = 0; i < fieldMatrix1.length; i++) {
            String prefix = "";
            String suffix = "  ";
            if (i % model.getFieldSize() == 0) {
                int rowIndex = i / model.getFieldSize() + 1;
                String rowIndexPrefix;
                if (rowIndex < 10) {
                    rowIndexPrefix = " ";
                } else {
                    rowIndexPrefix = "";
                }
                prefix = String.format("%1$s%2$s  ", rowIndexPrefix, rowIndex);
            }
            if ((i + 1) % model.getFieldSize() == 0) {
                suffix = "\n";
            }
            System.out.printf("%1$s%2$s%3$s", prefix, fieldMatrix1[i], suffix);
        }
    }


    public void showServiceMessage(String message) {
        System.out.printf("[Service message:] %1$s", message);
    }

}
