package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Completable;
import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Event;
import edu.uca.dhoelzeman.console.Meeting;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

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

    // Contained in the displayPanel to hold the events that are displayed
    JPanel eventsPanel = new JPanel() {
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }
    };


    // The panel to hold the Events
    JPanel displayPanel = new JPanel() {
        public static final int fontSize = 24;
        public static final int preferredHeight = 400;

        {
            setPreferredSize(new Dimension(0, preferredHeight));

            // Use BorderLayout to separate the events from the events label
            setLayout(new BorderLayout());

            // Add the panel for the events label and places it to the north of the BorderLayout
            add(new JPanel() {
                {
                    // Adds the label for the Events (HTML used for underline)
                    add(new JLabel("<html><u>EVENTS</u></html>") {
                        {
                            // Gets the font and applies a larger font size for the label
                            Font font = getFont();
                            setFont(new Font(font.getName(), font.getStyle(), fontSize));
                        }
                    });
                }
            }, BorderLayout.NORTH);

            add(eventsPanel, BorderLayout.CENTER);
        }
    };

    // The checkboxes to filter the display
    JCheckBox showCompleted = new JCheckBox(filterCompleted);
    JCheckBox showMeetings = new JCheckBox(filterMeetings, true);
    JCheckBox showDeadlines = new JCheckBox(filterDeadlines, true);

    private static final int preferredWidth = 750;
    public static final int preferredHeight = 1000;

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
        private static final int maxHeight = 100;

        {
            setMaximumSize(new Dimension(Integer.MAX_VALUE, maxHeight));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));

            add(new JPanel() {
                {
                    add(addEventButton);
                    add(new JLabel("Sort By:"));
                    add(sortDropDown);
                }
            });

            // Holds the main controls
            add(new JPanel() {
                {
                    add(filterPanel);
                }
            });
        }
    };


    // Constructor for the EventListPanel adds the two panels
    EventListPanel() {
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));

        // Use BoxLayout for vertical alignment
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        add(controlPanel);
        add(displayPanel);

        // Add Event Button listener for click
        addEventButton.addActionListener(_ -> {
            new AddEventModal(this); // Create the Add Events prompt to add new events
            addOrUpdateEvents(); // Updates the displayPanel with the newly added Events (If any)
        });

        // Add Event Listeners for filter checkboxes (All call the addOrUpdateEvents method)
        addActionListeners(showCompleted, showMeetings, showDeadlines);

        // Action Listener for the sort box
        sortDropDown.addActionListener(e -> addOrUpdateEvents());

        // Initially displays the events on instance creation
        addOrUpdateEvents();
    }


    // Contains the logic to add the event listener to a check box
    private void addActionListeners(JCheckBox... components) {
        for (var component : components) {
            component.addActionListener(e -> addOrUpdateEvents());
        }
    }


    /*  This is the main function that is called from most Components in the
        EventListPanel. Adds Events to the display according to all selected filters
        and sort options. */
    void addOrUpdateEvents() {
        final int gapHeight = 10;

        eventsPanel.removeAll(); // Initially remove all events from the displayPanel

        // Use a stream to filter the events based on user choice, and collect it in an ArrayList of Events
        ArrayList<Event> filteredEvents = events.stream()
                .filter(event -> showCompleted.isSelected() || !((Completable) event).isComplete())
                .filter(event -> showMeetings.isSelected() || !(event instanceof Meeting))
                .filter(event -> showDeadlines.isSelected() || !(event instanceof Deadline))
                .collect(Collectors.toCollection(ArrayList::new));

        // Sort the filtered events according to selected sort option
        sortEvents(filteredEvents);

        // Loop through each event in the filteredEvents array and add it to the displayPanel
        for (Event event : filteredEvents) {
            eventsPanel.add(new EventPanel(event, this));
            eventsPanel.add(Box.createRigidArea(new Dimension(0, gapHeight))); // Adds padding between events
        }

        // Refresh the Frame with the updated events to display
        revalidate();
        repaint();
    }


    // Sorts the events as specified by the user's selection
    private void sortEvents(ArrayList<Event> eventsToSort) {
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
