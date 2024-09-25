package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Completable;
import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Event;
import edu.uca.dhoelzeman.console.Meeting;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    ArrayList<Event> events = new ArrayList<>();
    // Set up the control panel
    JButton addEventButton = new JButton("Add Event");

    // The sortbox with the options to sort by
    JComboBox<String> sortDropDown = new JComboBox<>() {
        {
            addItem("Name");
            addItem("Reverse Name");
            addItem("Date");
            addItem("Reverse Date");
        }
    };
    JPanel displayPanel = new JPanel(); // The panel to hold the Events

    // The checkboxes to filter the display
    JCheckBox showCompleted = new JCheckBox("Show Completed");
    JCheckBox showMeetings = new JCheckBox("Show Meetings", true);
    JCheckBox showDeadlines = new JCheckBox("Show Deadlines", true);

    // Holds the filter checkboxes
    JPanel filterPanel = new JPanel() {
        {
            add(showCompleted);
            add(showMeetings);
            add(showDeadlines);
        }
    };

    // Holds the controls for the display of the events
    JPanel controlPanel = new JPanel() {
        {
            setPreferredSize(new Dimension(0, 100));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(new JPanel() {
                {
                    add(addEventButton);
                }
            });

            // Holds the main controls
            add(new JPanel() {
                {
                    add(new JLabel("Sort By:"));
                    add(sortDropDown);
                    add(filterPanel);
                }
            });
        }
    };


    // Constructor for the EventListPanel adds all the functionality
    EventListPanel() {
        setPreferredSize(new Dimension(750, 1000));

        // Use BoxLayout for vertical alignment
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(controlPanel);

        // Set up the displayPanel
        displayPanel.setPreferredSize(new Dimension(0, 400));

        add(displayPanel);

        // Initially displays the events on instance creation
        addOrUpdateEvents();

        // Add Event Button listener for click
        addEventButton.addActionListener(_ -> {
            new AddEventModal(this); // Create the Add Events prompt to add new events
            addOrUpdateEvents(); // Updates the displayPanel with the newly added Events (If any)
        });

        // Add Event Listeners for filter checkboxes (All call the addOrUpdateEvents method)
        showCompleted.addActionListener(e -> addOrUpdateEvents());
        showMeetings.addActionListener(e -> addOrUpdateEvents());
        showDeadlines.addActionListener(e -> addOrUpdateEvents());

        // Action Listener for the sort box
        sortDropDown.addActionListener(e -> addOrUpdateEvents());
    }


    /*  This is the main function that is called from most Components in the
        EventListPanel. Adds Events to the display according to all selected filters
        and sort options. */
    void addOrUpdateEvents() {
        displayPanel.removeAll(); // Initially remove all events from the displayPanel

        ArrayList<Event> filteredEvents = new ArrayList<>();

        // Loops through each event and checks if it is displayed based on criteria
        for (Event event : events) {
            boolean addEvent = true;

            // Logic for the showCompleted checkBox
            if (!showCompleted.isSelected() && event instanceof Completable && ((Completable) event).isComplete()) {
                addEvent = false;
            }

            // Logic for the showMeetings checkBox
            if (addEvent && !showMeetings.isSelected() && event instanceof Meeting) {
                addEvent = false;
            }

            // Logic for the showDeadlines checkBox
            if (addEvent && !showDeadlines.isSelected() && event instanceof Deadline) {
                addEvent = false;
            }

            // If the event needs to be displayed, add it to the filteredEvents array
            if (addEvent) {
                filteredEvents.add(event);
            }
        }

        // Sort the filtered events according to selected sort option
        sortEvents(filteredEvents);

        // Loop through each event in the filteredEvents array and add it to the displayPanel
        for (Event event : filteredEvents) {
            displayPanel.add(new EventPanel(event, this));
        }

        // Refresh the Frame with the updated events to display
        revalidate();
        repaint();
    }


    // Sorts the events as specified
    public void sortEvents(ArrayList<Event> eventsToSort) {
        String sortBy = (String) sortDropDown.getSelectedItem();

        // Logic for sorting by Name
        if ("Name".equals(sortBy)) {
            eventsToSort.sort(Comparator.comparing(Event::getName));

        // Logic for sorting by Name in reverse
        } else if ("Reverse Name".equals(sortBy)) {
            eventsToSort.sort(Comparator.comparing(Event::getName).reversed());
        }

        // Logic for sorting by Date
        else if ("Date".equals(sortBy)) {
            eventsToSort.sort(Comparator.comparing(Event::getDateTime));
        }

        // Logic for sorting by Date in reverse
        else if ("Reverse Date".equals(sortBy)) {
            eventsToSort.sort(Comparator.comparing(Event::getDateTime).reversed());
        }
    }
}
