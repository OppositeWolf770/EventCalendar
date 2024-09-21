package edu.uca.dhoelzeman.gui;

import edu.uca.dhoelzeman.console.Event;

import javax.swing.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel {
    ArrayList<Event> events;
    JPanel controlPanel;
    JPanel displayPanel;
    JComboBox sortDropDown;
    JCheckBox filterDisplay;
    JButton addEventButton;
}
