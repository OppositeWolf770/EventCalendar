package edu.uca.dhoelzeman.console;

import java.time.Duration;
import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    private LocalDateTime endDatetime;
    private boolean complete;
    private String location;

    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
    }


    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    public LocalDateTime getEndTime() {

        return endDatetime;
    }

    public Duration getDuration() {

        return null;
    }

    public String getLocation() {
        return this.location;
    }

    public void setEndTime(LocalDateTime end) {

        this.endDatetime = end;
    }
}
