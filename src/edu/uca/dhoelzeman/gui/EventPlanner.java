package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Deadline;
import edu.uca.dhoelzeman.console.MeetingDecorator;
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
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src/images/calendar.png").getImage());

        var eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);

        frame.add(eventListPanel);

        frame.pack();
        frame.setVisible(true);
    }

    static void addDefaultEvents(EventListPanel eventListPanel) {
        final LocalDateTime now = LocalDateTime.now();

        // Creates events with the current date/time as the event starting time
        eventListPanel.events.add(
                new MeetingDecorator(
                    new Meeting(
                            "Dr. Baarsch's Java Class",
                            now,
                            LocalDateTime.of(
                                    now.getYear(),
                                    now.getMonth(),
                                    now.getDayOfMonth(),
                                    now.getHour() + 2, // Adds 2 hours to the default meeting so the Duration can be calculated
                                    now.getMinute() + 1
                            )
                    ),
                    "MCS 339"
                )
        );
        eventListPanel.events.add(
                new Deadline(
                        "Lab 2 Due",
                        LocalDateTime.of(2024, 9, 25, 15, 0)
                )
        );

        eventListPanel.addOrUpdateEvents();
    }
}
