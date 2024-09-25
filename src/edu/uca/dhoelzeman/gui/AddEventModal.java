package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Meeting;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventModal extends JDialog {
    private final AddEventModal modal;

    // Constructor initializes the modal
    public AddEventModal(EventListPanel eventListPanel) {
        modal = this;
        setModal(true);
        setSize(600, 350);
        setLocationRelativeTo(eventListPanel);

        // Sets the title and close operation
        setTitle("Add Event");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Adds the panel to show the components of the modal
        var addEventPanel = new AddEventPanel(eventListPanel);
        add(addEventPanel);

        modal.setVisible(true);
    }


    // The panel to hold the contents of the modal window
    private class AddEventPanel extends JPanel {
        // Constants for the eventOptionsBox
        private static final String meetingOption = "Meeting";
        private static final String deadlineOption = "Deadline";

        // The combo box to choose the type of meeting to add
        JComboBox<String> eventOptionsBox = new JComboBox<>() {
            {
                addItem(meetingOption);
                addItem(deadlineOption);
            }
        };

        // Panel to hold the eventOptionsBox
        JPanel eventOptionsPanel = new JPanel() {
            {
                new Label("Event Type:");
                add(eventOptionsBox);
            }
        };

        // Panel to hold the addEventButton to add an event to the display
        JButton addEventButton = new JButton("Submit");
        JPanel addEventButtonPanel = new JPanel() {
            {
                add(addEventButton);
            }
        };

        // Meeting panel
        MeetingCard meetingCard = new MeetingCard();

        // Deadline panel
        DeadlineCard deadlineCard = new DeadlineCard();

        // Create CardLayout panel
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout) {
            {
                // Add the event panels to the cardPanel
                add(meetingCard, meetingOption);
                add(deadlineCard, deadlineOption);
            }
        };

        // Constructor initializes
        public AddEventPanel(EventListPanel eventListPanel) {
            // Use BoxLayout on the Y_AXIS for a vertical layout
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
                if (meetingOption.equals(selectedItem)) {
                    // Converts the start time to a LocalDateTime
                    LocalDateTime start = convertToLocalDateTime(
                            meetingCard.startPanel.getDateString(),
                            meetingCard.startPanel.getTimeString()
                    );

                    // Converts the end time to a LocalDateTime
                    LocalDateTime end = convertToLocalDateTime(
                            meetingCard.endPanel.getDateString(),
                            meetingCard.endPanel.getTimeString()
                    );

                    // If either of the LocalDateTime objects failed to initialize, return to the Event Modal
                    if (start == null || end == null) {
                        return;
                    }

                    // Add the Meeting to the eventListPanel
                    eventListPanel.events.add(
                            new Meeting(
                                    meetingCard.namePanel.getNameString(),
                                    start,
                                    end,
                                    meetingCard.locationPanel.getLocationString()
                            )
                    );

                // Add a Deadline to the eventListPanel
                } else if (deadlineOption.equals(selectedItem)) {
                    // Convert the due time to a LocalDateTime
                    LocalDateTime dateTime = convertToLocalDateTime(
                            deadlineCard.dueDateTime.getDateString(),
                            deadlineCard.dueDateTime.getTimeString()
                    );

                    // Early return if the dateTime was not successfully converted
                    if (dateTime == null) {
                        return;
                    }

                    // Add a new Deadline to the eventListPanel
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
        private final JTextField dateField = new JTextField(10);
        private final JTextField timeField = new JTextField(5);

        public LocalDateTimePanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Panel to hold the date information
            JPanel datePanel = new JPanel() {
                {
                    add(new JLabel("Enter date(yyyy-MM-dd):"));
                    add(dateField);
                }
            };
            add(datePanel);

            // Panel to hold the time information
            JPanel timePanel = new JPanel() {
                {
                    add(new JLabel("Enter time(HH:mm):"));
                    add(timeField);
                }
            };
            add(timePanel);
        }

        // Returns the contents of the dateField
        public String getDateString() {
            return dateField.getText();
        }

        // Returns the contents of the timeField
        public String getTimeString() {
            return timeField.getText();
        }
    }


    // The panel to obtain Location data from the user
    private static class LocationPanel extends JPanel {
        private final JTextField locationField = new JTextField(10);

        public LocationPanel() {
            add(new JLabel("Location:"));
            add(locationField);
        }

        // Returns the contents of the locationField
        public String getLocationString() {
            return locationField.getText();
        }
    }


    // The panel to obtain Name data from the user
    private static class NamePanel extends JPanel {
        private final JTextField nameField = new JTextField(10);

        public NamePanel() {
            add(new JLabel("Name:"));
            add(nameField);
        }

        // Returns the contents of the nameField
        public String getNameString() {
            return nameField.getText();
        }
    }


    // The panel to hold the contents of the Meeting Card when option is selected
    private static class MeetingCard extends JPanel {
        private final NamePanel namePanel = new NamePanel();
        private final LocalDateTimePanel startPanel = new LocalDateTimePanel();
        private final LocalDateTimePanel endPanel = new LocalDateTimePanel();
        private final LocationPanel locationPanel = new LocationPanel();

        public static final int borderWidth = 1;

        // Constructor
        MeetingCard() {
            // Sets the layout to BoxLayout for a Vertical alignment
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Creates a border for use in the labels
            Border border = BorderFactory.createLineBorder(Color.BLACK, borderWidth);

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
        private final NamePanel namePanel = new NamePanel();
        private final LocalDateTimePanel dueDateTime = new LocalDateTimePanel();

        // Constructor
        DeadlineCard() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(namePanel);
            add(dueDateTime);
        }
    }


    // Takes a given date and time (strings) and converts to a LocalDateTime object
    private LocalDateTime convertToLocalDateTime(String date, String time) {
        // Formats the string input into a LocalDateTime with the given pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Concatenates the date and time into the format provided
        String dateTimeString = date + "T" + time;

        // Parses the LocalDateTime and returns an error window if unsuccessful
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid format for LocalDateTime. Make sure to add a 0 before numbers that are a single character!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return null;
        }
    }
}
