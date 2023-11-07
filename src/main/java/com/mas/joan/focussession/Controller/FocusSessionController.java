package com.mas.joan.focussession.Controller;

import com.mas.joan.focussession.Database.Database;
import javafx.fxml.FXML;

import javafx.scene.control.ProgressIndicator;

public class FocusSessionController {

    private Database database;
    public void setDatabase(Database database) {
        this.database = database;
    }
}

