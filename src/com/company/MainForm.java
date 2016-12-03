package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
    
    public TaskManager getJourn(){
        return journ;
    }
    public void buildTable(){
        textTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column = null;
        for (int i = 0; i < textTable.getColumnModel().getColumnCount(); i++) {
            column = textTable.getColumnModel().getColumn(i);
            String hv = column.getHeaderValue().toString();
            JTableHeader th = textTable.getTableHeader();
            FontMetrics fm = th.getFontMetrics(th.getFont());
            column.setPreferredWidth(fm.stringWidth(hv)+25);
        }          
    }
    public void outputTasks(String pathCatalog) throws IOException, ClassNotFoundException{
        journ=new TaskManager();
        File f = null;
        File[] paths;                 
            f = new File(pathCatalog);       
            paths = f.listFiles();        
            for(File path:paths)  {   
                String pathStr=path.toString();
                if (pathStr.lastIndexOf("txt")==(pathStr.length()-3)){
                   Task task = TaskManager.getTaskFromFile(pathStr);
                   journ.add(task);
                }
            }
            tTable.deleteTasks();
            tTable.addTasks(journ);
            textTable.updateUI();
         
      
   }
    public MainForm mainform=this;
    public MainForm(String s) throws IOException, ClassNotFoundException {       
        super(s);
        //ПОЛУЧЕНИЕ ПУТИ К КОРНЕВОЙ ПАПКЕ ПРОЕКТА, СОЗДАНИЕ ПАПКИ TASKS
        String pathRoot = System.getProperty("user.dir");
        pathCatalog = pathRoot+"\\Tasks";
        if(!new File(pathCatalog).exists()){
            catalog = new File(pathCatalog);
        }        
        
        journ=new TaskManager();
        tTable = new TaskTable();
        textTable = new JTable(tTable);
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
        l1.setPreferredSize(new Dimension(200, 500));
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
        scroll = new JScrollPane(textTable);
        scroll.setPreferredSize(new Dimension(400,400));
        add(scroll,BorderLayout.WEST);
        
        
        outputTasks(pathCatalog);
        buildTable();               
        //КНОПКА "НОВОЕ ЗАДАНИЕ"
        newTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm form1 = new AddForm("Заполните поля",mainform);
                form1.setVisible(true);
                form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                form1.setSize(410, 300);
                
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
