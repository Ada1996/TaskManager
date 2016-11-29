/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Настя
 */
public class FileForm extends JFrame {
    public static String path;
    public FileForm(String s, MainForm parentForm) {
        super(s);
        setLayout(null);
        JLabel fileName = new JLabel("Путь:");
        JTextField pathField = new JTextField("", 20);
        add(fileName);
        add(pathField);
        fileName.setBounds(5, 5, 100, 20);
        pathField.setBounds(50, 5, 350, 20);
        JButton ok = new JButton("OK");
        add(ok);
        ok.setBounds(150, 50, 80, 30);        
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                path = pathField.getText();
                if (path!=null && path!=(""))
                {
                    parentForm.writeTasks(path);
                    parentForm.buildTable();
                }
                dispose();
            }
        });
    }
    public String getPath(){
        return path;
    }  
}
