package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


/**
 * Created by daryo on 17.11.2016.
 */
public class MainForm extends JFrame {
    private String path;
    private TaskManager journ;
    private TaskTable tTable;
    private JTable textTable;
    private JScrollPane scroll;

    public TaskManager getJourn() {
        return journ;
    }

    public void buildTable() {
        textTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column = null;
        for (int i = 0; i < textTable.getColumnModel().getColumnCount(); i++) {
            column = textTable.getColumnModel().getColumn(i);
            String hv = column.getHeaderValue().toString();
            JTableHeader th = textTable.getTableHeader();
            FontMetrics fm = th.getFontMetrics(th.getFont());
            column.setPreferredWidth(fm.stringWidth(hv) + 25);
        }
    }

    public void writeTasks(String s) {
        journ = new TaskManager();
        File f = null;
        File[] paths;     //"C:\\Users\\Настя\\Documents\\NetBeansProjects\\TaskManager"
        try {
            f = new File(s);
            paths = f.listFiles();
            for (File path : paths) {
                String pathStr = path.toString();
                System.out.println("pathStr = " + pathStr);
                if (pathStr.lastIndexOf("txt") == (pathStr.length() - 3)) {
                    Task task = TaskManager.getTaskFromFile(pathStr);
                    journ.add(task);
                }
            }
            tTable.deleteTasks();
            tTable.addTasks(journ);
            textTable.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Неверный путь!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

  /*  public void removeTask() throws IOException, ClassNotFoundException
    {
        StringBuilder sb = new StringBuilder(FileForm.path);
        String fileName;
        sb.deleteCharAt(sb.length() - 1);
        for(int i=0;i<textTable.getSelectedRowCount();i++)
        {
            sb.append("\\" + textTable.getValueAt(textTable.getSelectedRows()[0], 1) + ".txt");
            fileName = sb.toString();
            fileName = fileName.replace("\\", "/");

            //File delFile = new File(fileName);

            File file = new File("1.txt");



            Task task = TaskManager.getTaskFromFile(fileName);
            System.out.println("name = " + task.getName());
            //delFile.delete();
        }
        writeTasks(FileForm.path);
    }*/


    public MainForm mainform = this;

    public MainForm(String s) throws IOException, ClassNotFoundException {
        super(s);
        journ = new TaskManager();
        tTable = new TaskTable();
        textTable = new JTable(tTable);
        setLayout(new BorderLayout());
        JLabel l1 = new JLabel("<html>Ты видел деву на скале<br>" +
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
        l1.setPreferredSize(new Dimension(100, 400));
        add(l1);

        //МЕНЮШКА
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.lightGray);

        JButton file = new JButton("Файл");
        JButton newTask = new JButton("Новое Задание");
        JButton changeTask = new JButton("   Изменить   ");
        JButton deleteTask = new JButton("   Удалить   ");
        JLabel whatsUp = new JLabel("Системные сообщения:");

        menuBar.add(file);
        menuBar.add(newTask);
        menuBar.add(changeTask);
        menuBar.add(deleteTask);
        menuBar.add(whatsUp);

        setJMenuBar(menuBar);
        scroll = new JScrollPane(textTable);
        scroll.setPreferredSize(new Dimension(400, 500));
        add(scroll, BorderLayout.WEST);


        //КНОПКА ФАЙЛ
        file.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileForm form2 = new FileForm("Укажите путь к папке", mainform);
                form2.setVisible(true);
                form2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                form2.setSize(430, 140);
                path = form2.getPath();
            }
        });
        //КНОПКА "НОВОЕ ЗАДАНИЕ"
        newTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm form1 = new AddForm("Заполните поля", mainform, path);
                form1.setVisible(true);
                form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                form1.setSize(310, 200);

            }
        });


        //КНОПКА "ИЗМЕНИТЬ ЗАДАНИЕ"
        changeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String date =(String)textTable.getValueAt(textTable.getSelectedRow(), 3);
                String[] strings = date.split("[ /,.-[:]]");

                GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(strings[2]),Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
                Task t = new Task((String)textTable.getValueAt(textTable.getSelectedRow(), 1), (String)textTable.getValueAt(textTable.getSelectedRow(), 2), gc, (String)textTable.getValueAt(textTable.getSelectedRow(), 4));


                ChangeForm form1 = new ChangeForm("Измените поля", mainform, path, t);
                form1.setVisible(true);
                form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                form1.setSize(310, 200);
            }
        });

        //КНОПКА "УДАЛИТЬ ЗАДАНИЕ"
        deleteTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

            }
        });

       /* try {
            removeTask();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
//D:\Labs\NetCracker\TaskM
    }
}
