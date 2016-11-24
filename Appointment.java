/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.io.File;
import java.util.Date;
/**
 *
 * @author nichambers
 */
public class Appointment {
    
    String _name;
    Date _date;
    String _time;
    String _length;
    String _fellow;
    File _draft;
    String _status;
    
    public Appointment(String name, Date date, String time, String length, String fellow, File draft, String status){
        _name = name;
        _date = date;
        _time = time;
        _length = length;
        _fellow = fellow;
        _draft = draft;
        _status = status;
    }
    
    public String getName(){
        return _name;
    }
    
    public Date getDate(){
        return _date;
    }
    
    public String getTime(){
        return _time;
    }
    
    public String getLength(){
        return _length;
    }
    
    public String getFellow(){
        return _fellow;
    }
    
    public File getDraft(){
        return _draft;
    }
    
    public String getStatus(){
        return _status;
    }
            
    public String toString(){
        return  "<html>" + _name + " " + _date + "<br>" + _time + " " 
                + _length + "<br>" + _fellow + " " + _draft.getName() + "</html>";
    }
    
}
