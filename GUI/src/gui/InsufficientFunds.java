/*
Filename: TestProject1.java
Author: Paul Meissner
Date: 7/13/2017
Class: CMIS 242
Purpose: Create a checked exception that will make sure there are sufficient funds
for transactions.
 */

package gui;

import javax.swing.JOptionPane;


public class InsufficientFunds extends Exception {
    
    public double amount;
    // constructor that displays a JOptionPane if there are insufficient funds
    public InsufficientFunds(double amount) { 
       this.amount = amount; 
        JOptionPane.showMessageDialog(null,
               "Insufficient Funds!", "Insufficient Funds",  
               JOptionPane.ERROR_MESSAGE);
    }
    
    
}
