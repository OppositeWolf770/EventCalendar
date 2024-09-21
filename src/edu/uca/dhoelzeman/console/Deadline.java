package edu.uca.dhoelzeman.console;

import java.time.LocalDateTime;

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
}
