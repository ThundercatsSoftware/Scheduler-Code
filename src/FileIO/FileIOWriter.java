/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileIO;

import java.util.ArrayList;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import Schedule.Appointment;
import java.io.IOException;

/**
 *
 * @author Henry
 */
public class FileIOWriter {
    ArrayList<Appointment> ApptList;
    File toWrite;
    BufferedWriter out;
    
    public FileIOWriter(ArrayList l, File f) throws IOException{
	ApptList = l;
	toWrite = f;
	
	out = new BufferedWriter(new FileWriter(toWrite));
    }
    
    public void writeAppts(){
	int i = 0;
	
	while(!ApptList.isEmpty()){
	    Appointment holder;
	    
	    holder = ApptList.get(i);
	    i++;
	    
	    try{
		out.write(holder.toFile());
		out.close();
	    }
	    catch(Exception e){
		System.out.println("IO FAILURE");
	    }
	}
    }
}
