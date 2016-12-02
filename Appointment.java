package Appointment;

import java.io.File;

/**
 *  
 * @author nichambers
 */
public class Appointment {

    private String _status;
    private String _name;
    static int _day;
    static int _month;
    static int _year;
    private String _date;
    private String _time;
    private String _length;
    private String _fellow;
    private File _draft;

    public Appointment(String status, String name, String date, String time, String length, String fellow, File draft) {
        _status = status;
        _name = name;
        setDate(date);
        _time = time;
        _length = length;
        _fellow = fellow;
        _draft = draft;
    }

    public String getName() {
        return _name;
    }

    public String getDate() {
        return _date;
    }
    
    public void setDate(String d){
        _month = Integer.parseInt(d.substring(0, d.indexOf("/")));
        _day = Integer.parseInt(d.substring(d.indexOf("/")+1, d.lastIndexOf("/")));
        _year = Integer.parseInt(d.substring(d.lastIndexOf("/")+1));
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
        return "<html>" + _status + "<br>" + _name + " " + _date + "<br>" + _time + " "
                + _length + "<br> Fellow: " + _fellow + "<br>" + _draft.getName() + "</html>";
    }
    
    //returns this appointment or the one being compared to it, whichever has the date farther in the future
    public String compareDate(Appointment b){
        
        String toReturn = null;
        
        if(_year > b._year){
            toReturn  = "a";
        }
        else if (b._year > _year){
            toReturn = "b";
        }
        else if(_month > b._month){
            toReturn = "a";
        }
        else if(b._month > _month){
            toReturn = "b";
        }
        else if(_day > b._day){
            toReturn = "a";
        }
        else if(b._day > _day){
            toReturn = "b";
        }
        return toReturn;
    }

}
