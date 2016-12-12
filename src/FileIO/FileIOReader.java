package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import Schedule.Appointment;
import java.util.Calendar;

/**
 *
 * @author maroselli
 * @editor nichambers made singleton, added reading in of status
 */
public class FileIOReader {

    private static FileIOReader _frs;

    public static FileIOReader getInstance() {
        if (_frs == null) {
            _frs = new FileIOReader();
        }
        return _frs;
    }

    private FileIOReader() {

    }

    public ArrayList readInFile(File file) throws FileNotFoundException, IOException, ParseException {
        String status;
        String name;
        String date;
        String time;
        String length;
        String fellow;
        String draftname;

        BufferedReader br = new BufferedReader(new FileReader(file)); //read everything in first
        ArrayList<String> lines;
        lines = new ArrayList<>();
        //or ask user via Scanner
        String line = "temp";
        //separates each line in the file and creates an array
        while (line != null) {
            line = br.readLine();
            if (line != null) {
                lines.add(line);
            }
        }
        ArrayList<Appointment> apptlist = new ArrayList<>();
        int a = 0;
        int b = 1;
        int c = 2;
        int d = 3;
        int e = 4;
        int f = 5;
        int g = 6;
        for (int i = 0; i < lines.size() / 7; i++) {
            //these commands store the parts of the file into categories 
            //later we could put these back into an appointment object if favorable
            status = lines.get(a); // status of appt (pending, rejected, etc.)
            name = lines.get(b); // your name
            date = lines.get(c);  // date
            time = lines.get(d); // time
            length = lines.get(e); //length of appt
            fellow = lines.get(f); //fellow name
            draftname = lines.get(g); //draft name
            a += 7;
            b += 7;
            c += 7;
            d += 7;
            e += 7;
            f += 7;
            g += 7;

            //now put into Appointment object
            File temp = new File(draftname);
            Appointment appt = new Appointment(status, name, date, time, length, fellow, temp);
            apptlist.add(appt);
        }
        return apptlist;
    }

    //Sorts array of Appointments by date
    public ArrayList<Appointment> sort(ArrayList<Appointment> array) {
        //Quickly sift out rejected appointments
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getStatus().equalsIgnoreCase("Rejected")) {
                array.remove(i);
            }
        }

        if (array.size() > 1) {

            //Sift out any appointments that have already passed current date/time
            //removes appointments every hour
            String currentTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            int year = Integer.parseInt(currentTime.substring(0, 4)); //current year 
            int month = Integer.parseInt(currentTime.substring(4, 6)); //current month
            int day = Integer.parseInt(currentTime.substring(6, 8)); //current day
            int hour = Integer.parseInt(currentTime.substring(9, 11)); //current hour
            for (int i = array.size() - 1; i >= 0; i--) {
                Appointment a = array.get(i);
                if (a.getYear() < year) {
                    array.remove(i);
                } else if (a.getMonth() < month) {
                    array.remove(i);
                } else if (a.getDay() < day) {
                    array.remove(i);
                } else if (a.getDay() == day && a.getHour() < hour) {
                    array.remove(i);
                }
            }

            //Organize by date
            ArrayList<Appointment> sorted = new ArrayList<>();
            int siz = array.size();
            for (int t = 0; t < siz; t++) {

                Appointment minDate = array.get(array.size() - 1);
                int mindex = array.size() - 1;

                for (int i = array.size() - 1; i > 0; i--) {
                    Appointment compare = array.get(i - 1);
                    String s = minDate.compareDate(compare);
                    if (s != null && s.equals("first bigger")) {
                        minDate = compare;
                        mindex = i - 1;
                    }
                }
                sorted.add(array.remove(mindex));
            }
            if (array.size() != 0) {
                sorted.add(array.remove(0));
            }
            return sorted;
        } else {
            return array;
        }
    }
}
