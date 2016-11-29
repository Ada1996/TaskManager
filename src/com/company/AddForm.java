package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by daryo on 18.11.2016.
 */
public class AddForm extends JFrame {

    public AddForm(String s, MainForm parentForm) {
        super(s);
        setLayout(null);

        JLabel lname = new JLabel("Имя задания:");
        JTextField name = new JTextField("", 17);
        add(lname);
        add(name);
        lname.setBounds(0, 0, 100, 20);
        name.setBounds(100, 0, 194, 20);

        JLabel ldescription = new JLabel("Описание:");
        JTextField description = new JTextField("", 17);
        add(ldescription);
        add(description);
        ldescription.setBounds(0, 25, 100, 20);
        description.setBounds(100, 25, 194, 20);

        JLabel lcontacts = new JLabel("Контакты:");
        JTextField contacts = new JTextField("", 17);
        add(lcontacts);
        add(contacts);
        lcontacts.setBounds(0, 50, 100, 20);
        contacts.setBounds(100, 50, 194, 20);

        JLabel ldate = new JLabel("Дата:");
        JTextField day = new JTextField();
        JLabel lday = new JLabel("d");
        JTextField month = new JTextField();
        JLabel lmonth = new JLabel("m");
        JTextField year = new JTextField();
        JLabel lyear = new JLabel("y");

        JTextField hour = new JTextField();
        JLabel lhour = new JLabel("h");
        JTextField minute = new JTextField();
        JLabel lminute = new JLabel("m");


        add(ldate);

        add(lday);
        add(lmonth);
        add(lyear);
        add(lhour);
        add(lminute);

        add(day);
        add(month);
        add(year);
        add(minute);
        add(hour);

        ldate.setBounds(0, 75, 100, 20);

        day.setBounds(100, 75, 25, 20);
        lday.setBounds(125, 75, 11, 20);

        month.setBounds(136, 75, 25, 20);
        lmonth.setBounds(161, 75, 11, 20);

        year.setBounds(173, 75, 31, 20);
        lyear.setBounds(204, 75, 11, 20);

        hour.setBounds(220, 75, 25, 20);
        lhour.setBounds(245, 75, 11, 20);

        minute.setBounds(256, 75, 25, 20);
        lminute.setBounds(281, 75, 11, 20);


        JLabel systemMessage = new JLabel("<html>Здесь будут выводиться сообщения об ошибках</html>");
        systemMessage.setForeground(Color.red);
        add(systemMessage);
        systemMessage.setBounds(0, 100, 400, 20);

        JButton ok = new JButton("OK");
        add(ok);
        ok.setBounds(90, 135, 100, 20);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()), Integer.parseInt(minute.getText()));
                    Task t = new Task(name.getText(), description.getText(), gc, contacts.getText());
                    TaskManager tm = new TaskManager();
                    tm.addTaskToFile(t, name.getText()+".txt");
                    int k = 0;              
                    dispose();
                } catch (IOException e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        });


    }
}
