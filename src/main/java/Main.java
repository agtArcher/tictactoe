import controller.Controller;
import model.Field;
import view.View;
import view.ViewFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Field field = new Field();

        View view = ViewFactory.getView();
        Controller controller = new Controller(field, view);
        view.setController(controller);
        view.run();
    }
}
