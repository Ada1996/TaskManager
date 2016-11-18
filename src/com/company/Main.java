package com.company;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.GregorianCalendar;




public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException{

        Task ex = new Task("First Task", "blabla", new GregorianCalendar(1996, 11, 15, 23, 11), "tetya");

       /* GregorianCalendar gc = new GregorianCalendar(1996, 11, 15, 23, 11);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.print(sdf.format(gc.getTime()));
        String s = sdf.format(gc.getTime());*/
int d = 0;
       TaskManager.addTaskToFile(ex);
        Task t = TaskManager.getTaskFromFile();

//
//
        int k = 0;
    }
}
