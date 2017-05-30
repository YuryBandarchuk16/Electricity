package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Electricity");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        Main.primaryStage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void close() {
        primaryStage.close();
    }
}
