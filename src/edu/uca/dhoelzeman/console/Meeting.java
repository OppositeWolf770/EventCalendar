package edu.uca.dhoelzeman.console;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;


    // Constructor
    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        this.setEndDateTime(end);
        this.setLocation(location);
    }


    // Marks the meeting as completed
    public void complete() {
        complete = true;
    }


    // Returns the status of the meeting (Complete/Incomplete)
    public boolean isComplete() {
        return complete;
    }


    // endDateTime getter and setter
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime end) {
        endDateTime = end;
    }


    // Returns the duration from the start and end time
    public Duration getDuration() {
        return Duration.between(getDateTime(), getEndDateTime());
    }


    // location getter and setter
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    // Returns an array of strings describing the Meeting
    public ArrayList<String> getDisplayStrings() {
        ArrayList<String> displayStrings = new ArrayList<>();

        displayStrings.add(getName());
        displayStrings.add(getDateTime().format(formatter));
        displayStrings.add(getDuration().toHours() + " Hours");
        displayStrings.add(getLocation());
        displayStrings.add(isComplete() ? "Completed" : "Incomplete");

        return displayStrings;
    }
}
