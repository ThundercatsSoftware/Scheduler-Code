package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author nichambers
 */
public class StaffGUI extends JFrame {

    private JPanel _staffHome = new JPanel();
    private ArrayList<JLabel> _pendLabels = new ArrayList<>(); //reqLabels = requests/pending
    private ArrayList<JLabel> _rejLabels = new ArrayList<>(); //rejLabels = rejected requests
    private ArrayList<JLabel> _accLabels = new ArrayList<>(); //accLabels = accepted requests
    private ArrayList<Appointment> _pendingList = new ArrayList<>(); //appointments requested/pending
    private ArrayList<Appointment> _rejectList = new ArrayList<>(); //appointments rejected
    private ArrayList<Appointment> _acceptList = new ArrayList<>(); //appointments accepted

    public StaffGUI(ArrayList<Appointment> appts) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        _staffHome.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        _staffHome.setLayout(null);
        
        //Sort 
        for(int i = 0; i < appts.size();  i++){
            String status = appts.get(i).getStatus();
            if(status.equalsIgnoreCase("rejected")){
                _rejectList.add(appts.get(i));
            }
            else if (status.equalsIgnoreCase("accepted")){
                _acceptList.add(appts.get(i));
            }
            else if (status.equalsIgnoreCase("pending")){
                _pendingList.add(appts.get(i));
            }
        }

        //Populate pending list
        //Place JLabel appointments in line with each other, increment coordinates
        int x = 50; //starting position of list, appointments go down as list grows
        int y = 50;
        int l = 150;
        int h = 75;
        for (int i = 0; i < _pendingList.size(); i++) {
            JLabel temp = new JLabel(_pendingList.get(i).toString());
            _pendLabels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 125;
            temp.addMouseListener(new MouseClickListener());
            _staffHome.add(temp);
        }

        //Populate rejected list
        //Place JLabel appointments in line with each other, increment coordinates
        x = 300; //change x and y for list position, l and h constant
        y = 50;
        for (int i = 0; i < _rejectList.size(); i++) {
            JLabel temp = new JLabel(_rejectList.get(i).toString());
            _rejLabels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 125;
            temp.addMouseListener(new MouseClickListener());
            _staffHome.add(temp);
        }
        
        //Populate accepted list
        //Place JLabel appointments in line with each other, increment coordinates
        x = 500; //change x and y for list position, l and h constant
        y = 50;
        for (int i = 0; i < _acceptList.size(); i++) {
            JLabel temp = new JLabel(_acceptList.get(i).toString());
            _accLabels.add(temp);
            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 125;
            temp.addMouseListener(new MouseClickListener());
            _staffHome.add(temp);
        }
        
        
        
        //List Labels

        //gui maker
        getContentPane().add(_staffHome);
        pack();
    }

    private class MouseClickListener implements MouseListener {
        private Color _UCRed = new Color(162, 35, 35);
        private Color _UCOrange = new Color(230, 134, 9);
        
        public void mousePressed(MouseEvent e) {
           
        }

        public void mouseEntered(MouseEvent e) {
         
        }

        public void mouseExited(MouseEvent e) {
           
        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {
             for(int i = 0; i < _pendLabels.size(); i++){
                 if(e.getSource().equals(_pendLabels.get(i))){
                     AppointmentGUI openAppt = new AppointmentGUI(_pendingList.get(i));
                     openAppt.display();
                 }
             }
             for(int i = 0; i < _rejLabels.size(); i++){
                 if(e.getSource().equals(_rejLabels.get(i))){
                     AppointmentGUI openAppt = new AppointmentGUI(_rejectList.get(i));
                     openAppt.display();
                 }
             }
             for(int i = 0; i < _accLabels.size(); i++){
                 if(e.getSource().equals(_accLabels.get(i))){
                     AppointmentGUI openAppt = new AppointmentGUI(_acceptList.get(i));
                     openAppt.display();
                 }
             }
        }

    }

    public void display() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

}
