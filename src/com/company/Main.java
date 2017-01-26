package com.company;

import javax.swing.*;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        MainForm form = new MainForm("Task Manager");
        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setSize(650, 400);
    }
}
