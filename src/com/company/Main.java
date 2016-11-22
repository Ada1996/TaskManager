package com.company;

import javax.swing.*;
import java.io.IOException;
import java.util.GregorianCalendar;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

       /* Task ex = new Task("First Task", "blabla", new GregorianCalendar(1996, 11, 15, 23, 11), "tetya");
        TaskManager.addTaskToFile(ex,"First Task.txt");
        Task ex1 = new Task("Second Task", "blabla", new GregorianCalendar(1996, 11, 15, 23, 7), "tetya");
        TaskManager.addTaskToFile(ex1,"Second Task.txt");
        Task ex2 = new Task("Third Task", "blabla", new GregorianCalendar(1996, 11, 15, 23, 7), "tetya");
        TaskManager.addTaskToFile(ex2,"Third Task.txt");

        TaskManager journal = new TaskManager();
        Task task = TaskManager.getTaskFromFile("First Task.txt");
        Task task1 = TaskManager.getTaskFromFile("Second Task.txt");
        Task task2 = TaskManager.getTaskFromFile("Third Task.txt");

        journal.add(ex);
        journal.add(task1);
        journal.add(task2);

        System.out.println(journal.getCountTasks());*/


        MainForm form = new MainForm("Task Manager");
        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setSize(600, 400);

    }
}
