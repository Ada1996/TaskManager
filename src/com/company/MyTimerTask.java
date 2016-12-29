package com.company;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by daryo on 27.12.2016.
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        List<Task> tasks = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String formattedDate = df.format(calendar.getTime());

        System.out.println("Сейчас " + formattedDate);

        String pathRoot = System.getProperty("user.dir");
        String pathCatalog = pathRoot + "\\Tasks";
        File catalog;
        if (!new File(pathCatalog).exists()) {
            catalog = new File(pathCatalog);
        }
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


        for (Task x : tasks) {
            if (x.getDateOfMessage().equals(formattedDate)) {
                System.out.println("У вас сообщение!");
            }
        }
    }
}
