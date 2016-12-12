/*
AppointmentGUI opens a small window with the details of an appointment. This can be edited, accepted
or rejeced depending on what type of appointment it is.
Students and only students may schedule appointments using this UI.
Staff and only staff may reject, accept, and edit appointments.
Methods:
AppointmentGUI creates the small window UI for users.
SendLists(1) sends lists to make a StaffGUI object once the appointment is done with.
SendLists(2) sends lists to make a StudentGUI object once the appointment is done with.
ButtonListener(Class) waits for button presses to close the appointment, save the data,
    rewrite the files, and create a new Student or Staff GUI.
*/

package GUI;

import FileReading.FileWriterIO;
import Appointment.Appointment;
import FileReading.FileReadAndSort;
import java.awt.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 *
 * @author nichambers
 */
public class AppointmentGUI extends JFrame {

    //initialize  Jpanel
    private JPanel _appt = new JPanel();

    //member data Appointment object passed in
    Appointment appointment;

    //dynamic JLabels, the details of the appointment clicked on
    private JLabel _name = new JLabel();
    private JLabel _date = new JLabel();
    private JLabel _time = new JLabel();
    private JLabel _length = new JLabel();
    private JLabel _fellow = new JLabel();
    private JLabel _draft = new JLabel();
    private JLabel _status = new JLabel();

    //static JLabels, labels of what information is what
    private JLabel nameLab = new JLabel("Name:");
    private JLabel dateLab = new JLabel("Date:");
    private JLabel dateDirectionLab = new JLabel("MM/DD/YYYY");
    private JLabel timeLab = new JLabel("Time:");
    private JLabel lengthLab = new JLabel("Length:");
    private JLabel fellowLab = new JLabel("Fellow:");
    private JLabel draftLab = new JLabel("Draft:");
    private JLabel statusLab = new JLabel("Status:");

    //buttons, edit and close, edit text changes to "save" after being clicked.
    //Schedule button will make an appointment for the user, moves to pending
    private JButton editButton = new JButton("Edit");
    private JButton rejectButton = new JButton("Reject");
    private JButton acceptButton = new JButton("Accept");
    private JButton scheduleButton = new JButton("Schedule Appointment");

    //Editing Text Fields, become visible when editing, invisible when saved
    private JTextField editLength = new JTextField();
    private JTextField editFellow = new JTextField();
    private JTextField editDate = new JTextField();

    //Save ArrayLists from sendLists method for later creation of new gui
    ArrayList<Appointment> _openList = new ArrayList<>();
    ArrayList<Appointment> _pendingList = new ArrayList<>();
    ArrayList<Appointment> _acceptedList = new ArrayList<>();

