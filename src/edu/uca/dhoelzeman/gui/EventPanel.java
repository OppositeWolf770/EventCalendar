package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Completable;
import edu.uca.dhoelzeman.console.Event;

import javax.swing.*;
import java.util.ArrayList;

public class EventPanel extends JPanel {
    private final Event event;
    private JButton completeButton;

    public EventPanel(Event e, EventListPanel eventListPanel) {
        event = e;

        ArrayList<String> displayStrings = event.getDisplayStrings();

        for (String displayString : displayStrings) {
            add(new JLabel(displayString));
        }


        if (event instanceof Completable) {
            completeButton = new JButton("Mark Complete");

            add(completeButton);

            completeButton.addActionListener(_ -> {
                ((Completable) event).complete();
                this.remove(completeButton);
                completeButton.setVisible(false);
                eventListPanel.addOrUpdateEvents();
            });
        }

    }
}
