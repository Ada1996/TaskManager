/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Настя
 */
public class Client implements Runnable{
    private Socket socket;
    
    public Client(Socket s){
        socket = s;
    }
        
    public void run(){ 
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            String client = in.readUTF();
            List<Task> tasks = TaskManager.getTasksFromFiles(MainForm.pathCatalog);
            TaskManager tasksClient = new TaskManager(); 
            for (Task x : tasks) {
                if (x.getClient().equals(client)) {
                    tasksClient.add(x) ;
                }
            }           
            out.writeObject( tasksClient);   
            out.close();
            in.close();            
            socket.close();
        }
        catch (IOException e) {}       
    }
}
