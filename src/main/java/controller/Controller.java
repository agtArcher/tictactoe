package controller;

import model.Field;
import view.View;

public class Controller {
    private Field field;
    private View view;

    public Controller(Field field, View view) {
        this.field = field;
        this.view = view;
    }

    public char[][] getField() {
        return field.getField();
    }

    public boolean rowInsert(int row, int turn) {
        char symbol = turn == 0 ? 'X' : 'O';
        return field.setSymbol(row - 1, symbol);
    }

    public int checkLines() {
        return field.checkLines();
    }

    public boolean checkCrossLine() {
        return false;
    }
}
