package com.mas.joan.focussession.Model;

import com.mas.joan.focussession.Enums.Status;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class Task implements Serializable {
    private String title;
    private String description;
    private double total_time;
    private Status status;

    public void Task(String title, String description, double total_time, Status status) {
        this.title = title;
        this.description = description;
        this.total_time = total_time;
        this.status = status;
    }
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal_time() {
        return total_time;
    }

    public void setTotal_time(double total_time) {
        this.total_time = this.total_time + total_time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
