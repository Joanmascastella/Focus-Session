package com.mas.joan.focussession.Database;

import com.mas.joan.focussession.Model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

   List<Task> taskList = new ArrayList<>();

    public Database() {
        loadDatabaseFromFile();
    }

    public void addTask(Task task){
        taskList.add(task);
        saveDatabaseToFile();
    }

    public void removeTask(Task task){
        taskList.remove(task);
        saveDatabaseToFile();
    }

    public void updateTask(Task updatedTask) {
        int index = taskList.indexOf(updatedTask);
        if (index != -1) {
            taskList.set(index, updatedTask);
            saveDatabaseToFile();
        }
    }
    public List<Task> getTask() {
        return taskList;
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


