/*
OpeningScreenGUI is a main menu for the Scheduler system
To continue past this screen, the use rmust select that they are either a student or staff member.
To continue as a student, they MUST enter a name in the text box. Staff are not required to do this.
This is required by students for the purpose of scheduling appointments with an auto-fill name box.
Methods:
OpeningScreenGUI creates a UI for all users to choose staff or student.
ButtonListener(Class) waits for buttons to be pressed. It then deciphers which button was
    pressed and will open up the appropriate GUI for the user.
Display makes the GUI visible.
*/

package GUI;

import Schedule.Appointment;
import Schedule.Scheduler;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * First screen to appear when program runs, choice of student or staff
 * interface
 *
 * @author nichambers
 */
public class OpeningScreenGUI extends JFrame {

    //instantiate JPanel
    private JPanel _OpSc = new JPanel();

    //Buttons
    private JButton _studentButton = new JButton("Student");
    private JButton _staffButton = new JButton("Staff");

    //Label
    private JLabel _directions = new JLabel("Please type your name in the textBox and choose what type of user you are.");

    //TextBox for Name
    private JTextField _userName = new JTextField("");
    public static String USER_NAME;

    //ArrayLists to pass to next  GUI
    private ArrayList<Appointment> _open = new ArrayList<>();
    private ArrayList<Appointment> _pending = new ArrayList<>();
    private ArrayList<Appointment> _accepted = new ArrayList<>();

    public OpeningScreenGUI(ArrayList<Appointment> open, ArrayList<Appointment> pending, ArrayList<Appointment> accepted) {

        //set basic details
        _OpSc.setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
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
        _directions.setBounds(40, 200, 1000, 100);

        _OpSc.add(_directions);

        //TextBox
        _userName.setBounds(150, 100, 200, 50);

        _OpSc.add(_userName);

        getContentPane().add(_OpSc);
        pack();

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(_studentButton)) { //Open Student GUI
                if (!_userName.getText().equals("")) {
                    USER_NAME = _userName.getText();
                    StudentGUI studgui = new StudentGUI(_open, _pending); //Students only need to see what they can request
                    studgui.display();
                    setVisible(false); //close this window
                    dispose();
                } else {
                    _directions.setText("You must first enter a name to proceed as a student");
                }
            } else if (e.getSource().equals(_staffButton)) { //Open Staff GUI
                StaffGUI staffgui = new StaffGUI(_open, _pending, _accepted); //staff will see all appointments
                staffgui.display();
                USER_NAME = "";
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
