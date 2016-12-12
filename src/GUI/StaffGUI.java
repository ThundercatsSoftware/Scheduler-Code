/*
StaffGUI is the user interface for those who have selected "Staff" on the opening screen.
The staff member may not make appointments for people, but may accept or reject pending
appointments and edit accepted ones. The pending appointments may also be edited before making a decision.
Methods:
-StaffGUI creates a user interface from lists given to it by an outside call.
-populateList fills empts ArrayLists with Appointment objects to display on screen.
-makeMePretty stylizes the GUI, adding in all the colors, shapes, and pretty things.
-MouseClickListener (Class) listens for the mouse to click or hover over objects to change their color 
or open whichever Appointment was clicked on
    + MouseEntered senses hovering to change appointment labels to red with white text
    + MouseExited senses when the mouse has left a label to change its colors back
    + MouseClicked senses when a label has been clicked, then brings up its details in another window
-display makes the GUI visible
*/

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
    
    //Custom colors for style
    private Color _UCRed = new Color(162, 35, 35);
    private Color _UCOrange = new Color(255, 180, 27);

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
        _staffHome.setBackground(Color.WHITE);

        //Setting member data
        _pendingList = pending;
        _openList = open;
        _acceptedList = accepted;
        
        //static labels
        openLabel.setBounds(175, 100, 150, 50);
        pendingLabel.setBounds(375, 100, 150, 50);
        acceptedLabel.setBounds(575, 100, 150, 50);
        
        _staffHome.add(openLabel);
        _staffHome.add(pendingLabel);
        _staffHome.add(acceptedLabel);

        //Place JLabel appointments in line with each other, increment coordinates
        int x = 175; //starting position of list, appointments go down as list grows
        int y = 150;
        int l = 175;
        int h = 90;
        //populate lists
        populateList(x, y, l, h, _openList, _openLabels);
        x = 375; //change x and y for list position, l and h constant
        y = 150;
        populateList(x, y, l, h, _pendingList, _pendingLabels);
        x = 575; //change x and y
        y = 150;
        populateList(x, y, l, h, _acceptedList, _acceptedLabels);
        
        //stylize
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
            temp.setBackground(_UCOrange);
            temp.setForeground(Color.BLACK);
            temp.setOpaque(true);
            labels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 100;
            temp.addMouseListener(new MouseClickListener());
            _staffHome.add(temp);
        }
    }
    
    private void makeMePretty(){
        //Top text bar "Bearly Free Staff UI" Text
        JLabel bearlyText = new JLabel("Bearly Free Staff UI");
        bearlyText.setBounds(35, 5, 300, 55);
        bearlyText.setForeground(Color.WHITE);
        Font labelFont = bearlyText.getFont();
        bearlyText.setFont(new Font(labelFont.getName(), Font.BOLD, 25));
        _staffHome.add(bearlyText);
        
        //top bar watermark
        JLabel watermark = new JLabel("Thunder Cats Software Dev Team, 2016");
        watermark.setBounds(38, 45, 250, 25);
        watermark.setForeground(Color.LIGHT_GRAY);
        _staffHome.add(watermark);
        
        //UC logo
        ImageIcon img = new  ImageIcon(new ImageIcon("UCLogo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel();
        logo.setIcon(img);
        logo.setBounds(735, 10, 50, 50);
        _staffHome.add(logo);
        
        //Instructional Label
        JLabel instructions = new JLabel("Click on an Appointment to see your options.");
        instructions.setBounds(175, 75, 650, 25);
        _staffHome.add(instructions);
        
        //Notice label
        JLabel notice = new JLabel("<html>" + "**Notice** Open appointments must be changed"
                + " within a fellow's own schedule." + "</html>");
        notice.setBounds(8, 400, 120, 300);
        _staffHome.add(notice);
        
        //separating lines on side bar
        JLabel midLineTop = new JLabel("_____________");
        midLineTop.setBounds(20, 59, 100, 25);
        _staffHome.add(midLineTop);
        
        JLabel midLine1 = new JLabel("_____________"); //13 underscores
        midLine1.setBounds(20, 94, 100, 25);
        _staffHome.add(midLine1);
        
        JLabel midLine2 = new JLabel("_____________");
        midLine2.setBounds(20, 129, 100, 25);
        _staffHome.add(midLine2);
        
        JLabel midLine3 = new JLabel("_____________");
        midLine3.setBounds(20, 164, 100, 25);
        _staffHome.add(midLine3);
        
        JLabel midLineBottom = new JLabel("_____________");
        midLineBottom.setBounds(20, 199, 100, 25);
        _staffHome.add(midLineBottom);
        
        //sample nonfunctional texts on side bar
        JLabel sideText1 = new JLabel("Edit Your Schedule");
        sideText1.setBounds(8, 80, 125, 25);
        _staffHome.add(sideText1);
        
        JLabel sideText2 = new JLabel("Edit Your Bio");
        sideText2.setBounds(8, 115, 125, 25);
        _staffHome.add(sideText2);
        
        JLabel sideText3 = new JLabel("Notifications");
        sideText3.setBounds(8, 150, 125, 25);
        _staffHome.add(sideText3);
        
        JLabel sideText4 = new JLabel("Staff Group Chat");
        sideText4.setBounds(8, 185, 125, 25);
        _staffHome.add(sideText4);
        
        //red rectangle across top of screen
        JLabel topRedBox = new JLabel();
        topRedBox.setBounds(-10, -10, 850, 75);
        topRedBox.setBackground(_UCRed);
        topRedBox.setOpaque(true);
        _staffHome.add(topRedBox);
        
        //yellow rectangle side bar
        JLabel sideBar = new JLabel();
        sideBar.setBounds(-10, -10, 150, 650);
        sideBar.setBackground(_UCOrange);
        sideBar.setOpaque(true);
        _staffHome.add(sideBar);
        
    }

    private class MouseClickListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            
            for (int i = 0; i < _pendingLabels.size(); i++) {
                if (e.getSource().equals(_pendingLabels.get(i))) {
                    _pendingLabels.get(i).setBackground(_UCRed);
                    _pendingLabels.get(i).setForeground(Color.WHITE);
                    _pendingLabels.get(i).setOpaque(true);
                }
            }
            
            for (int i = 0; i < _acceptedLabels.size(); i++) {
                if (e.getSource().equals(_acceptedLabels.get(i))) {
                    _acceptedLabels.get(i).setBackground(_UCRed);
                    _acceptedLabels.get(i).setForeground(Color.WHITE);
                    _acceptedLabels.get(i).setOpaque(true);
                }
            }
            
        }

        public void mouseExited(MouseEvent e) {
            
            for (int i = 0; i < _pendingLabels.size(); i++) {
                if (e.getSource().equals(_pendingLabels.get(i))) {
                    _pendingLabels.get(i).setBackground(_UCOrange);
                    _pendingLabels.get(i).setForeground(Color.BLACK);
                    _pendingLabels.get(i).setOpaque(true);
                }
            }
            
            for (int i = 0; i < _acceptedLabels.size(); i++) {
                if (e.getSource().equals(_acceptedLabels.get(i))) {
                    _acceptedLabels.get(i).setBackground(_UCOrange);
                    _acceptedLabels.get(i).setForeground(Color.BLACK);
                    _acceptedLabels.get(i).setOpaque(true);
                }
            }
            
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < _pendingLabels.size(); i++) {
                if (e.getSource().equals(_pendingLabels.get(i))) {
                    AppointmentGUI openAppt = new AppointmentGUI(_pendingList.get(i));
                    openAppt.sendLists(_openList, _pendingList, _acceptedList);
                    openAppt.display();
                }
            }
            for (int i = 0; i < _acceptedLabels.size(); i++) {
                if (e.getSource().equals(_acceptedLabels.get(i))) {
                    AppointmentGUI openAppt = new AppointmentGUI(_acceptedList.get(i));
                    openAppt.sendLists(_openList, _pendingList, _acceptedList);
                    openAppt.display();
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
