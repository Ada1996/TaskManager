/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Настя
 */
public class TaskTable extends AbstractTableModel{
    private int column=5;
    private ArrayList<Object []> data;
   // Object[] columnNames = {"Название", "Описание", "Дата", "Контакты", "Выбрать"};
    DefaultTableModel model = new DefaultTableModel();
    public TaskTable() {
        data = new ArrayList<Object []>();
        for (int i=0; i<data.size(); i++)
            data.add(new String[getColumnCount()]);
        //model.
    }
    @Override
    public int getRowCount() {
        return data.size(); 
    }

    @Override
    public int getColumnCount() {
        return column;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object []rows = data.get(rowIndex);         
        return rows[columnIndex];
    }
    
   /*@Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Название";
            case 1: return "Описание";
            case 2: return "Дата";
            case 3: return "Контакты";
        }
        return "";
    }*/
    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "";
            case 1: return "Название";
            case 2: return "Описание";
            case 3: return "Дата                 ";
            case 4: return "Контакты";
        }
        return "";
    }
    
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex){
            case 0: return Boolean.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            default: return String.class;
        }     
    }
    public void addData(Object[] row){
        Object []rowTable = new String[getColumnCount()];
        rowTable=row;
        data.add(rowTable);
    }
    public void addTasks(TaskManager task){
        List<Task> tasks = task.getTasks();
        for(Task x : tasks){
        Object []row = {
             false,
             x.getName(),
             x.getDescriptionOfTask(),
             x.getDateOfMessage(),
             x.getContacts(),
      };
         addData(row);
    }
    }
}
