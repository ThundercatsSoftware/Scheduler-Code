/*
StudentGUI is the user interface for those who have selected "Student" on the opening screen.
The Student may request to schedule appointments from open slots that display when more
appointments are added to the OpenAppts.txt file.
Methods:
-StudentGUI creates a user interface from lists given to it by an outside call.
-populateList fills empts ArrayLists with Appointment objects to display on screen.
-makeMePretty stylizes the GUI, adding in all the colors, shapes, and pretty things.
-MouseClickListener (Class) listens for the mouse to click or hover over objects to change their color 
or open whichever Appointment was clicked on
    + MouseEntered senses hovering to change appointment labels to red with white text
    + MouseExited senses when the mouse has left a label to change its colors back
    + MouseClicked senses when a label has been clicked, then brings up its details in another window
-display makes the GUI visible
*/
package GUI;

import java.util.ArrayList;
import Schedule.Appointment;
import FileIO.FileIOReader;
import java.awt.*;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

/**
 *
 * @author nichambers
 */
public class StudentGUI extends JFrame {

    //JPanel
    private JPanel _studentHome = new JPanel();

    //Appointments
    private ArrayList<Appointment> _openList = new ArrayList<>();
    private ArrayList<Appointment> _pendingList = new ArrayList<>();

    //JLabel confirmation
    private JLabel _confirm = new JLabel();

    //labels for appts
    private ArrayList<JLabel> _openLabels = new ArrayList<>();

    //Custom colors for style
    private Color _UCRed = new Color(162, 35, 35);
    private Color _UCOrange = new Color(255, 180, 27);

    public StudentGUI(ArrayList<Appointment> open, ArrayList<Appointment> pending) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        _studentHome.setPreferredSize(new Dimension(800, 600));
        _studentHome.setLayout(null);
        _studentHome.setBackground(Color.WHITE);
        _openList = open;
        _pendingList = pending;

        int x = 175; //starting position of list, appointments go down as list grows
        int y = 100;
        int l = 200;
        int h = 90;
        populateList(x, y, l, h, _openList, _openLabels);

        //confirmation label bounds
        _confirm.setBounds(350, 400, 200, 200);
        _studentHome.add(_confirm);

        //Style and beauty
        makeMePretty();
         
