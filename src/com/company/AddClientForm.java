package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

/**
 * Created by daryo on 02.03.2017.
 */
public class AddClientForm extends JFrame {
    private JScrollPane scroll;

    public AddClientForm() throws IOException, ClassNotFoundException {

        //ВНЕШНИЙ ВИД
        super("Работа с пользователями");
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
                    if (name.length() <= 20) {
                        try {
                            if (!TaskManager.isAlignmentNames(name)) {
                                TaskManager.addNameToFile(name);
                                clientBox.addItem(name);
                                clientBox.setSelectedItem(name);
                            } else {
                                JOptionPane.showMessageDialog(null, "Данный пользователь уже существует! Повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (IOException e) {
                        } catch (ClassNotFoundException e) {
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Длина имени должна быть не более 20 символов! Повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);

                } else
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


                        java.util.List<Task> tasks = TaskManager.getTasksFromFiles(MainForm.pathCatalog);
                        java.util.List<Task> tasksClient = new ArrayList<>();

                        // boolean b = (tasksClient==null);//ложь, почемуууу

                        for (Task x : tasks) {
                            if (x.getClient().equals(name)) {
                                tasksClient.add(x);
                                System.out.println(x.getName());
                            }
                        }

                        if (tasksClient.isEmpty()) {
                            TaskManager.delNameFromFile(name);
                            clientBox.removeItem(clientBox.getSelectedItem());
                        } else {
                            JOptionPane.showMessageDialog(null, "Вы не можете удалить данного пользователя, так как у него остались задания.\nЛибо удалите их, либо передайте другим пользователям.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Данного пользователя не существует!", "Ошибка", JOptionPane.ERROR_MESSAGE);
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