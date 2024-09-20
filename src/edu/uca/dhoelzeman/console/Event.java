package edu.uca.dhoelzeman.console;


import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event> {
    String name;
    private LocalDateTime dateTime;

    // Constructor
    public Event(String name, LocalDateTime dateTime) {
        this.setName(name);
        this.setDateTime(dateTime);
    }


    public abstract String getName();

    public LocalDateTime getDateTime() {

        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Event e) {
        return 0;
    }
}
