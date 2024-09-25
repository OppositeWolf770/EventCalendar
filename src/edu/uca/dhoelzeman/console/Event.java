package edu.uca.dhoelzeman.console;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Event implements Comparable<Event> {
    String name;
    private LocalDateTime dateTime;

    // The formatter used to display LocalDateTime to the user
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

    // Spaces out the data in the display
    protected static final String spacer = "      ";


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


    // Compares an event starting time to another event starting time
    public int compareTo(Event e) {
        return dateTime.compareTo(e.dateTime);
    }


    // Returns an array of strings to be used when displaying the Events
    public abstract ArrayList<String> getDisplayStrings();
}
