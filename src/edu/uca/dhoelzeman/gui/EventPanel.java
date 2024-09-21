package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Completable;
import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Event;

import javax.swing.*;
import java.time.LocalDateTime;

public class EventPanel extends JPanel {
    private final Event event;
    private JButton completeButton;

    public EventPanel(Event e) {
        event = e;

        if (event instanceof Completable) {
            completeButton = new JButton("Mark Complete");

            add(completeButton);
        }

    }

    // Set background color according to the urgency
    public void updateUrgency() {

    }
}
