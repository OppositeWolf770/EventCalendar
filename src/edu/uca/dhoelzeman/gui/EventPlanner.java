package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.Meeting;
import edu.uca.dhoelzeman.console.TestEvent;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPlanner {
    public static void main(String[] args) {
        var frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src/images/calendar.png").getImage());

        frame.add(new EventListPanel());


        frame.pack();
        frame.setVisible(true);
    }

    static void addDefaultEvents(EventPanel events) {

    }
}
