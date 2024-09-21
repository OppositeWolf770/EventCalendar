package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Deadline;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddEventModal extends JDialog {
    private EventListPanel eventListPanel;
    AddEventModal modal;

    public AddEventModal(EventListPanel eventListPanel) {
        modal = this;
        this.eventListPanel = eventListPanel;

        this.setTitle("Add Event");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(new AddEventPanel());
    }


    private class AddEventPanel extends JPanel {
        public AddEventPanel() {
            JTextField nameField = new JTextField(10);
            JButton addEventButton = new JButton("Submit");

            addEventButton.addActionListener(_ -> {
                var newEvent = new Deadline("Deadline", LocalDateTime.now());
            });
        }


    }

//    AddEventModal(Component parent, String name, boolean modal) {
//        super((Frame) null, name, modal);
//
//        setSize(200, 150);
//        setLocationRelativeTo(parent);
//        setVisible(true);
//
//        var button = new JButton("Click Me");
//
//        add(button);
//
//        button.addActionListener(_ -> parent.);
//    }
//
//    private class AddEventPanel extends JPanel {
//
//    }
}
