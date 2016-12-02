package GUI;

import java.util.ArrayList;
import Appointment.Appointment;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author nichambers
 */
public class StudentGUI {

    //open Appointments
    private ArrayList<Appointment> _openAppts;

    public StudentGUI(ArrayList<Appointment> open) {

        _openAppts = open;
        
    }
}
