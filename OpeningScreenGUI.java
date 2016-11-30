package GUI;

import Appointment.Appointment;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * First screen to appear when program runs, choice of student or staff interface
 * @author nichambers
 */
public class OpeningScreenGUI extends JFrame {

    //instantiate JPanel
    private JPanel _OpSc = new JPanel();

    //Buttons
    private JButton _studentButton = new JButton("Student");
    private JButton _staffButton = new JButton("Staff");

    //Label
    private JLabel _question = new JLabel("Who are you?");

    //ArrayLists to pass to next  GUI
    ArrayList<Appointment> _open = new ArrayList<>();
    ArrayList<Appointment> _pending = new ArrayList<>();
    ArrayList<Appointment> _accepted = new ArrayList<>();

    public OpeningScreenGUI(ArrayList<Appointment> open, ArrayList<Appointment> pending, ArrayList<Appointment> accepted) {

        //set basic details
        _OpSc.setPreferredSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        _OpSc.setLayout(null);

        //set member data
        _open = open;
        _pending = pending;
        _accepted = accepted;

        //Button coordinates
        _studentButton.setBounds(100, 300, 100, 100);
        _staffButton.setBounds(300, 300, 100, 100);

        _studentButton.addActionListener(new ButtonListener());
        _staffButton.addActionListener(new ButtonListener());

        _OpSc.add(_studentButton);
        _OpSc.add(_staffButton);

        //Label coordinates
        _question.setBounds(200, 200, 100, 100);

        _OpSc.add(_question);

        getContentPane().add(_OpSc);
        pack();

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(_studentButton)) { //Open Student GUI
                StudentGUI studgui = new StudentGUI(_open); //Students only need to see what they can request
                studgui.display();
                setVisible(false); //close this window
                dispose();
            } else if (e.getSource().equals(_staffButton)) { //Open Staff GUI
                StaffGUI staffgui = new StaffGUI(_open, _pending, _accepted); //staff will see all appointments
                staffgui.display();
                setVisible(false); //close this window
                dispose();
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
