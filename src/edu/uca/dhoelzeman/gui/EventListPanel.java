package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Event;
import edu.uca.dhoelzeman.console.Meeting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventListPanel extends JPanel {
    ArrayList<Event> events = new ArrayList<>();
    // Set up the control panel
    JButton addEventButton = new JButton("Add Event");
    JComboBox<String> sortDropDown = new JComboBox<>() {
        {
            addItem("Name");
            addItem("Date");
        }
    };
    JPanel controlPanel = new JPanel() {
        {
            setPreferredSize(new Dimension(0, 100));
            setBackground(new Color(0xFF00FF));
            add(addEventButton);
            add(sortDropDown);
        }
    };
    JPanel displayPanel = new JPanel();
    JCheckBox filterDisplay;

    EventListPanel() {
        setPreferredSize(new Dimension(750, 1000));

        // Use BoxLayout for vertical alignment
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(controlPanel);

        // Set up the displayPanel
        displayPanel.setPreferredSize(new Dimension(0, 400));
        displayPanel.setBackground(new Color(0x00FFFF));

        add(displayPanel);

        // Add Event Button listener for click
        addEventButton.addActionListener(_ -> new AddEventModal(this));
    }
}
