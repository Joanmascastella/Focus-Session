package com.mas.joan.focussession.Controller;

import javafx.fxml.FXML;

import javafx.scene.control.ProgressIndicator;

public class FocusSessionController {
    @FXML
    private ProgressIndicator circularProgress;

    public void initialize() {
        // You can set progress value programmatically here
        // For example: set progress to 50%
        circularProgress.setProgress(0.5);
    }
}

