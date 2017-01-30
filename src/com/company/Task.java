package com.company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task implements Serializable {

    private String name;
    private String description;
    private GregorianCalendar date;
    private String contacts;


    public String getName() {
        return name;
    }

    public void setName(String nameOfTask) {
        this.name = nameOfTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Calendar calendar = this.date;
        String formattedDate = df.format(calendar.getTime());
        return formattedDate;

    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
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
        this.description = "";
        this.date = time;
    }

    public Task(String name, String description, GregorianCalendar time, String contacts) {
        this.name = name;
        this.contacts = contacts;
        this.description = description;
        this.date = time;
    }   
}
