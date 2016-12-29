package com.company;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static List<Task> tasks = null;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public static int getCountTasks() {
        return tasks.size();
    }

    public static void add(Task task) {
        if (task == null)
            throw new NullPointerException();
        else
            tasks.add(task);
    }

    public static void delete(Task task) {
        if (task != null) {
            tasks.remove(task);
        }
    }

    public static List<Task> getTasks()  {
        return tasks;
    }
    
    public static List<Task> getTasksFromFiles(String pathCatalog) {
        File f = null;
        File[] paths;
        f = new File(pathCatalog);
        paths = f.listFiles();
        for (File path : paths) {
            String pathStr = path.toString();
            if (pathStr.lastIndexOf("txt") == (pathStr.length() - 3)) {
                Task task = null;
                try {
                    task = TaskManager.getTaskFromFile(pathStr);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                tasks.add(task);
            }          
        }
        return tasks; 
    }

    public static void addTaskToFile(Task Task, String pathToFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathToFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Task);
        fos.close();
        oos.close();
    }

    public static Task getTaskFromFile(String pathToFile) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(pathToFile);
        ObjectInputStream oin = new ObjectInputStream(fis);
        Task ts = (Task) oin.readObject();
        fis.close();
        oin.close();
        return ts;
    }

    public static void renameFile(String pathToFile, String newPathToFile) {
        java.io.File file = new java.io.File(pathToFile);
        if (file.exists()) { // если файл существует, то переименовываем его
            file.renameTo(new java.io.File(newPathToFile));
        } else {
            System.out.println("File not found!");
        }
    }

    public static boolean equalsTasks(String pathTask, String pathCatalog) {
        boolean flag = true;
        File f = null;
        File[] paths;
        f = new File(pathCatalog);
        paths = f.listFiles();
        for (File path : paths) {
            String pathStr = path.toString();
            if (!pathStr.equals(pathTask)) flag = true;
            else flag = false;
        }
        return flag;
    }

}
