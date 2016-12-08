package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by daryo on 18.11.2016.
 */
public class AddForm extends JFrame {
   
    public AddForm(String nameForm, MainForm parentForm) {
        
        super(nameForm);
        setLayout(null);
        //                     КОМПОНЕНТЫ ФОРМЫ                     //


        //ИМЯ ЗАДАНИЯ
        JLabel lname = new JLabel("Имя задания:");
        JTextField name = new JTextField("");
        add(lname);
        add(name);
        lname.setBounds(0, 0, 100, 20);
        name.setBounds(100, 0, 194, 20);

        //ОПИСАНИЕ
        JLabel ldescription = new JLabel("Описание:");
        JTextField description = new JTextField("");
        add(ldescription);
        add(description);
        ldescription.setBounds(0, 25, 100, 20);
        description.setBounds(100, 25, 194, 20);

        //КОНТАКТЫ
        JLabel lcontacts = new JLabel("Контакты:");
        JTextField contacts = new JTextField("");
        add(lcontacts);
        add(contacts);
        lcontacts.setBounds(0, 50, 100, 20);
        contacts.setBounds(100, 50, 194, 20);

        //ДАТА
        JLabel ldate = new JLabel("Дата (ggg-mm-dd hh-mm):");
        JTextField date = new JTextField();
        add(ldate);
        add(date);
        ldate.setBounds(0, 75, 200, 20);
        date.setBounds(152, 75, 142, 20);


        //СООБЩЕНИЯ ОБ ОШИБКАХ
        JLabel systemMessage = new JLabel("<html>Здесь будут выводиться сообщения об ошибках</html>");
        systemMessage.setForeground(Color.red);
        add(systemMessage);
        systemMessage.setBounds(0, 100, 400, 20);

        //КНОПКА ОК
        JButton ok = new JButton("OK");
        add(ok);
        ok.setBounds(90, 135, 100, 20);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    String sb = new String(date.getText());
                    String[] strings = sb.split("[ ,.-]");

                    GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));

                    Task t = new Task(name.getText(), description.getText(), gc, contacts.getText());

                    TaskManager tm = new TaskManager();
                    tm.addTaskToFile(t,MainForm.pathCatalog+"\\"+name.getText()+".txt");                               
                    parentForm.outputTasks(MainForm.pathCatalog);                 
                    int k = 0;              
                    dispose();
                } catch (IOException e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (DateTimeException e) {
                    systemMessage.setText("<html>Неправильно введена дата, повторите ввод</html>");
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    systemMessage.setText("<html>Неправильно введена дата, повторите ввод</html>");
                }
            }
        });


    }
}
