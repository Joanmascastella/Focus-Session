package com.mas.joan.focussession.Controller;

import com.mas.joan.focussession.Database.Database;
import com.mas.joan.focussession.Enums.Status;
import com.mas.joan.focussession.Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OverviewController implements Initializable {
    private ObservableList<Task> tasks;
    @FXML private TextField searchInput;
    @FXML private TableView<Task> taskView;
    private Database database;
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasks = FXCollections.observableArrayList();
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> filterProducts(newValue));
        loadData();
    }
    private void filterProducts(String searchInput) {
        if (searchInput.length() >= 3) {
            ObservableList<Task> filteredList = taskView.getItems().filtered(task ->
                    task.getTitle().toLowerCase().contains(searchInput.toLowerCase()));
            taskView.setItems(filteredList);
        } else {
            loadData();
        }
    }
    public void loadData() {
        try {
            tasks.clear();
            tasks.addAll(database.getTask().stream()
                    .filter(task -> task.getStatus() == com.mas.joan.focussession.Enums.Status.Resolved)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            taskView.setItems(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