    public AppointmentGUI(Appointment appt) {
        //set basic details
        _appt.setPreferredSize(new Dimension(400, 300));
        _appt.setLayout(null);

        //Center Panel on screen
        setLocationRelativeTo(null);

        //set Appointment member data
        appointment = appt;

        //set static label boundaries
        nameLab.setBounds(10, 45, 50, 50);
        dateLab.setBounds(170, 25, 50, 50);
        dateDirectionLab.setBounds(230, 55, 70, 50);
        timeLab.setBounds(195, 100, 50, 50);
        lengthLab.setBounds(170, 175, 50, 50);
        fellowLab.setBounds(10, 125, 50, 50);

        _appt.add(nameLab);
        _appt.add(dateLab);
        _appt.add(dateDirectionLab);
        _appt.add(timeLab);
        _appt.add(lengthLab);
        _appt.add(fellowLab);

        //set dynamic Label boundaries and text
        _name.setBounds(60, 45, 200, 50);
        _date.setBounds(230, 25, 100, 50);
        _time.setBounds(255, 100, 100, 50);
        _length.setBounds(230, 175, 100, 50);
        _fellow.setBounds(60, 125, 100, 50);

        _name.setText(appt.getName());
        _date.setText(appt.getDate().toString());
        _time.setText(appt.getTime());
        _length.setText(appt.getLength());
        _fellow.setText(appt.getFellow());
        //_draft; 
        _status.setText(appt.getStatus());

        _appt.add(_name);
        _appt.add(_date);
        _appt.add(_time);
        _appt.add(_length);
        _appt.add(_fellow);

        //button boundaries vary depending on which kind of appointment is viewed
        if (appt.getStatus().equalsIgnoreCase("Pending")) {
            rejectButton.setBounds(260, 250, 100, 30);
            acceptButton.setBounds(150, 250, 100, 30);
            editButton.setBounds(40, 250, 100, 30);

            rejectButton.addActionListener(new ButtonListener());
            acceptButton.addActionListener(new ButtonListener());
            editButton.addActionListener(new ButtonListener());

            _appt.add(rejectButton);
            _appt.add(acceptButton);
            _appt.add(editButton);

        } else if (appt.getStatus().equalsIgnoreCase("Accepted")) {
            editButton.setBounds(100, 250, 100, 30);
            editButton.addActionListener(new ButtonListener());
            _appt.add(editButton);
        } else if (appt.getStatus().equalsIgnoreCase("Open") && !OpeningScreenGUI.USER_NAME.equals("")){
            scheduleButton.setBounds(50, 250, 300, 30);
            scheduleButton.addActionListener(new ButtonListener());
            _appt.add(scheduleButton);
        }

        //editing text field boundaries, should overlay labels being edited
        //also set visibility to false and set text to equal labels
        editFellow.setBounds(_fellow.getBounds());
        editDate.setBounds(_date.getBounds());
        editLength.setBounds(_length.getBounds());

        editFellow.setText(_fellow.getText());
        editDate.setText(_date.getText());
        editLength.setText(_length.getText());

        editFellow.setVisible(false);
        editDate.setVisible(false);
        dateDirectionLab.setVisible(false);
        editLength.setVisible(false);

        _appt.add(editFellow);
        _appt.add(editDate);
        _appt.add(editLength);

        //pack up
        //setLocationRelativeTo(null);
        getContentPane().add(_appt);
        pack();
    }

    //Sends in lists from another class to set up a new GUI with updated Info Later
    public void sendLists(ArrayList<Appointment> open, ArrayList<Appointment> pending, ArrayList<Appointment> accepted) {
        _openList = open;
        _pendingList = pending;
        _acceptedList = accepted;
    }

    public void sendLists(ArrayList<Appointment> open, ArrayList<Appointment> pending) {
        _openList = open;
        _pendingList = pending;
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(editButton)) { //functionality of the "Edit" button
                if (editButton.getText().equals("Edit")) {
                    editButton.setText("Save"); //change button text to "save"

                    //set visibilities to bring TextFields to front for editing, labels invisible
                    _fellow.setVisible(false);
                    _date.setVisible(false);
                    _length.setVisible(false);
                    editFellow.setVisible(true);
                    editDate.setVisible(true);
                    dateDirectionLab.setVisible(true);
                    editLength.setVisible(true);
                } else {
                    editButton.setText("Edit"); //change button text, update labels and document

                    //change label text
                    _fellow.setText(editFellow.getText());
                    _date.setText(editDate.getText());
                    _length.setText(editLength.getText());

                    //set visibilites to bring labels to front
                    _fellow.setVisible(true);
                    _date.setVisible(true);
                    _length.setVisible(true);

                    editFellow.setVisible(false);
                    editDate.setVisible(false);
                    editLength.setVisible(false);

                    //save changes to Appointment
                    appointment.setFellow(_fellow.getText());
                    appointment.setDate(_date.getText());
                    appointment.setLength(_length.getText());

                    //close window, rewrite files, rewrite lists upon saving
                    try {
                        FileReadAndSort frs = FileReadAndSort.getInstance();
                        _pendingList = frs.sort(_pendingList);
                        _acceptedList = frs.sort(_acceptedList);

                        FileWriterIO fw = new FileWriterIO();
                        fw.writeAppts(_pendingList);
                        fw.writeAppts(_acceptedList);
                    } catch (Exception except) {
                        System.out.println(except);
                    }
                    StaffGUI staffgui = new StaffGUI(_openList, _pendingList, _acceptedList);
                    staffgui.display();
                    setVisible(false);
                    dispose();

                }
            } 
            
