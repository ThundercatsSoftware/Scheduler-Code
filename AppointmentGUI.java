/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 *
 * @author nichambers
 */
public class AppointmentGUI extends JFrame{
    
    private JPanel _appt = new JPanel();
    
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
    private JLabel timeLab = new JLabel("Time:");
    private JLabel lengthLab = new JLabel("Length:");
    private JLabel fellowLab = new JLabel("Fellow:");
    private JLabel draftLab = new JLabel("Draft:");
    private JLabel statusLab = new JLabel("Status:");
    
    //buttons, edit and close, edit text changes to "save" after being clicked.
    private JButton editButton = new JButton("Edit");
    private JButton rejectButton = new JButton("Reject");
    private JButton acceptButton = new JButton("Accept");
    
    //Editing Text Fields, become visible when editing, invisible when saved
    private JTextField editLength = new JTextField();
    private JTextField editFellow = new JTextField();
    private JTextField editDate = new JTextField();
    
    public AppointmentGUI(Appointment appt){
        _appt.setPreferredSize(new Dimension(400, 300));
        _appt.setLayout(null);
        
        //set static label boundaries
        nameLab.setBounds(10, 45, 50, 50);
        dateLab.setBounds(120, 25, 50, 50);
        timeLab.setBounds(120, 100, 50, 50);
        lengthLab.setBounds(120, 175, 50, 50);
        fellowLab.setBounds(10, 125, 50, 50);
        _appt.add(nameLab);
        _appt.add(dateLab);
        _appt.add(timeLab);
        _appt.add(lengthLab);
        _appt.add(fellowLab);
        
        //set dynamic Label boundaries and text
        _name.setBounds(60, 45, 200, 50);
        _date.setBounds(170, 25, 200, 50);
        _time.setBounds(170, 100, 100, 50);
        _length.setBounds(170, 175, 100, 50);
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
        
        //button boundaries
        editButton.setBounds(100, 250, 50, 30);
        rejectButton.setBounds(250, 250, 50, 30);
        acceptButton.setBounds(175, 250, 50, 30);
        editButton.addActionListener(new ButtonListener());
        rejectButton.addActionListener(new ButtonListener());
        acceptButton.addActionListener(new ButtonListener());
        _appt.add(editButton);
        _appt.add(rejectButton);
        _appt.add(acceptButton);
        
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
        editLength.setVisible(false);
        
        _appt.add(editFellow);
        _appt.add(editDate);
        _appt.add(editLength);
        
        //pack up
        getContentPane().add(_appt);
        pack();
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource().equals(editButton)){
                if(editButton.getText().equals("Edit")){
                    editButton.setText("Save"); //change button text
                    
                    //set visibilities to bring TextFields to front
                    _fellow.setVisible(false);
                    _date.setVisible(false);
                    _length.setVisible(false);
                    editFellow.setVisible(true);
                    editDate.setVisible(true);
                    editLength.setVisible(true);
                }
                else{
                    editButton.setText("Edit"); //change button text
                    
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
                }
            }
            else if(e.getSource().equals(rejectButton)){
                
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
