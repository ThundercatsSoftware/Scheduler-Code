package GUI;

import java.util.ArrayList;
import Appointment.Appointment;
import FileReading.FileReadAndSort;
import java.awt.*;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author nichambers
 */
public class StudentGUI extends JFrame {

    //JPanel
    JPanel _studentHome = new JPanel();

    //Appointments
    private ArrayList<Appointment> _openList = new ArrayList<>();
    private ArrayList<Appointment> _pendingList = new ArrayList<>();

    //JLabel confirmation
    private JLabel _confirm = new JLabel();

    //labels for appts
    private ArrayList<JLabel> _openLabels = new ArrayList<>();

    public StudentGUI(ArrayList<Appointment> open, ArrayList<Appointment> pending) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        _studentHome.setPreferredSize(new Dimension(800, 600));
        _studentHome.setLayout(null);

        _openList = open;
        _pendingList = pending;

        int x = 200; //starting position of list, appointments go down as list grows
        int y = 200;
        int l = 200;
        int h = 90;
        populateList(x, y, l, h, _openList, _openLabels);

        //confirmation label bounds
        _confirm.setBounds(400, 375, 200, 200);
        _studentHome.add(_confirm);
        
        //style
        makeMePretty();

        getContentPane().add(_studentHome);
        pack();

    }

    public void populateList(int x, int y, int l, int h, ArrayList<Appointment> list, ArrayList<JLabel> labels) {
        //Populate list of open appointments
        for (int i = 0; i < _openList.size(); i++) {
            JLabel temp = new JLabel(_openList.get(i).toString());
            _openLabels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 100;
            temp.addMouseListener(new StudentGUI.MouseClickListener());
            _studentHome.add(temp);
        }
    }

    public void showConfirmation(Appointment a) {
        String s = "<html>" + "Appointment for " + "<br>" + a.getName() + " on "
                + a.getDate() + "<br>" + "at " + a.getTime() + " requested!" + "</html>";
        _confirm.setText(s);
    }
    
    public void makeMePretty(){
        //DO STYLE STUFF HERE
    }

    private class MouseClickListener implements MouseListener {

        public void mousePressed(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < _openLabels.size(); i++) {
                if (e.getSource().equals(_openLabels.get(i))) {
                    AppointmentGUI openAppt = new AppointmentGUI(_openList.get(i));
                    openAppt.sendLists(_openList, _pendingList);
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
