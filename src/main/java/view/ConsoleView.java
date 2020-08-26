package view;

import controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView implements View {
    private Controller controller;
    private int turnSign;
    private boolean crossWin = false;
    private boolean noughtWin = false;
    private boolean isTide = false;


    public void paint() {
        char[][] field = controller.getField();
        for (char[] column : field) {
            for (char row : column) {
                System.out.print(" " + row + " ");
            }
            System.out.println();
        }
    }
    public void run() throws IOException {
        turnSign = Math.random() > 0.5 ? 1 : 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        paint();
        while(!isTide && !crossWin && !noughtWin) {

            System.out.println("Player " + (turnSign == 1 ? "Nought" : "Cross") + ", enter number of row from 1 to 9");
            while (true) {
                String answer = reader.readLine();
                int rowNumber;
                if (isInteger(answer) && (rowNumber = Integer.parseInt(answer)) <= 9 && rowNumber > 0) {
                    if(controller.rowInsert(rowNumber, turnSign)) {
                        break;
                    } else {
                        System.out.println("This row is already taken. Please, enter another row.");
                    }
                } else {
                    System.out.println("Wrong input. Please, repeat.");
                }
            }
            paint();
            int respond = controller.checkLines();
            switch (respond) {
                case -2:
                    turnSign = (turnSign + 1) % 2;
                    break;
                case -1:
                    isTide = true;
                    System.out.println("It is tide!");
                    break;
                case 0:
                    noughtWin = true;
                    System.out.println("Player Nought win!");
                    break;
                case 1:
                    crossWin = true;
                    System.out.println("Player Cross win!");
                    break;
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    private boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
