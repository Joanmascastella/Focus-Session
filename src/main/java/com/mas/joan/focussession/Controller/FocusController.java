package com.mas.joan.focussession.Controller;

import com.mas.joan.focussession.Database.Database;
import com.mas.joan.focussession.Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.mas.joan.focussession.Enums.Status.Open;

public class FocusController implements Initializable {
    private ObservableList<Task> tasks;
    public TableView taskView;
    private Database database;
    public void setDatabase(Database database) {
        this.database = database;
        loadData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasks = FXCollections.observableArrayList();
        taskView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadData();
    }

    public void loadData() {
        try {
            tasks.clear();
            tasks.addAll(database.getTask().stream()
                    .filter(task -> task.getStatus() == Open)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            taskView.setItems(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleStart() {
    }

    public void handlePause() {
    }

    public void handleStop() {
    }

}
