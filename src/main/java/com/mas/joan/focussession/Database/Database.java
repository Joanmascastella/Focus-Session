package com.mas.joan.focussession.Database;

import com.mas.joan.focussession.Model.Task;

import java.io.*;
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
//
//    public void saveDatabaseToFile() {
//        databaseData = new DataWrapper(users, products, orders);
//        File file = new File("database.dat");
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
//            oos.writeObject(databaseData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void loadDatabaseFromFile() {
//        File file = new File("database.dat");
//        if (!file.exists()) {
        //    System.out.println("DAT file does not exist, sample data loaded");
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
//            databaseData = (DataWrapper) ois.readObject();
//            users.clear();
//            products.clear();
//            orders.clear();
//            users.addAll(databaseData.users);
//            products.addAll(databaseData.products);
//            orders.addAll(databaseData.orders);
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error loading data from file");
//            e.printStackTrace();
//        }
//    }
}


