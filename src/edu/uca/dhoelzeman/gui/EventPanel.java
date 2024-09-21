package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Completable;
import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Event;

import javax.swing.*;
import java.time.LocalDateTime;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event e) {
        event = e;

        if (event instanceof Completable) {
            completeButton = new JButton("Button1");

            add(completeButton);
        }

    }

    // Set background color according to the urgency
    public void updateUrgency() {

    }
}
