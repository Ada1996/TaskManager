package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by daryo on 01.12.2016.
 */

public class ChangeForm extends JFrame {

    public ChangeForm(String nameForm, MainForm parentForm, Task cTask) throws IOException, ClassNotFoundException {

        super(nameForm);
        setLayout(null);
        String nameF = cTask.getName();

        //                     КОМПОНЕНТЫ ФОРМЫ                     //

        //КЛИЕНТ
        JLabel lclient = new JLabel("Пользователь:");
        JComboBox client = new JComboBox(TaskManager.getNamesFromFile());
        add(lclient);
        add(client);
        lclient.setBounds(0, 0, 100, 20);
        client.setBounds(100, 0, 194, 20);

        //ИМЯ ЗАДАНИЯ
        JLabel lname = new JLabel("Имя задания:");
        JTextField name = new JTextField(cTask.getName());
        add(lname);
        add(name);
        lname.setBounds(0, 25, 100, 20);
        name.setBounds(100, 25, 194, 20);

        //ОПИСАНИЕ
        JLabel ldescription = new JLabel("Описание:");
        JTextArea description = new JTextArea(cTask.getDescription());
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        add(ldescription);
        add(description);
        ldescription.setBounds(0, 50, 100, 20);
        description.setBounds(100, 50, 194, 120);

        //КОНТАКТЫ
        JLabel lcontacts = new JLabel("Контакты:");
        JTextField contacts = new JTextField(cTask.getContacts());
        add(lcontacts);
        add(contacts);
        lcontacts.setBounds(0, 175, 100, 20);
        contacts.setBounds(100, 175, 194, 20);

        //ДАТА
        JLabel ldate = new JLabel("Дата (mm/dd/yyyy hh:mm):");
        JTextField date = new JTextField(cTask.getDate());
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
                            String pathTask = MainForm.pathCatalog + "\\" + name.getText() + ".txt";

                            //формирование даты
                            String sb = new String(date.getText());
                            String[] strings = sb.split("[ /,.-[:]]");

                            strings[0] = (Integer.parseInt(strings[0]) - 1) + "";

                            GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(strings[2]), Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
                            //формирование нового таска
                            Task t = new Task((String) (client.getSelectedItem()), name.getText(), description.getText(), gc, contacts.getText());
                            String dateFormatted = t.getDate().replace('/', '-');
                            dateFormatted = dateFormatted.replace(':', '-');
                            //запись таска в исходный файл
                            String dateF = cTask.getDate().replace('/', '-');
                            dateF = dateF.replace(':', '-');

                            TaskManager.addTaskToFile(t, MainForm.pathCatalog + "\\" + nameF + dateF + ".txt");
                            TaskManager.renameFile(MainForm.pathCatalog + "\\" + nameF + dateF + ".txt", MainForm.pathCatalog + "\\" + name.getText() + dateFormatted + ".txt");

                            //вывод на экран обновленного списка задач
                            parentForm.outputTasks(MainForm.pathCatalog);

                            dispose();

                        } else
                            JOptionPane.showMessageDialog(null, "Имя задания не должно быть пустым и начинаться с пробела", "Ошибка", JOptionPane.ERROR_MESSAGE);

                } catch (IOException e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                } catch (DateTimeException e) {
                    JOptionPane.showMessageDialog(null, "Неправильно введена дата, повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "Неправильно введена дата, повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Неправильно введена дата, повторите ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
