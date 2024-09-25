package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Completable;
import edu.uca.dhoelzeman.console.Event;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventPanel extends JPanel {
    private final Event event;
    private JButton completeButton;

    // Constants
    public static final int bgColor = 0xD9D9D9; // Gray background
    public static final int maxWidth = 900;
    public static final int maxHeight = 50;

    public EventPanel(Event e, EventListPanel eventListPanel) {
        event = e;

        // Sets the background color and size
        setBackground(new Color(bgColor));
        setMaximumSize(new Dimension(maxWidth, maxHeight));

        // Holds the displayStrings for the event
        ArrayList<String> displayStrings = event.getDisplayStrings();

        // Loops through each displayString and adds it to the EventPanel
        for (String displayString : displayStrings) {
            add(new JLabel(displayString));
        }

        // If the event is completable, add the complete button
        if (event instanceof Completable) {
            // Only add the button if the event is not complete
            if (!((Completable) event).isComplete()) {
                completeButton = new JButton("Mark Complete");
                add(completeButton);

                // On click, call the update method in eventListPanel
                completeButton.addActionListener(_ -> {
                    ((Completable) event).complete();
                    eventListPanel.addOrUpdateEvents();
                });
            }
        }
    }
}
