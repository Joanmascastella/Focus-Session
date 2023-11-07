package com.mas.joan.focussession.Controller;

import com.mas.joan.focussession.Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TaskController {
    @FXML
    private TableView taskView;
    @FXML
    private TextField title;
    @FXML
    private TextField description;
    @FXML
    private Button addTask;
    @FXML
    private Button editTask;
    @FXML
    private Button deleteTask;
    private Database database;

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void onAddTaskButtonClick() {
    }

    public void onEditTaskButtonClick() {
    }

    public void onDeleteButtonClick() {
    }
}
