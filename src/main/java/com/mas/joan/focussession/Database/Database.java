package com.mas.joan.focussession.Database;

import com.mas.joan.focussession.Model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

   List<Task> taskList = new ArrayList<>();

    public void addTask(Task task){
        taskList.add(task);
        saveDatabaseToFile();
    }

    public void removeTask(Task task){
        taskList.remove(task);
        saveDatabaseToFile();
    }

    public void editTask(Task task){
        taskList.set(taskList.indexOf(task), task);
        saveDatabaseToFile();
    }

    public Task getTask(Task task){
        return taskList.get(taskList.indexOf(task));
    }

    public void saveDatabaseToFile(){
        File file = new File("database.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(taskList);
       } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadDatabaseFromFile(){
        File file = new File("database.dat");
        if (!file.exists()) {
            System.out.println("DAT file does not exist");
        }
        else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                taskList = (List<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data from file");
                e.printStackTrace();
            }
        }
    }

}


