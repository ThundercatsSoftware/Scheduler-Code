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

    //Sorts array of Appointments by date using bubble sort
    public ArrayList<Appointment> sort(ArrayList<Appointment> array) {
        if (array.size() > 1) {
            for (int i = array.size() - 1; i >= 0; i--) {
                for (int j = 1; j < i; j++) {
                    if (array.get(j - 1).compareDate(array.get(j)).equals("a")) {
                        Appointment a = array.get(j - 1);
                        Appointment b = array.get(j);
                        array.remove(j - 1);
                        array.add(j - 1, b);
                        array.remove(j);
                        array.add(j, a);
                    }
                }

            }
        }

        return array;
    }
}