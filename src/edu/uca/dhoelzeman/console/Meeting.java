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


    // Sets complete to true
    public void complete() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public Duration getDuration() {
        return Duration.between(getDateTime(), getEndDateTime());
    }

    public String getLocation() {
        return location;
    }

    public void setEndDateTime(LocalDateTime end) {
        endDateTime = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }
}
