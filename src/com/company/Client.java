/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Настя
 */
public class Client implements Runnable {
    private Socket socket;

    public Client(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String client = in.readUTF();          
            if (!TaskManager.isAlignmentNames(client))
                out.writeObject(null);
            else 
            {
                List<Task> tasks = TaskManager.getTasksFromFiles(MainForm.pathCatalog);
                List<Task> tasksClient = new ArrayList<>();
                for (Task x : tasks) {
                    if (x.getClient().equals(client)) {
                        tasksClient.add(x);
                        System.out.println(x.getName());
                    }
                }
                out.writeObject(tasksClient);
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
