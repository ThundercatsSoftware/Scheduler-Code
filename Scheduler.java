/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import FileReading.FileReadAndSort;
import Appointment.Appointment;
import GUI.OpeningScreenGUI;
import java.util.ArrayList;
import java.io.File;
/**
 *
 * @author nichambers
 */
public class Scheduler {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Appointment> _open = new ArrayList<>();
        ArrayList<Appointment> _pending = new ArrayList<>();
        ArrayList<Appointment> _accepted = new ArrayList<>();
        
        FileReadAndSort fr = FileReadAndSort.getInstance();
        
        File openAppts = new File("OpenAppts.txt");
        File pendingAppts = new File("PendingAppts.txt");
        File acceptedAppts = new File("AcceptedAppts.txt");
        try{
        _open = fr.readInFile(openAppts);
        _pending = fr.readInFile(pendingAppts);
        _accepted = fr.readInFile(acceptedAppts);
        
        _open = fr.sort(_open);
        _pending = fr.sort(_pending);
        _accepted = fr.sort(_accepted);
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        OpeningScreenGUI opGUI = new OpeningScreenGUI(_open, _pending, _accepted);
        opGUI.display();
    }
    
}
