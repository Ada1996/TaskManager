package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

       
       //Клиент
       JLabel lclient = new JLabel("Клиент:");
        JTextField client = new JTextField("");
        add(lclient);
        add(client);
        lclient.setBounds(0, 0, 100, 20);
        client.setBounds(100, 0, 194, 20);

        //ИМЯ ЗАДАНИЯ
        JLabel lname = new JLabel("Имя задания:");
        JTextField name = new JTextField("");
        add(lname);
        add(name);
        lname.setBounds(0, 25, 100, 20);
        name.setBounds(100, 25, 194, 20);

        //ОПИСАНИЕ
        JLabel ldescription = new JLabel("Описание:");
        JTextArea description = new JTextArea("");
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        add(ldescription);
        add(description);
        ldescription.setBounds(0, 50, 100, 20);
        description.setBounds(100, 50, 194, 120);

        //КОНТАКТЫ
        JLabel lcontacts = new JLabel("Контакты:");
        JTextField contacts = new JTextField("");
        add(lcontacts);
        add(contacts);
        lcontacts.setBounds(0, 175, 100, 20);
        contacts.setBounds(100, 175, 194, 20);

        //ДАТА
        JLabel ldate = new JLabel("Дата (mm-dd-yyyy hh:mm):");
        JTextField date = new JTextField();
        add(ldate);
        add(date);
        ldate.setBounds(0, 200, 200, 20);
        date.setBounds(152, 200, 142, 20);


        //КНОПКА ОК
        JButton ok = new JButton("OK");
        add(ok);
        ok.setBounds(90, 225, 100, 20);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {

                    String nameTask = name.getText();
                    char[] nameCharTask = nameTask.toCharArray();
                    if ((!name.getText().equals("")) && (nameCharTask[0] != ' ')) {

                        String sb = new String(date.getText());
                        String[] strings = sb.split("[ /,.-[:]]");
                        strings[0]=(Integer.parseInt(strings[0])-1)+"";

                        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(strings[2]), Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
                        Task t = new Task(client.getText(),name.getText(), description.getText(), gc, contacts.getText());
                        String pathTask = MainForm.pathCatalog + "\\" + name.getText() + ".txt";
                        if (TaskManager.equalsTasks(pathTask, MainForm.pathCatalog)) {
                            TaskManager.addTaskToFile(t, pathTask);
                            parentForm.outputTasks(MainForm.pathCatalog);
                            int k = 0;
                            dispose();
                        } else
                            JOptionPane.showMessageDialog(null, "Данная задача уже существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "Имя задания не должно быть пустым и начинаться с пробела", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DateTimeException e) {
                    JOptionPane.showMessageDialog(null, "Неправильно введена дата, повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);

                } catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "Неправильно введена дата, повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }
}
