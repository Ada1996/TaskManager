package com.company;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> tasks = null;

    public TaskManager(){
        this.tasks = new ArrayList<>();
    }

    public int getCountTasks(){
        return this.tasks.size();
    }

    public void add(Task task) {
        if(task == null)
            throw new NullPointerException();
        else
            this.tasks.add(task);
    }

    public void delete(Task task){
        if(task != null){
            this.tasks.remove(task);
        }
    }

    public List<Task> getTasks() {
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
