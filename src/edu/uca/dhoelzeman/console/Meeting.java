package edu.uca.dhoelzeman.console;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;


    // Constructor sets the private data fields appropriately
    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        setEndDateTime(end);
        setLocation(location);
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
        displayStrings.add(getLocation() + spacer);
        displayStrings.add(isComplete() ? "Completed" : "Incomplete" + spacer);

        return displayStrings;
    }
}
