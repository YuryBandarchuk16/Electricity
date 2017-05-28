package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import ru.ifmo.ctddev.bandarchuk.expression.DivisionByZeroException;
import ru.ifmo.ctddev.bandarchuk.expression.OverflowException;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Mode3DController {

    @FXML
    private Label eResult;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private TextField zField;


    public void evaluate(ActionEvent actionEvent) throws OverflowException, DivisionByZeroException {
        double value = Controller.field.getEAt(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()),
                Double.parseDouble(zField.getText()));
        eResult.setText(Controller.decimalFormat.format(value));
        System.out.println("!");
    }
}