        getContentPane().add(_studentHome);
        pack();

    }

    public void populateList(int x, int y, int l, int h, ArrayList<Appointment> list, ArrayList<JLabel> labels) {
        //Populate list of open appointments
        for (int i = 0; i < _openList.size(); i++) {
            JLabel temp = new JLabel(_openList.get(i).toString());
            temp.setBackground(_UCOrange);
            temp.setForeground(Color.BLACK);
            temp.setOpaque(true);
            _openLabels.add(temp);

            temp.setBounds(x, y, l, h);
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            y += 100;
            temp.addMouseListener(new StudentGUI.MouseClickListener());
            _studentHome.add(temp);
        }
    }

    public void showConfirmation(Appointment a) {
        String s = "<html>" + "Appointment for " + "<br>" + a.getName() + " on "
                + a.getDate() + "<br>" + "at " + a.getTime() + " requested!" + "</html>";
        _confirm.setText(s);
    }
    
    //Stylizes the UI to be not ugly, non functional pieces
    public void makeMePretty(){
   
        //Top text bar "Bearly Free Student UI" Text
        JLabel bearlyText = new JLabel("Bearly Free Student UI");
        bearlyText.setBounds(35, 5, 300, 55);
        bearlyText.setForeground(Color.WHITE);
        Font labelFont = bearlyText.getFont();
        bearlyText.setFont(new Font(labelFont.getName(), Font.BOLD, 25));
        _studentHome.add(bearlyText);
        
        //UC logo
        ImageIcon img = new  ImageIcon(new ImageIcon("UCLogo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel();
        logo.setIcon(img);
        logo.setBounds(735, 10, 50, 50);
        _studentHome.add(logo);
        
        //red rectangle across top of screen
        JLabel topRedBox = new JLabel();
        topRedBox.setBounds(-10, -10, 850, 75);
        topRedBox.setBackground(_UCRed);
        topRedBox.setOpaque(true);
        _studentHome.add(topRedBox);
        
        //separating lines on side bar
        JLabel midLineTop = new JLabel("_____________");
        midLineTop.setBounds(20, 59, 100, 25);
        _studentHome.add(midLineTop);
        
        JLabel midLine1 = new JLabel("_____________"); //13 underscores
        midLine1.setBounds(20, 94, 100, 25);
        _studentHome.add(midLine1);
        
        JLabel midLine2 = new JLabel("_____________");
        midLine2.setBounds(20, 129, 100, 25);
        _studentHome.add(midLine2);
        
        JLabel midLine3 = new JLabel("_____________");
        midLine3.setBounds(20, 164, 100, 25);
        _studentHome.add(midLine3);
        
        JLabel midLineBottom = new JLabel("_____________");
        midLineBottom.setBounds(20, 199, 100, 25);
        _studentHome.add(midLineBottom);
        
        //sample nonfunctional texts on side bar
        JLabel sideText1 = new JLabel("Help Room Hours");
        sideText1.setBounds(8, 80, 125, 25);
        _studentHome.add(sideText1);
        
        JLabel sideText2 = new JLabel("Fellow Bios");
        sideText2.setBounds(8, 115, 125, 25);
        _studentHome.add(sideText2);
        
        JLabel sideText3 = new JLabel("About Us");
        sideText3.setBounds(8, 150, 125, 25);
        _studentHome.add(sideText3);
        
        JLabel sideText4 = new JLabel("Contact Us");
        sideText4.setBounds(8, 185, 125, 25);
        _studentHome.add(sideText4);
        
        //use info texts
        JLabel info1 = new JLabel("<html>" + "- To request an appointment, just"
                + " click an open spot!" + "</html>");
        info1.setBounds(510, 110, 200, 75);
        info1.setForeground(Color.WHITE);
        _studentHome.add(info1);
        
        JLabel info2 = new JLabel("<html>" + "- Writing fellows will post their own" 
                       + " availability as they get time!" + "</html>");
        info2.setBounds(510, 175, 200, 75);
        info2.setForeground(Color.WHITE);
        _studentHome.add(info2);
        
        JLabel info3 = new JLabel("<html>" + "- After scheduling, a writing fellow"
                       + " will accept or reject the request appropriately." + "</html>");
        info3.setBounds(510, 250, 200, 75);
        info3.setForeground(Color.WHITE);
        _studentHome.add(info3);
        
        //Bottom bar watermark
        JLabel watermark = new JLabel("Thunder Cats Software Dev Team, 2016");
        watermark.setBounds(550, 575, 250, 25);
        watermark.setForeground(Color.LIGHT_GRAY);
        _studentHome.add(watermark);
        
        //Bottom bar on screen
        JLabel bottomLine = new JLabel("_________________________________________________________"
                + "______________________________________________________________________________");
        bottomLine.setBounds(140, 550, 1000, 25);
        _studentHome.add(bottomLine);
        
        //red rectangle with use info
        JLabel aboutArea = new JLabel();
        aboutArea.setBounds(500, 100, 250, 400);
        aboutArea.setBackground(_UCRed);
        aboutArea.setOpaque(true);
        _studentHome.add(aboutArea);
        
        //yellow rectangle side bar
        JLabel sideBar = new JLabel();
        sideBar.setBounds(-10, -10, 150, 650);
        sideBar.setBackground(_UCOrange);
        sideBar.setOpaque(true);
        _studentHome.add(sideBar);
    }

    private class MouseClickListener implements MouseListener {

        public void mousePressed(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {
            for (int i = 0; i < _openLabels.size(); i++) {
                if (e.getSource().equals(_openLabels.get(i))) {
                    _openLabels.get(i).setBackground(_UCRed);
                    _openLabels.get(i).setForeground(Color.WHITE);
                    _openLabels.get(i).setOpaque(true);
                }
            }
        }

        public void mouseExited(MouseEvent e) {
            for (int i = 0; i < _openLabels.size(); i++) {
                if (e.getSource().equals(_openLabels.get(i))) {
                    _openLabels.get(i).setBackground(_UCOrange);
                    _openLabels.get(i).setForeground(Color.BLACK);
                    _openLabels.get(i).setOpaque(true);
                }
            }
        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < _openLabels.size(); i++) {
                if (e.getSource().equals(_openLabels.get(i))) {
                    AppointmentGUI openAppt = new AppointmentGUI(_openList.get(i));
                    openAppt.sendLists(_openList, _pendingList);
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
