package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


/**
 * Created by daryo on 17.11.2016.
 */
public class MainForm extends JFrame {

    public static String pathCatalog;
    File catalog;
    private TaskManager journ;
    private TaskTable tTable;
    private JTable textTable;
    private JScrollPane scroll;
    public MainForm mainForm = this;

    public TaskManager getJourn() {
        return journ;
    }

    //РАБОТА С ТАБЛИЦЕЙ
    public void buildTable() {
        textTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column = null;
        int prefWidth = 0;
        JTableHeader th = textTable.getTableHeader();
        for (int i = 0; i < textTable.getColumnCount(); i++) {
            column = textTable.getColumnModel().getColumn(i);
            int prefWidthMax = 0;
            for (int j = 0; j < textTable.getRowCount(); j++) {
                String s = textTable.getModel().getValueAt(j, i).toString();
                prefWidth =
                        Math.round(
                                (float) th.getFontMetrics(
                                        th.getFont()).getStringBounds(s,
                                        th.getGraphics()
                                ).getWidth()
                        );
                if (prefWidth > prefWidthMax) prefWidthMax = prefWidth;
            }
            column.setPreferredWidth(prefWidthMax + 68);
        }
    }

    //ВЫВОД ВСЕХ ЗАДАЧ НА ЭКРАН
    public void outputTasks(String pathCatalog) throws IOException, ClassNotFoundException {
        journ = new TaskManager();
        File f = null;
        File[] paths;
        f = new File(pathCatalog);
        paths = f.listFiles();
        tTable.deleteTasks();
        textTable.updateUI();
        for (File path : paths) {
            String pathStr = path.toString();
            if (pathStr.lastIndexOf("txt") == (pathStr.length() - 3)) {
                Task task = TaskManager.getTaskFromFile(pathStr);
                journ.add(task);
            }
            tTable.deleteTasks();
            tTable.addTasks(journ);
            textTable.updateUI();
        }
    }


    public MainForm(String s) throws IOException, ClassNotFoundException {
        super(s);


        //ПОЛУЧЕНИЕ ПУТИ К КОРНЕВОЙ ПАПКЕ ПРОЕКТА, СОЗДАНИЕ ПАПКИ TASKS
        String pathRoot = System.getProperty("user.dir");
        pathCatalog = pathRoot + "\\Tasks";
        if (!new File(pathCatalog).exists()) {
            catalog = new File(pathCatalog);
        }
        journ = new TaskManager();
        tTable = new TaskTable();
        textTable = new JTable(tTable);

        //setLayout(null);
        setLayout(new BorderLayout());


        //МЕНЮШКА
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.lightGray);

        JButton newTask = new JButton("Новое Задание");
        JButton changeTask = new JButton("Изменить задание");
        JButton deleteTask = new JButton("Удалить задание");
        JButton addClient = new JButton("Работа с пользователями");


        menuBar.add(newTask);
        menuBar.add(changeTask);
        menuBar.add(deleteTask);
        menuBar.add(addClient);

        setJMenuBar(menuBar);
        scroll = new JScrollPane(textTable);
        scroll.setPreferredSize(new Dimension(650, 400));
        add(scroll, BorderLayout.WEST);


        outputTasks(pathCatalog);
        buildTable();


        //КНОПКА "НОВОЕ ЗАДАНИЕ"
        newTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AddForm form1 = new AddForm("Заполните поля", mainForm);
                    form1.setVisible(true);
                    form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    form1.setSize(320, 287);
                    form1.setLocationRelativeTo(null);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        });
        //КНОПКА "УДАЛИТЬ ЗАДАНИЕ"
        deleteTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int[] selectedRows = textTable.getSelectedRows();

                    if (selectedRows.length == 0)
                        JOptionPane.showMessageDialog(null, "      Выберите файл!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    else {
                        for (int i = 0; i < selectedRows.length; i++) {
                            StringBuilder sb = new StringBuilder(pathCatalog);
                            int selIndex = selectedRows[i];

                            String dateF = ((String) textTable.getValueAt(selIndex, 3)).replace('/', '-');
                            dateF = dateF.replace(':', '-');


                            sb.append("\\" + textTable.getValueAt(selIndex, 1) + dateF + ".txt");
                            String fileName = sb.toString();
                            File delFile = new File(fileName);
                            System.gc();
                            delFile.delete();

                        }

                        textTable.getSelectionModel().clearSelection();//убирает выделение строк
                        outputTasks(pathCatalog);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        //КНОПКА "ИЗМЕНИТЬ ЗАДАНИЕ"
        changeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int[] selectedRows = textTable.getSelectedRows();
                if (selectedRows.length == 0)
                    JOptionPane.showMessageDialog(null, "      Выберите файл!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                else if (selectedRows.length > 1) {
                    JOptionPane.showMessageDialog(null, "Выберите только один файл!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    String date = (String) textTable.getValueAt(textTable.getSelectedRow(), 3);
                    String[] strings = date.split("[ /,.-[:]]");

                    strings[0] = (Integer.parseInt(strings[0]) - 1) + "";

                    GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(strings[2]), Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));

                    StringBuffer str = new StringBuffer();
                    SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                    f.setCalendar(gc);
                    String dateFormatted = f.format(gc.getTime());
                    str.append(dateFormatted);


                    Task t = new Task((String) textTable.getValueAt(textTable.getSelectedRow(), 0), (String) textTable.getValueAt(textTable.getSelectedRow(), 1), (String) textTable.getValueAt(textTable.getSelectedRow(), 2), gc, (String) textTable.getValueAt(textTable.getSelectedRow(), 4));
                    try {
                        ChangeForm form1 = new ChangeForm("Измените поля", mainForm, t);
                        form1.setVisible(true);
                        form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        form1.setSize(320, 287);
                        form1.setLocationRelativeTo(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        //КНОПКА "РАБОТА С ПОЛЬЗОВАТЕЛЯМИ"
        addClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClientForm form1 = null;
                try {
                    form1 = new AddClientForm();
                    form1.setVisible(true);
                    form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    form1.setSize(320, 210);
                    form1.setLocationRelativeTo(null);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
