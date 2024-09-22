package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Event;
import edu.uca.dhoelzeman.console.Meeting;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddEventModal extends JDialog {
    String[] eventOptions = new String[] {
            "Meeting",
            "Deadline"
    };

    AddEventModal modal;

    public AddEventModal(EventListPanel eventListPanel) {
        modal = this;
        this.setModal(true);
        this.setSize(200, 150);

        this.setTitle(this.toString());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        this.getContentPane().add(new AddEventPanel());
        this.getContentPane().add(new JPanel() {
            {
                JTextField nameField = new JTextField(10);
                JComboBox<String> eventOptions = new JComboBox<>() {
                    {
                        addItem("Meeting");
                        addItem("Deadline");
                    }
                };


                JButton addEventButton = new JButton("Submit");

                add(nameField);
                add(eventOptions);
                add(addEventButton);

                addEventButton.addActionListener(e -> {
                    JLabel confirmationLabel = new JLabel();

                    confirmationLabel = new JLabel("Testing");

//                    if (nameField.getSize() != new Dimension(0, 0).getSize()) {
//                        confirmationLabel = new JLabel("Error!");
//                    } else {
//                        Event newEvent = new Deadline("Deadline 1", LocalDateTime.now());
//                        eventListPanel.events.add(newEvent);
//                        add(new JLabel("Added a new " + newEvent.getClass()));
//                    }

                    add(confirmationLabel);
                    revalidate();
                    this.remove(confirmationLabel);
                });


            }
        });
        this.setVisible(true);
    }


    private class AddEventPanel extends JPanel {
        public AddEventPanel() {
            JTextField nameField = new JTextField(10);
            JButton addEventButton = new JButton("Submit");

            this.add(addEventButton);


            addEventButton.addActionListener(_ -> {
                var newEvent = new Deadline("Deadline", LocalDateTime.now());
            });
        }
    }
}
