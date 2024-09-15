package edu.uca.dhoelzeman.console;

import java.util.Date;

public class Deadline extends Event implements Completable {
    private boolean complete;

    @Override
    public void complete() {

    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
