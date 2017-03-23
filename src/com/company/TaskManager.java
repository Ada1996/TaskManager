package com.company;

import java.io.*;
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

    public static List<Task> getTasks() {
        return tasks;
    }

    public static List<Task> getTasksFromFiles(String pathCatalog) {
        File f = new File(pathCatalog);
        File[] paths = f.listFiles();
        tasks.clear();
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
        boolean flag = false;
        File f = null;
        f = new File(pathCatalog);
        File[] paths = f.listFiles();
        for (File path : paths) {
            String pathStr = path.toString();
            if (pathStr.equals(pathTask)) flag = true;
        }
        return flag;
    }


    public static void addNameToFile(String name) throws IOException {

        OutputStream os = new FileOutputStream("clientsNames.txt", true);
        name = name + ',';
        os.write(name.getBytes());
        os.close();
    }

    public static String[] getNamesFromFile() throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream("clientsNames.txt");
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = is.read()) != -1) {
            sb.append((char) c);
        }
        String[] names = sb.toString().split(",");

        is.close();
        return names;
    }

    public static void delNameFromFile(String name) throws IOException, ClassNotFoundException {

        String[] names = getNamesFromFile();
        StringBuilder newNames = new StringBuilder();
        int number = -1;
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                number = i;
            }
        }
        for (int i = 0; i < number; i++) {
            newNames.append(names[i]);
            newNames.append(',');
        }
        for (int i = number+1; i < names.length; i++) {
            newNames.append(names[i]);
            newNames.append(',');
        }

        OutputStream os = new FileOutputStream("clientsNames.txt");
        os.write(newNames.toString().getBytes());
        os.close();

    }

    public static boolean isAlignmentNames(String name) throws IOException, ClassNotFoundException {
        int alignment = 0;
        String[] names = TaskManager.getNamesFromFile();
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                alignment++;
            }
        }
        if (alignment > 0) {
            return true;
        } else {
            return false;
        }
    }

}
