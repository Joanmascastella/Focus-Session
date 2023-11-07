package com.mas.joan.focussession.Controller;

import com.mas.joan.focussession.Database.Database;
import com.mas.joan.focussession.Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.mas.joan.focussession.Enums.Status.Open;

public class TaskController implements Initializable {
    private ObservableList<Task> tasks;
    @FXML
    private Label message;
    @FXML
    private TextField searchInput;
    @FXML
    private TableView<Task> taskView;
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
    private Task selectedTaskForEdit;

    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasks = FXCollections.observableArrayList();
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> filterProducts(newValue));
        taskView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedTaskForEdit = newSelection;
                title.setText(selectedTaskForEdit.getTitle());
                description.setText(selectedTaskForEdit.getDescription());

            }
        });
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
            tasks.addAll(database.getTask());
            taskView.setItems(tasks);
        } catch (Exception e) {
        }
    }


    public void onAddTaskButtonClick() {
        LocalDate date = LocalDate.now();
        Task task = new Task(date, title.getText(), description.getText(), 0, Open);
        database.addTask(task);
        tasks.add(task);
        taskView.setItems(tasks);
        ClearTextFields();
        message.setText("Task has been added successfully.");
    }


    public void onEditTaskButtonClick() {
        if (selectedTaskForEdit != null) {
            String newTitle = title.getText();
            String newDescription = description.getText().isEmpty() ? selectedTaskForEdit.getDescription() : description.getText();
            LocalDate currentDate = LocalDate.now();
            selectedTaskForEdit.setTitle(newTitle);
            selectedTaskForEdit.setDescription(newDescription);
            selectedTaskForEdit.setStart_time(currentDate);
            database.updateTask(selectedTaskForEdit);
            taskView.refresh();

            ClearTextFields();
            message.setText("Task has been edited successfully.");
        } else {
            message.setText("Please select a task to edit.");
        }
    }


    public void onDeleteButtonClick() {
        try {
            ObservableList<Task> tasksToDelete = taskView.getSelectionModel().getSelectedItems();
            for (Task task : tasksToDelete) {
                database.removeTask(task);
            }
            tasks.removeAll(tasksToDelete);
            message.setText("Task(s) have been deleted successfully.");
        } catch (Exception e) {
            message.setText("Error deleting task(s).");
        }
    }

    private void ClearTextFields() {
        title.clear();
        description.clear();
    }

    private void setPromptText() {
        Task selectedTask = taskView.getSelectionModel().getSelectedItem();
        title.setText(selectedTask.getTitle());
        description.setText(selectedTask.getDescription());
    }


}
