package com.mas.joan.focussession.Controller;

import com.mas.joan.focussession.Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML private Button focusButton;
    @FXML private Button tasksButton;
    @FXML private Button overviewButton;
    @FXML private VBox newViewContainer;
    @FXML private Label message;
    private Database database;
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onFocusButtonClick(ActionEvent actionEvent) {
    }

    public void onTasksButtonClick(ActionEvent actionEvent) {
    }

    public void onOverviewButtonClick(ActionEvent actionEvent) {
    }


}

