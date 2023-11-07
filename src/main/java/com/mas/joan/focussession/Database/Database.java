package com.mas.joan.focussession.Database;

import com.mas.joan.focussession.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Database {

   List<Task> taskList = new ArrayList<>();

    public void addTask(Task task){
        taskList.add(task);
    }

    public void removeTask(Task task){
        taskList.remove(task);
    }

    public void editTask(Task task){
        taskList.set(taskList.indexOf(task), task);
    }

    public Task getTask(Task task){
        return taskList.get(taskList.indexOf(task));
    }
    //Create a method to serialize the array list

    //Create a method to deserialize the array list


}
