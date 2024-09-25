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

    // Constants for the sort options
    private static final String sortName = "Name";
    private static final String sortReverseName = "Reverse Name";
    private static final String sortDate = "Date";
    private static final String sortReverseDate = "Reverse Date";

    // Constants for the filter options
    private static final String filterCompleted = "Show Completed";
    private static final String filterMeetings = "Show Meetings";
    private static final String filterDeadlines = "Show Deadlines";

    // Button to add an event to the display
    JButton addEventButton = new JButton("Add Event");

    // The sortbox with the options to sort by
    JComboBox<String> sortDropDown = new JComboBox<>() {
        {
            addItem(sortName);
            addItem(sortReverseName);
            addItem(sortDate);
            addItem(sortReverseDate);
        }
    };

    // The panel to hold the Events
    JPanel displayPanel = new JPanel() {
        {
//            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setLayout(new GridLayout(0, 1));
        }
    };

    // The checkboxes to filter the display
    JCheckBox showCompleted = new JCheckBox(filterCompleted);
    JCheckBox showMeetings = new JCheckBox(filterMeetings, true);
    JCheckBox showDeadlines = new JCheckBox(filterDeadlines, true);

    // Holds the filter checkboxes
    JPanel filterPanel = new JPanel() {
        {
            add(showCompleted);
            add(showMeetings);
            add(showDeadlines);
        }
    };

    // Control panel to hold the display controls
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
        if (sortName.equals(sortBy)) {
            eventsToSort.sort(Comparator.comparing(Event::getName));

        // Logic for sorting by Name in reverse
        } else if (sortReverseName.equals(sortBy)) {
            eventsToSort.sort(Comparator.comparing(Event::getName).reversed());
        }

        // Logic for sorting by Date
        else if (sortDate.equals(sortBy)) {
            eventsToSort.sort(Comparator.comparing(Event::getDateTime));
        }

        // Logic for sorting by Date in reverse
        else if (sortReverseDate.equals(sortBy)) {
            eventsToSort.sort(Comparator.comparing(Event::getDateTime).reversed());
        }
    }
}
