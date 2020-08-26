package view;

import controller.Controller;

import java.io.IOException;

public interface View {
    void paint();
    void run() throws IOException;
    void setController(Controller controller);
}
