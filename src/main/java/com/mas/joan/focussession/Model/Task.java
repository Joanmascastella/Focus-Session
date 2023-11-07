package com.mas.joan.focussession.Model;

import com.mas.joan.focussession.Enums.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Task implements Serializable {
    private String title;
    private String description;
    private double total_time;
    private Status status;

    private Date start_time;


    public Task(LocalDate start_time, String title, String description, double total_time, Status status) {
        this.start_time = Date.from(start_time.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.title = title;
        this.description = description;
        this.total_time = total_time;
        this.status = status;
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

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDate start_time) {
        this.start_time = Date.from(start_time.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
