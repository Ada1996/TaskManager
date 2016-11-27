package com.company;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
/**
 * Created by daryo on 17.11.2016.
 */
public class MainForm extends JFrame {


   // JLabel l1=new JLabel("ABC");
    public MainForm(String s) {
        super(s);
        setLayout(new BorderLayout());
        JLabel l1=new JLabel("<html>Ты видел деву на скале<br>" +
                "В одежде белой над волнами<br>" +
                "Когда, бушуя в бурной мгле,<br>" +
                "Играло море с берегами,<br>" +
                "Когда луч молний озарял<br>" +
                "Ее всечасно блеском алым<br>" +
                "И ветер бился и летал<br>" +
                "С ее летучим покрывалом?<br>" +
                "Прекрасно море в бурной мгле<br>" +
                "И небо в блесках без лазури;<br>" +
                "Но верь мне: дева на скале<br>" +
                "Прекрасней волн, небес и бури.</html>");
        l1.setVerticalAlignment(JLabel.TOP);
        l1.setPreferredSize(new Dimension(200, 400));
        add(l1);

        //МЕНЮШКА
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.lightGray);


        JButton newTask = new JButton("Новое Задание");
        JButton changeTask = new JButton("Изменить задание");
        JButton deleteTask = new JButton("Удалить задание");
        JLabel whatsUp = new JLabel("Системные сообщения:");

        menuBar.add(newTask);
        menuBar.add(changeTask);
        menuBar.add(deleteTask);
        menuBar.add(whatsUp);

        setJMenuBar(menuBar);


        //СОЗДАНИЕ СПИСКА ЗАДАНИЙ
        Task e = new Task("First Task", "blabla", new GregorianCalendar(1996, 11, 15, 23, 11), "tetya");
        Task e1 = new Task("Second Task", "blabla", new GregorianCalendar(1996, 11, 15, 23, 7), "tetya");
        TaskManager journ = new TaskManager(); 
        journ.add(e);
        journ.add(e1);


        //РАБОТА С ТАБЛИЦЕЙ
        TaskTable tTable=new TaskTable();
        JTable textTable = new JTable(tTable);
        JScrollPane scroll = new JScrollPane(textTable);       
        scroll.setPreferredSize(new Dimension(400,400));
        add(scroll,BorderLayout.WEST);
      
      textTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column = null;
        for (int i = 0; i < textTable.getColumnModel().getColumnCount(); i++) {
            column = textTable.getColumnModel().getColumn(i);
            String hv = column.getHeaderValue().toString();
            JTableHeader th = textTable.getTableHeader();
            FontMetrics fm = th.getFontMetrics(th.getFont());
            column.setPreferredWidth(fm.stringWidth(hv)+25);
        }     
        tTable.addTasks(journ);
       
        //КНОПКА "НОВОЕ ЗАДАНИЕ"
        newTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm form1 = new AddForm("Заполните поля");
                form1.setVisible(true);
                form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                form1.setSize(310, 200);

            }
        });


        //КНОПКА "ИЗМЕНИТЬ ЗАДАНИЕ"
        changeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

            }
        });







        }
}
