package edu.uca.dhoelzeman.console;

import java.util.Date;

public abstract class Event implements Comparable<Event> {
    private String name;
    private Date dateTime;

    public String getName() {
        return this.name;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Event e) {
        return 0;
    }
}
