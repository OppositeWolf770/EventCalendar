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
    JPanel controlPanel = new JPanel();
    JPanel displayPanel = new JPanel();
    JComboBox sortDropDown;
    JCheckBox filterDisplay;
    JButton addEventButton = new JButton("Add Event");

    String[] sortOptions = new String[] {
            "Name",
            "Date",

    };

    EventListPanel() {
        setPreferredSize(new Dimension(750, 1000));

        // Use BoxLayout for vertical alignment
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set up the controlPanel
        controlPanel.setPreferredSize(new Dimension(0, 100));
        controlPanel.setBackground(new Color(0xFF00FF));
        controlPanel.add(addEventButton);

//        controlPanel.add(sortDropDown);

        add(controlPanel);

        // Set up the displayPanel
        displayPanel.setPreferredSize(new Dimension(0, 400));
        displayPanel.setBackground(new Color(0x00FFFF));

        add(displayPanel);

        // Add Event Button listener for click
        addEventButton.addActionListener(_ -> new AddEventModal(this));
    }
}
