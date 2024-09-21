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


    // name getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // dateTime getter and setter
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    /**
     * Compares an event starting time to another event starting time
     * @param e the event to be compared.
     * @return a negative integer, zero, or a positive integer
     * as this object is less than, equal to, or greater than
     * the specified object.
     */
    public int compareTo(Event e) {
        return dateTime.compareTo(e.dateTime);
    }
}
