package edu.uca.dhoelzeman.console;

import java.util.Date;

public class Deadline extends Event implements Completable {
    private Date endDatetime;
    private String location;

    @Override
    public void complete() {

    }

    @Override
    public boolean isComplete() {
        return false;
    }

    public Date getEndTime() {

        return new Date();
    }

    public int getDuration() {

        return 0;
    }

    public String getLocation() {
        return this.location;
    }

    public void setEndTime(Date end) {

        this.endDatetime = end;
    }
}
