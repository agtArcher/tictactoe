package view;

import controller.Controller;

public class ViewFactory {
    public static View getView() {
        return new ConsoleView();
    }
}
