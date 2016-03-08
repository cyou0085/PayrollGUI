/*
 * Carlos Young
 * 3/13/13
 * CSCI 292
 * Homework 4: Creating a GUI program to calculate pay for different types of 
 * employees. Program must contain a combo box a button and two text fields to
 * get credentials from user. 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

/**
 *
 * @author Carlos Y
 */

//gui inherits Jframe and has an actionlistener
public class gui extends JFrame implements ActionListener {

    //Variables
    private JPanel panel;          //Panel for holding elements
    private JLabel hourLabel;      //Prints out "Hour"
    private JLabel rateLabel;      //Prints out "Rate"
    private JLabel payLabel;       //Prints out "Pay:"
    private JButton clickButton;   //Button for calculating total
    private JTextField hourText;   //Text box to get hours
    private JTextField rateText;   //Text box to get rates
    private JComboBox empCombo;    //ComboBox to pick employment Type
    private double popup;          //Popup holds salary bonus
    private String arr[] = {"Select an Employee Type...", "Salaried", "Hourly", "Volunteer"};
    
    //Default Constructor
    gui() {
        
        //Set Title and size
        setTitle("PayRoll");
        setSize(500, 200);
        
        //initilize Labels,TextField,ComboBox
        hourLabel = new JLabel("Hour:");
        hourText = new JTextField(7);
        hourText.setText("0");
        rateLabel = new JLabel("Rate:");
        rateText = new JTextField(7);
        rateText.setText("0.0");
        empCombo = new JComboBox(arr);
        //prints out last label "Pay"
        payLabel = new JLabel("Pay: $0.00");

        //Initilizes a click button
        clickButton = new JButton(new AbstractAction("Calculate") {
            //Action listener for the button
            public void actionPerformed(ActionEvent e) {
                DecimalFormat df = new DecimalFormat("#.##");
                String empType;
                empType = (String) empCombo.getSelectedItem();

                //$2000 + a weekly bonus to find yearly salary 
                if (empType == "Salaried") {
                    popup = new Double(JOptionPane.showInputDialog("Enter in weekly Bonus"));
                    payLabel.setText("Pay:$" + (df.format(popup * 52.1775 + 2000)));
                } 
                //Hourly Hours * Pay 
                else if (empType == "Hourly") {
                    
                    String hour = hourText.getText();
                    
                    String rate = rateText.getText();
                   
                    double rate2 = Double.parseDouble(rate);
                    double hour2 = Double.parseDouble(hour);
                    double total = rate2 * hour2;
                    String answer = df.format(total);
                    double ot = hour2 - 40;
                   //String answer = Double.toString(total);
                   if(ot <= 0){            
                       payLabel.setText("Pay:$" + answer);
                    }
                   
                 // If hours is more than 40 add regular pay to Time and half 
                   else if(ot > 0){
                    total = (40 * rate2) + (rate2 * 1.5 * ot); 
                    answer = Double.toString(total);
                    payLabel.setText("Pay:$" + answer);
                           
                   }
                } 
               
                
                //Volunteer
                else if (empType == "Volunteer") {
                    payLabel.setText("Pay:$0.00");
                }
            }
        });
        
        //initilize the panel
        panel = new JPanel();

        //add all other elements to the panel
        panel.add(empCombo);
        panel.add(hourLabel);
        panel.add(hourText);
        panel.add(rateLabel);
        panel.add(rateText);
        panel.add(clickButton);
        panel.add(payLabel);
        
        //Add the panel to the frame and select color
        add(panel);
        panel.setBackground(Color.CYAN);
        
        //Add action listeners to the button and combo box
        empCombo.addActionListener(this);
        clickButton.addActionListener(this);
        
        //visibility to true
        setVisible(true);
    }

    //Action listener for Combo Box
    public void actionPerformed(ActionEvent e) {
        //variables
        String empType;
        empType = (String) empCombo.getSelectedItem();

        //Check Combo Box Option
        //Salaried
        if (empType.equals("Salaried")) {
            hourText.setText("");
            rateText.setText("");
            hourText.setEditable(false);
            rateText.setEditable(false);
        } 
        
        //Hourly
        else if (empType.equals("Hourly")) {
            //hourText.setText("0");
            //rateText.setText("0.0");
            hourText.setEditable(true);
            rateText.setEditable(true);
         
        } 
        
        //Volunteer
        else if (empType.equals("Volunteer")) {
            hourText.setText("");
            rateText.setText("");
            hourText.setEditable(false);
            rateText.setEditable(false);
        } 
        
        //Other cASES   
        else {
            JOptionPane.showMessageDialog(this, "Please select employee type");
        }
    }

    public static void main(String[] args) {

        //Create instance of GUI class
        gui pay = new gui();

    }
}
