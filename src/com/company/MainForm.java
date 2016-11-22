package com.company;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by daryo on 17.11.2016.
 */
public class MainForm extends JFrame {


    JLabel l1=new JLabel("");
    
    public MainForm(String s) {
        super(s);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.lightGray);

        JMenu newTask = new JMenu("Новое Задание");
        JMenu changeTask = new JMenu("Изменить задание");
        JMenu deleteTask = new JMenu("Удалить задание");

        menuBar.add(newTask);
        menuBar.add(changeTask);
        menuBar.add(deleteTask);

        setJMenuBar(menuBar);
        add(l1, BorderLayout.CENTER);
        
        Task e = new Task("First Task", "blabla", new GregorianCalendar(1996, 11, 15, 23, 11), "tetya");
        Task e1 = new Task("Second Task", "blabla", new GregorianCalendar(1996, 11, 15, 23, 7), "tetya");
        TaskManager journ = new TaskManager(); 
        journ.add(e);
        journ.add(e1);
   
        TaskTable tTable=new TaskTable();
        JTable textTable = new JTable(tTable);
        JScrollPane scroll = new JScrollPane(textTable);       
        scroll.setPreferredSize(new Dimension(400,400));
        add(scroll,BorderLayout.WEST);
        tTable.addTasks(journ);
        newTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm form1 = new AddForm("Заполните поля");
                form1.setVisible(true);
                form1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                form1.setSize(300, 200);
                //

            }
        });







    }
}
