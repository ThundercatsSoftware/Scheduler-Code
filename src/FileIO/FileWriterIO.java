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
 * scheduler into data files so appointments can be saved over multiple uses of
 * the application.
 *
 * @author Henry
 * @editor nichambers
 */
public class FileWriterIO {

    ArrayList<Appointment> ApptList;
    File toWrite;

    public FileWriterIO() throws IOException {

    }

    public void writeAppts(ArrayList<Appointment> l) {
        if (l.size() > 0) {
            ApptList = l;

            String s = ApptList.get(0).getStatus();
            if (s.equalsIgnoreCase("Open")) {
                File f = new File("OpenAppts.txt");
                toWrite = f;
            } else if (s.equalsIgnoreCase("Pending")) {
                File f = new File("PendingAppts.txt");
                toWrite = f;
            } else if (s.equalsIgnoreCase("Accepted")) {
                File f = new File("AcceptedAppts.txt");
                toWrite = f;
            }
            try {
                FileWriter fw = new FileWriter(toWrite, false);
                BufferedWriter out = new BufferedWriter(fw);
                for (int i = 0; i < l.size(); i++) {
                    Appointment holder;

                    holder = ApptList.get(i);

                    out.write(holder.getStatus());
                    out.newLine();
                    out.write(holder.getName());
                    out.newLine();
                    out.write(holder.getDate());
                    out.newLine();
                    out.write(holder.getTime());
                    out.newLine();
                    out.write(holder.getLength());
                    out.newLine();
                    out.write(holder.getFellow());
                    out.newLine();
                    out.write(holder.getDraft().getName());
                    out.newLine();

                }
                out.close();
            } catch (Exception except) {
                System.out.println(except);
            }
        } else {

        }
    }

    public void clearFile(File f) {

        try {
            FileWriter fw = new FileWriter(f, false);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(" ");
            out.close();

        } catch (Exception except) {
            System.out.println(except);
        }

    }
}
