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
 * Created by daryo on 02.03.2017.
 */
public class AddClientForm extends JFrame {
    private JScrollPane scroll;

    public AddClientForm() {
        super("Работа с клиентами");
        setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.lightGray);

        JTextField client = new JTextField("");
        JButton add = new JButton("Добавить клиента");
        JButton delete = new JButton("Удалить клиента");


        //add(client);
        //client.setBounds(50, 50, 200, 20);
        //add(ok);
        //ok.setBounds(50, 120, 200, 20);

        menuBar.add(client);
        menuBar.add(add);
        menuBar.add(delete);

        setJMenuBar(menuBar);


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String name = client.getText();
                try {

                    if (!TaskManager.isAlignmentNames(name)) {
                        TaskManager.addNameToFile(name);
                    } else {
                        JOptionPane.showMessageDialog(null, "Данный клиент уже существует! Повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException e) {
                } catch (ClassNotFoundException e) {
                }
                client.setText("");

            }
        });


        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String name = client.getText();

                try {
                    if (TaskManager.isAlignmentNames(name)) {
                        TaskManager.delNameFromFile(name);
                    } else {
                        JOptionPane.showMessageDialog(null, "Данного клиента не существует!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                client.setText("");
            }
        });


    }
}