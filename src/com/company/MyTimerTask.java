package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by daryo on 27.12.2016.
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        List<Task> tasks = TaskManager.getTasks();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String formattedDate = df.format(calendar.getTime());

        System.out.println("Сейчас " + formattedDate);
        for (Task x : tasks) {
            if (x.getDateOfMessage() == formattedDate) {
                System.out.println("У вас сообщение!");
            }
        }
    }
}
