package com.company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task implements Serializable {

    private String name;
    private String descriptionOfTask;
    private GregorianCalendar dateOfMessage;
    private String contacts;


    public String getName() {
        return name;
    }

    public void setName(String nameOfTask) {
        this.name = nameOfTask;
    }

    public String getDescriptionOfTask() {
        return descriptionOfTask;
    }

    public void setDescriptionOfTask(String descriptionOfTask) {
        this.descriptionOfTask = descriptionOfTask;
    }

    public String getDateOfMessage() {     
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Calendar calendar = this.dateOfMessage;      
        String formattedDate = df.format(calendar.getTime());
        return formattedDate;

    }

    public void setDateOfMessage(GregorianCalendar dateOfMessage) {
        this.dateOfMessage = dateOfMessage;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }


    public Task(String name, GregorianCalendar time, String contacts) {
        this.name = name;
        this.contacts = contacts;
        this.descriptionOfTask = "";
        this.dateOfMessage = time;
    }

    public Task(String name, String description, GregorianCalendar time, String contacts) {
        this.name = name;
        this.contacts = contacts;
        this.descriptionOfTask = description;
        this.dateOfMessage = time;
    }   
}
