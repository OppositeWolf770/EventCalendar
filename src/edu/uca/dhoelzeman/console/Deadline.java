package edu.uca.dhoelzeman.console;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Deadline extends Event implements Completable {
    private boolean complete; // The state of the Deadline


    // Constructor
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
    }



    // Sets complete to true
    @Override
    public void complete() {
        complete = true;
    }


    // Returns the status of the deadline (Complete/Incomplete)
    @Override
    public boolean isComplete() {
        return complete;
    }


    // Returns an array of strings describing the Deadline
    @Override
    public ArrayList<String> getDisplayStrings() {
        ArrayList<String> displayStrings = new ArrayList<>();

        displayStrings.add(getName());
        displayStrings.add(getDateTime().format(formatter)); // Formats the due date
        displayStrings.add(isComplete() ? "Completed" : "Incomplete");


        return displayStrings;
    }
}
