package com.mas.joan.focussession;

import com.mas.joan.focussession.Controller.MainViewController;
import com.mas.joan.focussession.Database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FocusSession extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Database database = Database.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(FocusSession.class.getResource("MainView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 888, 569);
        stage.setTitle("Focus Sessions");
        stage.setScene(scene);
        stage.show();
        Runtime.getRuntime().addShutdownHook(new Thread(database::saveDatabaseToFile));
    }
    public static void main(String[] args) {
        launch();
    }
}