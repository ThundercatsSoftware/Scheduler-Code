package Appointment;

import java.io.File;

/**
 *
 * @author nichambers
 */
public class Appointment {

    private String _status;
    private String _name;
    private int _day;
    private int _month;
    private int _year;
    private String _date;
    private int _hour;
    private int _minute;
    private String _time;
    private String _length;
    private String _fellow;
    private File _draft;

    public Appointment(String status, String name, String date, String time, String length, String fellow, File draft) {
        _status = status;
        _name = name;
        setDate(date);
        setTime(time);
        _length = length;
        _fellow = fellow;
        _draft = draft;
    }

    public String getName() {
        return _name;
    }
    
    public void setName(String n){
        _name = n;
    }

    public String getDate() {
        return _date;
    }

    public int getYear() {
        return _year;
    }

    public int getMonth() {
        return _month;
    }

    public int getDay() {
        return _day;
    }

    public void setDate(String d) {
        _month = Integer.parseInt(d.substring(0, d.indexOf("/")));
        _day = Integer.parseInt(d.substring(d.indexOf("/") + 1, d.lastIndexOf("/")));
        _year = Integer.parseInt(d.substring(d.lastIndexOf("/") + 1));
        _date = d;
    }

    public String getTime() {
        return _time;
    }

    public int getHour() {
        return _hour;
    }

    public int getMinute() {
        return _minute;
    }

    public void setTime(String t) {
        _hour = Integer.parseInt(t.substring(0, t.indexOf(":")));
        _minute = Integer.parseInt(t.substring(t.indexOf(":") + 1, t.indexOf(":") + 3));
        _time = _hour + t.substring(t.indexOf(":"));
    }

    public String getLength() {
        return _length;
    }

    public void setLength(String l) {
        _length = l;
    }

    public String getFellow() {
        return _fellow;
    }

    public void setFellow(String f) {
        _fellow = f;
    }

    public File getDraft() {
        return _draft;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String s) {
        _status = s;
    }

    public String toString() {
        return "<html>" + _status + "<br>" + _name + " " + _date + "<br>" + _time + " "
                + _length + "<br> Fellow: " + _fellow + "<br>" + _draft.getName() + "</html>";
    }

    //returns this appointment or the one being compared to it, whichever has the date farther in the future
    //not proud of this sorting algorithm but it works
    public String compareDate(Appointment b) {

        String toReturn = null;

        if (_year > b.getYear()) {
            toReturn = "first bigger";
        } else if (b.getYear() > _year) {
            toReturn = "second bigger";
        } else if (_month > b.getMonth()) {
            toReturn = "first bigger";
        } else if (b.getMonth() > _month) {
            toReturn = "second bigger";
        } else if (_day > b.getDay()) {
            toReturn = "first bigger";
        } else if (b.getDay() > _day) {
            toReturn = "second bigger";
        } else if (_hour > b.getHour()) {
            toReturn = "first bigger";
        } else if (b.getHour() > _hour) {
            toReturn = "second bigger";
        } else if (_minute > b.getMinute()) {
            toReturn = "first bigger";
        } else if (b.getMinute() > _minute) {
            toReturn = "second bigger";
        }
        return toReturn;
    }

    //Puts Appointment data in a format friendly to write to a file
    public String toFile() {
        String FileOut;

        FileOut = _status + '\n'
                + _name + '\n'
                + _date + '\n'
                + _time + '\n'
                + _length + '\n'
                + _fellow + '\n'
                + _draft.getPath();

        return FileOut;
    }

}
