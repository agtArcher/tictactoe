package model;

public class Field {
    public static final int FIELD_SIZE = 3;
    private char[][] field;
    private final String[] winConditions;
    
    public Field() {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = '.';
            }
        }
        StringBuilder nought = new StringBuilder();
        StringBuilder cross = new StringBuilder();
        for (int i = 0; i < FIELD_SIZE; i++) {
            nought.append('O');
            cross.append('X');
        }
        winConditions = new String[]{nought.toString(), cross.toString()};
    }

    public char[][] getField() {
        return field;
    }

    public boolean setSymbol(int position, char symbol) {
        int x = position % FIELD_SIZE;
        int y = position / FIELD_SIZE;

        if (field[y][x] == '.') {
            field[y][x] = symbol;
            return true;
        } else {
            return false;
        }
    }
    
    //checking lines. if find '.' in line, interrupt appending and continue cycle
    //first check horizontal, then vertical and diagonal last.
    //return -2 if no one win and no tide, -1 if tide, 0 if nought, 1 if cross
    public int checkLines() {
        boolean isTide = false;
        StringBuilder rowBuilder;
        boolean interrupt;
        //horizontal lines check, if '.' is present, tide is false.
        for (char[] row : field) {
            rowBuilder = new StringBuilder();
            interrupt = false;
            for (char cell : row) {
                if (cell == '.') {
                    isTide = true;
                    interrupt = true;
                    break;
                }
                rowBuilder.append(cell);
            }
            if (!interrupt) {
                int result = checkRow(rowBuilder.toString());
                if (result != -2) return result;
            }
        }
        //vertical lines check
        for (int i = 0; i < FIELD_SIZE; i++) {
            rowBuilder = new StringBuilder();
            interrupt = false;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[j][i] == '.') {
                    interrupt = true;
                    break;
                }
                rowBuilder.append(field[j][i]);
            }
            if (!interrupt) {
                int result = checkRow(rowBuilder.toString());
                if (result != -2) return result;
            }
        }
        //diagonal lines check
        rowBuilder = new StringBuilder();
        interrupt = false;
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (field[i][i] == '.') {
                interrupt = true;
                break;
            }
            rowBuilder.append(field[i][i]);
        }
        int result;
        if (!interrupt) {
            result = checkRow(rowBuilder.toString());
            if (result != -2) return result;
        }
        
        
        //second diagonal check
        rowBuilder = new StringBuilder();
        interrupt = false;
        int xLimit = FIELD_SIZE - 1;
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (field[i][i] == '.') {
                interrupt = true;
                break;
            }
            rowBuilder.append(field[i][xLimit - i]);
        }
        if (!interrupt) {
            result = checkRow(rowBuilder.toString());
            if (result != -2) return result;
            else if (!isTide) {
                return -1;
            }
        }
        return -2;
    }
    
    
    private int checkRow(String row) {
        if (row.equals(winConditions[0])) {
            return 0;
        } else if (row.equals(winConditions[1])) {
            return 1;
        } else return -2;
    }
    
}
