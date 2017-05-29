package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.ifmo.ctddev.bandarchuk.expression.IncorrectExpressionException;
import ru.ifmo.ctddev.bandarchuk.expression.OverflowException;
import sample.physics.Field;

import java.text.DecimalFormat;

public class Controller {

    @FXML
    private TextField expressionField;

    public static Field field;
    public static DecimalFormat decimalFormat;

    public void clicked2DMode(ActionEvent actionEvent) {
        Main.close();
        Mode2DWindow mode2DWindow = new Mode2DWindow();
        Thread thread = new Thread(mode2DWindow);
        thread.start();
    }

    public void clicked3DMode(ActionEvent actionEvent) throws OverflowException, IncorrectExpressionException {
        decimalFormat = new DecimalFormat("#0.000");
        field = new Field(expressionField.getText());
        Platform.runLater(() -> {
            try {
                new Mode3DWindow().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Main.close();
    }
}
