package com.company;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static List<Task> tasks = null;

   public  TaskManager(){
        tasks = new ArrayList<>();
    }

    public static int getCountTasks(){
        return tasks.size();
    }

    public static void add(Task task) {
        if(task == null)
            throw new NullPointerException();
        else
            tasks.add(task);
    }

    public static void delete(Task task){
        if(task != null){
            tasks.remove(task);
        }
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static void addTaskToFile(Task Task, String pathToFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathToFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Task);
    }

    public static Task getTaskFromFile(String pathToFile) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(pathToFile);
        ObjectInputStream oin = new ObjectInputStream(fis);
        Task ts = (Task) oin.readObject();
        return ts;
    }
     
}
