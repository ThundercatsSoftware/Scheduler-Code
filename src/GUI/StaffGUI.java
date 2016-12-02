package GUI;

import Schedule.Appointment;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;

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

    //Arrays of Appointments for data manipulation
    private ArrayList<Appointment> _pendingList = new ArrayList<>(); //appointments requested/pending
    private ArrayList<Appointment> _openList = new ArrayList<>(); //appointments rejected
    private ArrayList<Appointment> _acceptedList = new ArrayList<>(); //appointments accepted

    public StaffGUI(ArrayList<Appointment> open, ArrayList<Appointment> pending, ArrayList<Appointment> accepted) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        _staffHome.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        _staffHome.setLayout(null);

        //Setting member data
        _pendingList = pending;
        _openList = open;
        _acceptedList = accepted;

        //Populate pending list
        //Place JLabel appointments in line with each other, increment coordinates
        int x = 50; //starting position of list, appointments go down as list grows
        int y = 50;
        int l = 150;
        int h = 75;
        for (int i = 0; i < _pendingList.size(); i++) {
            JLabel temp = new JLabel(_pendingList.get(i).toString());
            _pendingLabels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 125;
            temp.addMouseListener(new MouseClickListener());
            _staffHome.add(temp);
        }

        //Populate rejected list
        //Place JLabel appointments in line with each other, increment coordinates
        x = 300; //change x and y for list position, l and h constant
        y = 50;
        for (int i = 0; i < _openList.size(); i++) {
            JLabel temp = new JLabel(_openList.get(i).toString());
            _openLabels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 125;
            temp.addMouseListener(new MouseClickListener());
            _staffHome.add(temp);
        }

        //Populate accepted list
        //Place JLabel appointments in line with each other, increment coordinates
        x = 500; //change x and y for list position, l and h constant
        y = 50;
        for (int i = 0; i < _acceptedList.size(); i++) {
            JLabel temp = new JLabel(_acceptedList.get(i).toString());
            _acceptedLabels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 125;
            temp.addMouseListener(new MouseClickListener());
            _staffHome.add(temp);
        }

        //List Labels
        //gui maker
        getContentPane().add(_staffHome);
        pack();
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
                    openAppt.display();
                }
            }
            for (int i = 0; i < _openLabels.size(); i++) {
                if (e.getSource().equals(_openLabels.get(i))) {
                    AppointmentGUI openAppt = new AppointmentGUI(_openList.get(i));
                    openAppt.display();
                }
            }
            for (int i = 0; i < _acceptedLabels.size(); i++) {
                if (e.getSource().equals(_acceptedLabels.get(i))) {
                    AppointmentGUI openAppt = new AppointmentGUI(_acceptedList.get(i));
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
