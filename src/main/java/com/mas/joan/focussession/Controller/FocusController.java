package com.mas.joan.focussession.Controller;

import com.mas.joan.focussession.Database.Database;
import com.mas.joan.focussession.Model.Task;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class FocusController implements Initializable {
    @FXML private Label message;
    @FXML private Label timerText;
    @FXML private RadioButton thirtyMinute;
    @FXML private RadioButton sixtyMinute;
    @FXML private RadioButton ninetyMinute;
    @FXML private TextField customAmount;
    @FXML private TableView<Task> taskView;
    private Timeline timeline;
    private Duration timeLeft = Duration.ZERO;
    private Duration timeElapsed = Duration.ZERO;
    private ObservableList<Task> tasks;
    Database database = Database.getInstance();
    private Task selectedTask;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasks = FXCollections.observableArrayList();
        taskView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // Change to single selection
        taskView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedTask = newSelection;
        });
        loadData();
        timeElapsed = Duration.ZERO;
    }

    public void loadData() {
        try {
            tasks = FXCollections.observableArrayList(database.getOpenTask());
            taskView.setItems(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isTimerRunning() {
        return timeline != null && timeline.getStatus() == Animation.Status.RUNNING;
    }

    private void setTimerControlsDisabled(boolean disabled) {
        thirtyMinute.setDisable(disabled);
        sixtyMinute.setDisable(disabled);
        ninetyMinute.setDisable(disabled);
        customAmount.setDisable(disabled);
    }


    private void updateTimeText(Duration duration) {
        if (!duration.lessThan(Duration.ZERO)) {
            long seconds = (long) duration.toSeconds();
            String time = String.format("%02d:%02d",
                    (seconds / 3600),
                    (seconds % 3600) / 60);
            timerText.setText(time);
        } else {
            timerText.setText("00:00:00");
        }
    }

    public void timerLogic() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    timeLeft = timeLeft.subtract(Duration.seconds(1));
                    updateTimeText(timeLeft);
                    if (timeLeft.lessThanOrEqualTo(Duration.ZERO)) {
                        timeline.stop();
                        showTimeUpAlert();
                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void handleStart() {
        if (selectedTask == null) {
            message.setText("Please select a task to start the timer.");
            return;
        }
        if (isTimerRunning()) {
            message.setText("Timer is already running.");
            return;
        }

        setTimerControlsDisabled(true);
        taskView.setDisable(true);

        if(thirtyMinute.isSelected()) {
            timeLeft = Duration.minutes(30);
        } else if(sixtyMinute.isSelected()) {
            timeLeft = Duration.minutes(60);
        } else if(ninetyMinute.isSelected()) {
            timeLeft = Duration.minutes(90);
        } else {
            try {
                int minutes = Integer.parseInt(customAmount.getText());
                timeLeft = Duration.minutes(minutes);
            } catch (NumberFormatException e) {
                setTimerControlsDisabled(false);
                return;
            }
        }
        updateTimeText(timeLeft);
        timerLogic();
    }

    public void handlePause() {
        if (timeline != null) {
            timeline.pause();
            if (selectedTask != null) {
                double elapsedHours = timeElapsed.toSeconds() / 3600.0;
                selectedTask.setTotal_time(elapsedHours);
                database.updateTask(selectedTask);
                taskView.refresh(); // Refresh the TableView to update the UI
            }
            setTimerControlsDisabled(false);
        }
    }

    public void handleStop() {
        if (timeline != null) {
            timeline.stop();
            if (selectedTask != null) {
                double elapsedHours = timeElapsed.toSeconds() / 3600.0;
                selectedTask.setTotal_time(elapsedHours);
                database.updateTask(selectedTask);
                timeElapsed = Duration.ZERO;
                taskView.refresh(); // Refresh the TableView to update the UI
                selectedTask = null;
            }
            setTimerControlsDisabled(false);
            taskView.setDisable(false);
            timerText.setText("00:00:00");
        }
    }



    public void setTimerThirty() {
        timerText.setText("00:30:00");
    }

    public void setTimerSixty() {
        timerText.setText("00:60:00");
    }

    public void setTimerNinety() {
        timerText.setText("00:90:00");
    }
    private void showTimeUpAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Time's Up!");
        alert.setHeaderText(null);
        alert.setContentText("The countdown has finished!");
        alert.showAndWait();
    }

    private void showNavigationWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Timer is still running");
        alert.setContentText("Please do not switch views while the timer is running.");
        alert.showAndWait();
    }
    public void handleViewSwitching() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            showNavigationWarning();
        }
    }

}
