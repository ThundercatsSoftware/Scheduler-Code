package Appointment;

import java.io.File;

/**
 *  
 * @author nichambers
 */
public class Appointment {

    String _name;
    String _date;
    String _time;
    String _length;
    String _fellow;
    File _draft;
    String _status;

    public Appointment(String name, String date, String time, String length, String fellow, File draft, String status) {
        _name = name;
        _date = date;
        _time = time;
        _length = length;
        _fellow = fellow;
        _draft = draft;
        _status = status;
    }

    public String getName() {
        return _name;
    }

    public String getDate() {
        return _date;
    }
    
    public void setDate(String d){
        _date = d;
    }

    public String getTime() {
        return _time;
    }

    public String getLength() {
        return _length;
    }
    
    public void setLength(String l){
        _length = l;
    }

    public String getFellow() {
        return _fellow;
    }
    
    public void setFellow(String f){
        _fellow = f;
    }

    public File getDraft() {
        return _draft;
    }

    public String getStatus() {
        return _status;
    }
    
    public void setStatus(String s){
        _status = s;
    }

    public String toString() {
        return "<html>" + _name + " " + _date + "<br>" + _time + " "
                + _length + "<br>" + _fellow + " " + _draft.getName() + "</html>";
    }

}
