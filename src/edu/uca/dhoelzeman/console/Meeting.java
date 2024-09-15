package edu.uca.dhoelzeman.console;

import java.util.Date;

public class Meeting extends Event implements Completable {
    private Date endDateTime;
    private String location;
    private boolean complete;

    public void complete() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    public Date getEndTime() {
        return endDateTime;
    }

    public int getDuration() {
        var duration = getEndTime().getTime() - getDateTime().getTime();
        int durationInMinutes = (int) (duration / (1000 * 60));

        return durationInMinutes;
    }

    public String getLocation() {
        return location;
    }

    public void setEndTime(Date end) {
        this.endDateTime = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
