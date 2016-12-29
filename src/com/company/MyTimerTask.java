package com.company;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Created by daryo on 27.12.2016.
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
       
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String formattedDate = df.format(calendar.getTime());

        System.out.println("Сейчас " + formattedDate);
        List<Task> tasks = TaskManager.getTasksFromFiles(MainForm.pathCatalog);      
        for (Task x : tasks) {
            if (x.getDateOfMessage().equals(formattedDate)) {
                System.out.println("У вас сообщение!");
               try {
                    File soundFile = new File("snd.wav"); //Звуковой файл
                    //Получаем AudioInputStream
                    AudioInputStream ais = null;
                                try {
                                    ais = AudioSystem.getAudioInputStream(soundFile);
                                } catch (UnsupportedAudioFileException ex) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex);
                                }
                    //Получаем реализацию интерфейса Clip
                    Clip clip = null;
                                try {
                                    clip = AudioSystem.getClip();
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex);
                                }
                    //Загружаем наш звуковой поток в Clip
                    clip.open(ais);
                    clip.setFramePosition(0); //устанавливаем указатель на старт
                    clip.start(); //Поехали!!!
                    //Если не запущено других потоков, то стоит подождать, пока клип не закончится
                    //В GUI-приложениях следующие 3 строчки не понадобятся
                    Thread.sleep(clip.getMicrosecondLength()/1000);
                    clip.stop(); //Останавливаем
                    clip.close(); //Закрываем
                    } 
               catch(IOException | LineUnavailableException exc) { exc.printStackTrace();} 
               catch (InterruptedException exc) {}
            }
        }
    }
}
