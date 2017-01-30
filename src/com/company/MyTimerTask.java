package com.company;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 * Created by daryo on 27.12.2016.
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        for (;;) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            String formattedDate = df.format(calendar.getTime());

            List<Task> tasks = TaskManager.getTasksFromFiles(MainForm.pathCatalog);

            for (Task x : tasks) {
                if (x.getDate().equals(formattedDate)) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null,"Название: "+ x.getName(), "Вам сообщение!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
