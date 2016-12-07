/*
 * Bearly Free Scheduler Software
 */
package FileIO;

import java.util.ArrayList;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import Schedule.Appointment;
import java.io.IOException;

/**
 * FileIOWriter is used to write appointments that have been entered into the
 * scheduler into data files so appointments can be saved over multiple uses
 * of the application.
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
	int l = ApptList.size();
	
	for(int i=0;i<=l;i++){
	    Appointment holder;
	    
	    holder = ApptList.get(i);
	    
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
