package sample;

import javafx.event.ActionEvent;

public class Controller {
    public void clicked2DMode(ActionEvent actionEvent) {
    }

    public void clicked3DMode(ActionEvent actionEvent) {
        Main.runMode3D();
        Main.close();
    }
}
