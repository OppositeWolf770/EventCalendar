package edu.uca.dhoelzeman.console;

import java.time.Duration;
import java.time.LocalDateTime;

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

}
