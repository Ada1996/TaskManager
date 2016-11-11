package com.company;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class Task implements Serializable {

    private String nameOfTask;
    private String descriptionOfTask;
    private GregorianCalendar dateOfMessage;
    private String contacts;


    public String getName() {
        return nameOfTask;
    }

    public void setName(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }

    public String getDescriptionOfTask() {
        return descriptionOfTask;
    }

    public void setDescriptionOfTask(String descriptionOfTask) {
        this.descriptionOfTask = descriptionOfTask;
    }

    public GregorianCalendar getDateOfMessage() {
        return dateOfMessage;
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
        this.nameOfTask = name;
        this.contacts = contacts;
        this.descriptionOfTask = "";
        this.dateOfMessage = time;
    }

    public Task(String name, String description, GregorianCalendar time, String contacts) {
        this.nameOfTask = name;
        this.contacts = contacts;
        this.descriptionOfTask = description;
        this.dateOfMessage = time;
    }


}
