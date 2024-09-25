package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Meeting;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPlanner {
    public static void main(String[] args) {
        var frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setLocation(200, 200);
//        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src/images/calendar.png").getImage());

        var eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);

        frame.add(eventListPanel);


        frame.pack();
        frame.setVisible(true);
    }

    static void addDefaultEvents(EventListPanel eventListPanel) {
        eventListPanel.events.add(new Meeting("B", LocalDateTime.of(2003, 3, 7, 13, 30), LocalDateTime.of(2003, 3, 7, 12, 35), "Default Location"));
        eventListPanel.events.add(new Deadline("A", LocalDateTime.of(2003, 3, 7, 12, 30)));

        eventListPanel.addOrUpdateEvents();
    }
}
