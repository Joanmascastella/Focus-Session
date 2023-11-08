package com.mas.joan.focussession.Database;

import com.mas.joan.focussession.Model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    List<Task> opentaskList = new ArrayList<>();
    List<Task> closetaskList = new ArrayList<>();
    private static Database instance;
    private Database() {
        loadDatabaseFromFile();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }


        public void addOpenTask(Task task) {
        if (task.getStatus() == com.mas.joan.focussession.Enums.Status.Open) {
            opentaskList.add(task);
            saveDatabaseToFile();
        } else {
            addOpenTask(task);
            saveDatabaseToFile();
        }

    }

    public void addCloseTask(Task task) {
        if (task.getStatus() == com.mas.joan.focussession.Enums.Status.Resolved) {
            closetaskList.add(task);
            saveDatabaseToFile();
        } else {
            addOpenTask(task);
            saveDatabaseToFile();
        }
    }

    public void removeTask(Task task) {
        opentaskList.remove(task);
        saveDatabaseToFile();
    }

    public void updateTask(Task updatedTask) {
        int index = opentaskList.indexOf(updatedTask);
        if (index != -1) {
            opentaskList.set(index, updatedTask);
            saveDatabaseToFile();
        }
    }

    public List<Task> getOpenTask() {
        return opentaskList;
    }

    public List<Task> getCloseTask() {
        return closetaskList;
    }

    public void saveDatabaseToFile() {
        File file = new File("database.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(opentaskList);
            oos.writeObject(closetaskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadDatabaseFromFile() {
        File file = new File("database.dat");
        if (!file.exists()) {
            System.out.println("DAT file does not exist");
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                opentaskList = (List<Task>) ois.readObject();
                closetaskList = (List<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data from file");
                e.printStackTrace();
            }
        }
    }

}


