package edu.uca.dhoelzeman.console;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MeetingDecorator extends EventDecorator {
    private String location;
    private LocalDateTime endDateTime;
    private boolean complete;

    public MeetingDecorator(Event decoratedEvent, String location) {
        super(decoratedEvent);
        this.location = location;
    }

    public void complete() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Duration getDuration() {
        return Duration.between(getDateTime(), getEndDateTime());
    }

    @Override
    public ArrayList<String> getDisplayStrings() {
        ArrayList<String> displayStrings = super.getDisplayStrings();
        final int hoursToMin = 60;


        // Add the name and time of the Meeting
        displayStrings.add(getName() + spacer);
        displayStrings.add(getDateTime().format(formatter) + spacer); // Formats the start date

        // Calculate the duration of the meeting in hours and minutes
        long hours = getDuration().toHours();
        long minutes = getDuration().toMinutes();

        // Only display hours if it is an hour or longer
        if (hours > 0) {
            displayStrings.add(hours + " Hours");

            // Get the remaining minutes after the hour is added
            minutes -= (hours * hoursToMin);
        }

        // Display the minutes if necessary
        if (minutes > 0) {
            displayStrings.add(" " + minutes + " Minutes");
        }

        // Add the spacer after the Duration display
        displayStrings.add(spacer);

        // Add the location and completion status
        displayStrings.add(isComplete() ? "Completed" : "Incomplete" + spacer);

        return displayStrings;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
