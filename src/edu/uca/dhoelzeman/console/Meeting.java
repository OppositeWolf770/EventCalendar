package edu.uca.dhoelzeman.console;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Meeting extends Event {
    // Constructor sets the private data fields appropriately
    public Meeting(String name, LocalDateTime start, LocalDateTime end) {
        super(name, start);
    }

    @Override
    public ArrayList<String> getDisplayStrings() {
        return null;
    }
}
