package com.company;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
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
 * Created by daryo on 02.03.2017.
 */
public class AddClientForm extends JFrame {
    private JScrollPane scroll;

    public AddClientForm() throws IOException, ClassNotFoundException {

        //ВНЕШНИЙ ВИД
        super("Работа с клиентами");
        setLayout(null);

        JTextField client = new JTextField("");
        JButton add = new JButton("Добавить");
        JComboBox clientBox = new JComboBox(TaskManager.getNamesFromFile());
        JButton delete = new JButton("Удалить");

        add(client);
        add(add);
        add(clientBox);
        add(delete);


        client.setBounds(25, 50, 125, 20);
        add.setBounds(152, 50, 125, 20);
        clientBox.setBounds(25, 90, 125, 20);
        delete.setBounds(152, 90, 125, 20);


        //ДОБАВИТЬ КЛИЕНТА
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                String name = client.getText();
                char[] nameCharTask = name.toCharArray();
                if (((!name.equals("")) && (!name.contains(" ")) && (!name.contains(",")) && (!name.contains("/")) && (!name.contains("\\")))) {
                    try {
                        if (!TaskManager.isAlignmentNames(name)) {
                            TaskManager.addNameToFile(name);
                            clientBox.addItem(name);
                        } else {
                            JOptionPane.showMessageDialog(null, "Данный клиент уже существует! Повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (IOException e) {
                    } catch (ClassNotFoundException e) {
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Символы , \\ / ПРОБЕЛ не допускаются! Повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);

                client.setText("");


            }
        });


        //УДАЛИТЬ КЛИЕНТА
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String name = (String) clientBox.getSelectedItem();

                try {
                    if (TaskManager.isAlignmentNames(name)) {
                        TaskManager.delNameFromFile(name);
                        clientBox.removeItem(clientBox.getSelectedItem());
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