package com.mas.joan.focussession.Controller;

import com.mas.joan.focussession.Database.Database;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button focusButton;
    @FXML
    private Button tasksButton;
    @FXML
    private Button overviewButton;
    @FXML
    private VBox newViewContainer;
    @FXML
    private Label message;
    Database database = Database.getInstance();

    public void initialize() {
        try {
            loadDefaultView();
        } catch (Exception e) {
            message.setText("Error initializing the default view.");
        }
    }

    private void loadDefaultView() {
        onFocusButtonClick();
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            VBox navigationView = loader.load();
            Object controller = loader.getController();
            if (controller instanceof FocusController focusController) {
                focusController.loadData();
            } else if (controller instanceof TaskController taskController) {
                taskController.loadData();
            } else if (controller instanceof OverviewController overviewController) {
               overviewController.loadData();
            }

            newViewContainer.getChildren().setAll(navigationView);
        } catch (IOException e) {
            message.setText("Unable to find view");
        } catch (Exception e) {
            message.setText("Error loading the view.");
        }
    }


    public void onFocusButtonClick() {
        loadView("/com/mas/joan/focussession/FocusView.fxml");
    }

    public void onTasksButtonClick() {
        loadView("/com/mas/joan/focussession/TaskView.fxml");
    }

    public void onOverviewButtonClick() {
        loadView("/com/mas/joan/focussession/OverviewView.fxml");
    }
}

