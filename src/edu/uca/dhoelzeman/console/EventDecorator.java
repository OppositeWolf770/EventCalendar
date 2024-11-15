package edu.uca.dhoelzeman.console;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class EventDecorator extends Event {
    protected Event decoratedEvent;

    public EventDecorator(Event decoratedEvent) {
        super(decoratedEvent.getName(), decoratedEvent.getDateTime());
        this.decoratedEvent = decoratedEvent;
    }

    @Override
    public String getName() {
        return decoratedEvent.getName();
    }

    @Override
    public LocalDateTime getDateTime() {
        return decoratedEvent.getDateTime();
    }

    @Override
    public ArrayList<String> getDisplayStrings() {
        return decoratedEvent.getDisplayStrings();
    }
}
