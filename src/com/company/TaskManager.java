package com.company;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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

    public static void addTaskToFile(Task Task, String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Task);
    }

    public static Task getTaskFromFile(String name) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(name);
        ObjectInputStream oin = new ObjectInputStream(fis);
        Task ts = (Task) oin.readObject();
        return ts;
    }
     public void writeTasks(String s){
        File f = null;
        File[] paths;     //"C:\\Users\\Настя\\Documents\\NetBeansProjects\\TaskManager\\Tasks"
        try{      
            f = new File(s);       
            paths = f.listFiles();        
            for(File path:paths)  {   
                String pathStr=path.toString();
                if (pathStr.substring(pathStr.length()-3, pathStr.length()).equals("txt")){
                   Task task = TaskManager.getTaskFromFile(pathStr);
                   this.add(task);
                }
            }
         }
        catch(Exception e){JOptionPane.showMessageDialog(null, "Неверный путь!", "Ошибка", JOptionPane.ERROR_MESSAGE);}
   }
}