            else if (e.getSource().equals(rejectButton)) {
                appointment.setStatus("Rejected");
                for (int i = 0; i < _pendingList.size(); i++) {
                    if (_pendingList.get(i).toString().equals(appointment.toString())) {
                        _pendingList.remove(i);
                    }
                }

                try {
                    FileReadAndSort frs = FileReadAndSort.getInstance();
                    _pendingList = frs.sort(_pendingList);
                    _acceptedList = frs.sort(_acceptedList);

                    FileWriterIO fw = new FileWriterIO();

                    if (_pendingList.size() > 0) {
                        fw.writeAppts(_pendingList);
                    } else {
                        File f = new File("PendingAppts.txt");
                        fw.clearFile(f);
                    }
                    if (_acceptedList.size() > 0) {
                        fw.writeAppts(_acceptedList);
                    } else {
                        File f = new File("AcceptedAppts.txt");
                        fw.clearFile(f);
                    }

                } catch (Exception except) {
                    System.out.println(except);
                }
                StaffGUI staffgui = new StaffGUI(_openList, _pendingList, _acceptedList);
                staffgui.display();
                setVisible(false);
                dispose();

            } 
            
            //accept button actions
            else if (e.getSource().equals(acceptButton)) {
                appointment.setStatus("Accepted");
                for (int i = 0; i < _pendingList.size(); i++) {
                    if (_pendingList.get(i).toString().equals(appointment.toString())) {
                        _acceptedList.add(_pendingList.get(i));
                        _pendingList.remove(i);
                    }
                }

                try {
                    FileWriterIO fw = new FileWriterIO();
                    FileReadAndSort frs = FileReadAndSort.getInstance();
                    _pendingList = frs.sort(_pendingList);
                    _acceptedList = frs.sort(_acceptedList);

                    if (_pendingList.size() > 0) {
                        fw.writeAppts(_pendingList);
                    } else {
                        File f = new File("PendingAppts.txt");
                        fw.clearFile(f);
                    }
                    if (_acceptedList.size() > 0) {
                        fw.writeAppts(_acceptedList);
                    } else {
                        File f = new File("AcceptedAppts.txt");
                        fw.clearFile(f);
                    }

                } catch (Exception except) {
                    System.out.println(except);
                }
                StaffGUI staffgui = new StaffGUI(_openList, _pendingList, _acceptedList);
                staffgui.display();
                setVisible(false);
                dispose();
            } 
            
            //Schedule Button actions
            else if (e.getSource().equals(scheduleButton)){
                if(scheduleButton.getText().equals("Schedule Appointment")){
                    scheduleButton.setText("Submit");
                    _name.setText(OpeningScreenGUI.USER_NAME);
                }else if(scheduleButton.getText().equals("Submit")){
                    scheduleButton.setText("Schedule Appointment");
                    appointment.setName(_name.getText());
                 
                     
                for (int i = 0; i < _openList.size(); i++) {
                    if (_openList.get(i).toString().equals(appointment.toString())) {
                        appointment.setStatus("Pending");
                        _pendingList.add(_openList.get(i));
                        _openList.remove(i);
                    }
                }

                try {
                    FileWriterIO fw = new FileWriterIO();
                    FileReadAndSort frs = FileReadAndSort.getInstance();
                    _pendingList = frs.sort(_pendingList);

                    if (_pendingList.size() > 0) {
                        fw.writeAppts(_pendingList);
                    } else {
                        File f = new File("PendingAppts.txt");
                        fw.clearFile(f);
                    }
                    if (_openList.size() > 0) {
                        fw.writeAppts(_openList);
                    } else {
                        File f = new File("OpenAppts.txt");
                        fw.clearFile(f);
                    }

                } catch (Exception except) {
                    System.out.println(except);
                }
                StudentGUI studgui = new StudentGUI(_openList, _pendingList);
                studgui.showConfirmation(appointment);
                studgui.display();
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
