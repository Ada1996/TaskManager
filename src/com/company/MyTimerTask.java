package com.company;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
                if (x.getDateOfMessage().equals(formattedDate)) {
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
