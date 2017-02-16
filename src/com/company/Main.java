package com.company;

import javax.swing.*;
import java.io.*;
import java.net.*;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MainForm form = new MainForm("Task Manager");
        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setSize(650, 400);
        form.setLocationRelativeTo(null);


        int port = 180;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.exit(-1);
        }

        try {
            for (; ; ) {
                Socket socket = server.accept();
                new Thread(new Client(socket)).start();
            }
        } catch (IOException e) {
        }
    }
}
