package com.mas.joan.focussession;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FocusSession extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FocusSession.class.getResource("InitialView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 656.0);
        stage.setTitle("Focus Sessions");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}