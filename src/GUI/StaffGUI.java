package GUI;

import FileReading.FileWriterIO;
import Appointment.Appointment;
import FileReading.FileReadAndSort;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author nichambers
 */
public class StaffGUI extends JFrame {

    private JPanel _staffHome = new JPanel();

    //Arrays of Labels for display
    private ArrayList<JLabel> _pendingLabels = new ArrayList<>(); //pendingLabels = pending appointments
    private ArrayList<JLabel> _openLabels = new ArrayList<>(); //openLabels = 
    private ArrayList<JLabel> _acceptedLabels = new ArrayList<>(); //acceptedLabels = accepted requests
    
    //static labels
    JLabel openLabel = new JLabel("Open Appointments:");
    JLabel pendingLabel = new JLabel("Pending Appointments:");
    JLabel acceptedLabel = new JLabel("Accepted Appointments:");

    //Arrays of Appointments for data manipulation
    private ArrayList<Appointment> _pendingList = new ArrayList<>(); //appointments requested/pending
    private ArrayList<Appointment> _openList = new ArrayList<>(); //appointments rejected
    private ArrayList<Appointment> _acceptedList = new ArrayList<>(); //appointments accepted

    public StaffGUI(ArrayList<Appointment> open, ArrayList<Appointment> pending, ArrayList<Appointment> accepted) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        _staffHome.setPreferredSize(new Dimension(800, 600));
        _staffHome.setLayout(null);

        //Setting member data
        _pendingList = pending;
        _openList = open;
        _acceptedList = accepted;
        
        //static labels
        openLabel.setBounds(275, 100, 150, 50);
        pendingLabel.setBounds(75, 100, 150, 50);
        acceptedLabel.setBounds(475, 100, 150, 50);
        
        _staffHome.add(openLabel);
        _staffHome.add(pendingLabel);
        _staffHome.add(acceptedLabel);

        //Place JLabel appointments in line with each other, increment coordinates
        int x = 75; //starting position of list, appointments go down as list grows
        int y = 150;
        int l = 175;
        int h = 90;
        //populate lists
        populateList(x, y, l, h, _pendingList, _pendingLabels);
        x = 275; //change x and y for list position, l and h constant
        y = 150;
        populateList(x, y, l, h, _openList, _openLabels);
        x = 475; //change x and y
        y = 150;
        populateList(x, y, l, h, _acceptedList, _acceptedLabels);
        
        makeMePretty();

        //pack
        getContentPane().add(_staffHome);
        pack();
    }

    private void populateList(int x, int y, int l, int h, ArrayList<Appointment> list, ArrayList<JLabel> labels) {

        //Populate list sent in
        //Place JLabel appointments in line with each other, increment coordinates
        for (int i = 0; i < list.size(); i++) {
            JLabel temp = new JLabel(list.get(i).toString());
            labels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 100;
            temp.addMouseListener(new MouseClickListener());
            _staffHome.add(temp);
        }
    }
    
    private void makeMePretty(){
        //DO ALL STYLE STUFF HERE
    }

    private class MouseClickListener implements MouseListener {

        private Color _UCRed = new Color(162, 35, 35);
        private Color _UCOrange = new Color(230, 134, 9);

        public void mousePressed(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < _pendingLabels.size(); i++) {
                if (e.getSource().equals(_pendingLabels.get(i))) {
                    AppointmentGUI openAppt = new AppointmentGUI(_pendingList.get(i));
                    openAppt.sendLists(_openList, _pendingList, _acceptedList);
                    openAppt.display();
                    setVisible(false);
                    dispose();
                }
            }
            for (int i = 0; i < _acceptedLabels.size(); i++) {
                if (e.getSource().equals(_acceptedLabels.get(i))) {
                    AppointmentGUI openAppt = new AppointmentGUI(_acceptedList.get(i));
                    openAppt.sendLists(_openList, _pendingList, _acceptedList);
                    openAppt.display();
                    setVisible(false);
                    dispose();
                }
            }
            
        }

    }

    public void display() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

}
