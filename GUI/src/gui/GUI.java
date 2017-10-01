/*
Filename: TestProject1.java
Author: Paul Meissner
Date: 7/13/2017
Class: CMIS 242
Purpose: Create a GUI ATM that has buttons and a text box that will allow funds
to be moved between accounts through withdrawls, deposits, and transfers. At any
time the user can check the balance of either account. Checked exceptions and warnings
will be built in to the program and displayed by using JOptionPane.
 */

package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;

public class GUI extends JFrame{
    // Formats doubles into currency
    private static NumberFormat cf = NumberFormat.getCurrencyInstance();
    // Buttons to perform account actions
    private JButton withdraw = new JButton("Withdraw");
    private JButton deposit = new JButton("Deposit");
    private JButton transferTo = new JButton("Transfer to");
    private JButton balance = new JButton("Balance");
    // Radio buttons that allow the user to switch between accounts
    private ButtonGroup group;
    private JRadioButton checking, savings; 
    // A text field that the user will use to enter amounts
    private JTextField input = new JTextField(20);
    // Account objects that will hold account balances
    private static Account savingsAccount = new Account();
    private static Account checkingAccount = new Account();
    // GUI constructor
    public GUI() {
        
        super("ATM Machine");
        setSize(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        group = new ButtonGroup();
        checking = new JRadioButton("Checking", true);
        group.add(checking);
        savings = new JRadioButton("Savings", false);
        group.add(savings);
        createPanel();
        createlowerPanel();
        
        
    // Code that allows actions when the withdraw button is clicked    
    withdraw.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
       // Checks for sufficient funds, for valid input, and $20 withdrawl increments
       try {
        if (getInputAmount() > 0 ) {
          if(getInputAmount() %20 == 0) {
           // If statement to determine which account to use
           if (savings.isSelected()) {
               savingsAccount.withdrawFromSavings(getInputAmount());
               JOptionPane.showMessageDialog(null, cf.format(getInputAmount()) +
                                " has been withdrawn from your savings account.");
               
           } else if (checking.isSelected()) {
               checkingAccount.withdrawFromChecking(getInputAmount());
               JOptionPane.showMessageDialog(null, cf.format(getInputAmount()) +
                                " has been withdrawn from your checking account.");
           }
        } else {
            JOptionPane.showMessageDialog(null,
               "Withdrawals must be in increments of $20.", "Invalid Amount", 
               JOptionPane.ERROR_MESSAGE);
          }   
        }
       } catch (InsufficientFunds ex) {}
       
       clearInput();
    }
    });
    // Code that allows actions when the deposit button is clicked  
    deposit.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
         // Checks for sufficient funds, for valid input
        try {
          if (getInputAmount() > 0) {
           // If statement to determine which account to use   
           if (savings.isSelected()) {
               savingsAccount.depositIntoSavings(getInputAmount());
               JOptionPane.showMessageDialog(null, cf.format(getInputAmount()) +
                                " has been deposited into your savings account.");
               
           } else if (checking.isSelected()) {
               checkingAccount.depositIntoChecking(getInputAmount());
               JOptionPane.showMessageDialog(null, cf.format(getInputAmount()) +
                                " has been deposited into your checking account.");
             }
          }
        } catch (NumberFormatException nfe) {
               JOptionPane.showMessageDialog(null,
               "You must enter whole numbers.", "NumberFormatException", 
               JOptionPane.ERROR_MESSAGE);
               
          }
           clearInput();
    }});
    // Code that allows actions when the transfer to button is clicked 
    transferTo.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Checks for sufficient funds, for valid input
        try { 
          if (getInputAmount() > 0) {
           // If statement to determine which account to use   
           if (savings.isSelected()) {
               savingsAccount.transferToSavings(getInputAmount());
               checkingAccount.transferFromChecking(getInputAmount());
               JOptionPane.showMessageDialog(null, cf.format(getInputAmount()) +
                                " has been transfered from your checking acount to your savings account." );
               
           } else if (checking.isSelected()) {
               checkingAccount.transferToChecking(getInputAmount());
               savingsAccount.transferFromSavings(getInputAmount());
               JOptionPane.showMessageDialog(null, cf.format(getInputAmount()) +
                                " has been transfered from your savings account to your checking account.");
           }
          } 
        } catch (NumberFormatException nfe) {
               JOptionPane.showMessageDialog(null,
               "You must enter whole numbers.", "NumberFormatException", 
               JOptionPane.ERROR_MESSAGE);
               
          } catch (InsufficientFunds ex) {}
          
        clearInput();
    }
    });
    // Code that allows actions when the balance button is clicked 
    balance.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
           // If statement to determine which account to use   
           if (savings.isSelected()) {
               JOptionPane.showMessageDialog(null, "Your savings account balance is: " + 
                       cf.format(savingsAccount.getSavingsAccountBalance()));
               
           } else if (checking.isSelected()) {
               JOptionPane.showMessageDialog(null, "Your checking account balance is: " + 
                       cf.format(checkingAccount.getCheckingAccountBalance()));
           }
        
        clearInput();
    }
    });
    }
    // method that takes the user input and parses it to a double and also checks for valid input
    public double getInputAmount() {
        
        try {
            
            return Double.parseDouble(input.getText());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null,
               "You must enter a positive number.", "NumberFormatException", 
               JOptionPane.ERROR_MESSAGE);
            clearInput();
            return 0;
            
        }
    }    
    // method to clear the text field after a button is clicked
    public void clearInput() {
        
        input.setText("");
    }
    // method that creates the upper panel in the GUI
    public void createPanel() {

       JPanel panel = new JPanel();
       GridLayout main = new GridLayout(0,2,10,10);
       panel.setLayout(main);
       
       panel.add(withdraw);
       panel.add(deposit);
       panel.add(transferTo);
       panel.add(balance);
       panel.add(checking);
       panel.add(savings);
       add(panel);
       setVisible(true);
    }   
     // method that creates the lower panel in the GUI
    public void createlowerPanel() {
        
       JPanel lowerPanel = new JPanel();
       FlowLayout flow = new FlowLayout();
       setLayout(flow);
       lowerPanel.add(input);
       add(lowerPanel);
       setVisible(true);
       
    }
    // main method
    public static void main(String[] args) {
        // instantiating a GUI object that runs the program
        GUI atm = new GUI();
       
    }
    
}
