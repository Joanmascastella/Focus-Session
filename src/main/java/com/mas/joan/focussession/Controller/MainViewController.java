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
    private FocusController focusController;

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

            if (controller instanceof FocusController) {
                focusController = (FocusController) controller;
                focusController.loadData();
            } else if (controller instanceof TaskController taskController) {
                taskController.loadData();
            } else if (controller instanceof OverviewController overviewController) {
                overviewController.loadData();
            }

            // Before switching views, check if it's possible
            if (controller != focusController || canSwitchView()) {
                newViewContainer.getChildren().setAll(navigationView);
            }

        } catch (IOException e) {
            message.setText("Unable to find view");
        } catch (Exception e) {
            message.setText("Error loading the view.");
        }
    }

    private boolean canSwitchView() {
        if (focusController != null && focusController.isTimerRunning()) {
            focusController.handleViewSwitching();
            return false;
        }
        return true;
    }

    public void onFocusButtonClick() {
        if (canSwitchView()) {
            loadView("/com/mas/joan/focussession/FocusView.fxml");
        }
    }

    public void onTasksButtonClick() {
        if (canSwitchView()) {
            loadView("/com/mas/joan/focussession/TaskView.fxml");
        }
    }

    public void onOverviewButtonClick() {
        if (canSwitchView()) {
            loadView("/com/mas/joan/focussession/OverviewView.fxml");
        }
    }
}

