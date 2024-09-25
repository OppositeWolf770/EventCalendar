package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Meeting;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.stream.Location;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class AddEventModal extends JDialog {
    AddEventModal modal;

    public AddEventModal(EventListPanel eventListPanel) {
        modal = this;
        setModal(true);
        setSize(600, 350);
        setLocationRelativeTo(eventListPanel);

        setTitle("Add Event");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        var addEventPanel = new AddEventPanel(eventListPanel);
        add(addEventPanel);

        modal.setVisible(true);
    }


    private class AddEventPanel extends JPanel {
        private final CardLayout cardLayout;
        private final JPanel cardPanel;

        public AddEventPanel(EventListPanel eventListPanel) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            var eventOptionsPanel = new JPanel();
            var addEventButtonPanel = new JPanel();

            JButton addEventButton = new JButton("Submit");
            addEventButtonPanel.add(addEventButton);

            // The combo box to choose the type of meeting to add
            JComboBox<String> eventOptionsBox = new JComboBox<>();
            eventOptionsBox.addItem("Meeting");
            eventOptionsBox.addItem("Deadline");

            JLabel eventOptionsLabel = new JLabel("Event Type:");
            eventOptionsPanel.add(eventOptionsLabel);
            eventOptionsPanel.add(eventOptionsBox);

            // Create CardLayout panel
            cardLayout = new CardLayout();
            cardPanel = new JPanel(cardLayout);

            // Meeting panel
            MeetingCard meetingCard = new MeetingCard();

            // Deadline panel
            DeadlineCard deadlineCard = new DeadlineCard();

            // Add panels to CardLayout
            cardPanel.add(meetingCard, "Meeting");
            cardPanel.add(deadlineCard, "Deadline");

            // Adds all the pieces to the modal
            add(eventOptionsPanel);
            add(cardPanel);
            add(addEventButtonPanel);


            // Changes the options provided depending on the type of event selected
            eventOptionsBox.addActionListener(e -> {
                String selectedItem = (String) eventOptionsBox.getSelectedItem();
                cardLayout.show(cardPanel, selectedItem);
            });


            // Provides the logic for adding the event to the eventListPanel when submit is clicked
            addEventButton.addActionListener(e -> {
                String selectedItem = (String) eventOptionsBox.getSelectedItem();

                // Adds a Meeting to the eventListPanel
                if ("Meeting".equals(selectedItem)) {
                    // Converts the start time to a LocalDateTime
                    var start = convertToLocalDateTime(
                            meetingCard.startPanel.getDateString(),
                            meetingCard.startPanel.getTimeString()
                    );

                    // Converts the end time to a LocalDateTime
                    var end = convertToLocalDateTime(
                            meetingCard.endPanel.getDateString(),
                            meetingCard.endPanel.getTimeString()
                    );

                    // If either of the LocalDateTime objects failed to initialize, return to the Event Modal
                    if (start == null || end == null) {
                        return;
                    }

                    eventListPanel.events.add(
                            new Meeting(
                                    meetingCard.namePanel.getNameString(),
                                    start,
                                    end,
                                    meetingCard.locationPanel.getLocationString()
                            )
                    );

                } else if ("Deadline".equals(selectedItem)) {
                    var dateTime = convertToLocalDateTime(
                            deadlineCard.dueDateTime.getDateString(),
                            deadlineCard.dueDateTime.getTimeString()
                    );

                    if (dateTime == null) {
                        return;
                    }

                    eventListPanel.events.add(
                            new Deadline(
                                    deadlineCard.namePanel.getNameString(),
                                    dateTime
                            )
                    );
                }

                // Dispose the Modal and return to the eventListPanel
                modal.dispose();
            });
        }
    }


    // The panel to obtain LocalDateTime data from the user
    private static class LocalDateTimePanel extends JPanel {
        private final JTextField dateField;
        private final JTextField timeField;

        public LocalDateTimePanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            var datePanel = new JPanel();

            JLabel dateLabel = new JLabel("Enter date(yyyy-MM-dd):");
            dateField = new JTextField(10);

            datePanel.add(dateLabel);
            datePanel.add(dateField);

            var timePanel = new JPanel();

            JLabel timeLabel = new JLabel("Enter time(HH:mm):");
            timeField = new JTextField(5);

            timePanel.add(timeLabel);
            timePanel.add(timeField);

            add(datePanel);
            add(timePanel);
        }

        public String getDateString() {
            return dateField.getText();
        }

        public String getTimeString() {
            return timeField.getText();
        }
    }


    // The panel to obtain Location data from the user
    private static class LocationPanel extends JPanel {
        JTextField locationField;

        public LocationPanel() {
            JLabel locationLabel = new JLabel("Location:");
            locationField = new JTextField(10);

            add(locationLabel);
            add(locationField);
        }

        // Returns the contents of the locationField
        public String getLocationString() {
            return locationField.getText();
        }
    }


    // The panel to obtain Name data from the user
    private static class NamePanel extends JPanel {
        JTextField nameField;

        public NamePanel() {
            JLabel nameLabel = new JLabel("Name:");
            nameField = new JTextField(10);

            add(nameLabel);
            add(nameField);
        }

        // Returns the contents of the nameField
        public String getNameString() {
            return nameField.getText();
        }
    }


    // The panel to hold the contents of the Meeting Card when option is selected
    private static class MeetingCard extends JPanel {
        NamePanel namePanel = new NamePanel();
        LocalDateTimePanel startPanel = new LocalDateTimePanel();
        LocalDateTimePanel endPanel = new LocalDateTimePanel();
        LocationPanel locationPanel = new LocationPanel();

        MeetingCard() {
            // Sets the layout to BoxLayout for a Vertical alignment
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Creates a border for use in the Meeting Card
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

            add(namePanel);

            // Adds a label with a border for the starting time of the Meeting
            add(new JLabel("Starting Date/Time") {
                { setBorder(border); }
            });

            add(startPanel);

            // Adds a label with a border for the ending time of the Meeting
            add(new JLabel("Ending Date/Time") {
                { setBorder(border);}
            });

            add(endPanel);
            add(locationPanel);
        }
    }


    // The panel to hold the contents of the Deadline Card when option is selected
    private static class DeadlineCard extends JPanel {
        NamePanel namePanel = new NamePanel();
        LocalDateTimePanel dueDateTime = new LocalDateTimePanel();

        DeadlineCard() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(namePanel);
            add(dueDateTime);
        }
    }


    // Takes a given date and time (strings) and converts to a LocalDateTime object
    private LocalDateTime convertToLocalDateTime(String date, String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String dateTimeString = date + "T" + time;

        // Parses the LocalDateTime and returns an error window if unsuccessful
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid format for LocalDateTime.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
