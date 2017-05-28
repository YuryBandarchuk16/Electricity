package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ifmo.ctddev.bandarchuk.expression.IncorrectExpressionException;
import ru.ifmo.ctddev.bandarchuk.expression.OverflowException;
import sample.physics.Field;

public class Mode3DWindow extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("thirdmode.fxml"));
        primaryStage.setTitle("3D Mode");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        Mode3DWindow.primaryStage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void close() {
        primaryStage.close();
    }

}
