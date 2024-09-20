package edu.uca.dhoelzeman.console;

import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    private boolean complete; // The state of the Deadline


    // Constructor
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
    }


    // Getter for the name of the Deadline
    @Override
    public String getName() {
        return name;
    }


    // Sets complete to true
    @Override
    public void complete() {
        complete = true;
    }


    // Returns the complete boolean indicating whether the Deadline is complete
    @Override
    public boolean isComplete() {
        return complete;
    }
}
