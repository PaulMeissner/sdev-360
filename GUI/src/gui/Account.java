/*
Filename: TestProject1.java
Author: Paul Meissner
Date: 7/13/2017
Class: CMIS 242
Purpose: Create a class with a constructor and methods for the action events in
the GUI class.
 */

package gui;

import javax.swing.JOptionPane;


public class Account extends GUI {
    // Fields that hold the initial value of accounts
    private double checkingAccountBalance = 500;
    private double savingsAccountBalance = 500;
    // Class variable for the service fee
    private static final double SERVICECHARGE = 1.5;
    // Class variable used with counter to determine when a service fee is charged
    private static final int COUNT= 3;
    // Class variable that keeps track of number of withdrawls 
    private static int counter = 0;
    
  
    // method for withdrawing from checking account that checks for sufficient funds
    public void withdrawFromChecking(double amount) throws InsufficientFunds {
                            
        if(COUNT == counter) {
           checkingAccountBalance = checkingAccountBalance - SERVICECHARGE; 
           serviceCharge();
           counter = 0;
        } 
        
        if (amount <= checkingAccountBalance) {
            checkingAccountBalance = checkingAccountBalance - amount;
            counter ++;
        } else {
            double needs = amount - checkingAccountBalance - SERVICECHARGE;
            throw new InsufficientFunds(needs);
        }       
    }
    // method that takes the amount the user inputs and adds it to the checking account balance
    public void depositIntoChecking(double amount) {
        
        
        checkingAccountBalance = checkingAccountBalance + amount;
            
    }
    // method that takes the amount the user inputs and adds it to the checking account balance
    public void transferToChecking(double amount)  {
        
        checkingAccountBalance = checkingAccountBalance + amount;
    }
    // method that takes the amount the user types from the savings account and 
    //places it in the checking account
    public void transferFromSavings(double amount) throws InsufficientFunds{
        
        if (amount <= savingsAccountBalance) {
        savingsAccountBalance = savingsAccountBalance - amount;
        }  else {
            double needs = amount - savingsAccountBalance;
            throw new InsufficientFunds(needs);
        }
    }
    //method that gives the current balance of the checking account 
    public double getCheckingAccountBalance() {
        return checkingAccountBalance;
    }

    // method for withdrawing from savings account that checks for sufficient funds
    public void withdrawFromSavings(double amount) throws InsufficientFunds {
        
        if(COUNT == counter) {
           savingsAccountBalance = savingsAccountBalance - SERVICECHARGE; 
           serviceCharge();
           counter = 0;
        } 
        if (amount <= savingsAccountBalance) {
            savingsAccountBalance = savingsAccountBalance - amount;
            counter ++;
        } else {
            double needs = amount - savingsAccountBalance;
            throw new InsufficientFunds(needs);
        }
    }
    // method that takes the amount the user inputs and adds it to the savings account balance
    public void depositIntoSavings(double amount) {
        
        savingsAccountBalance = savingsAccountBalance + amount;
          
    }
    // method that takes the amount the user inputs and adds it to the savings account balance
    public void transferToSavings(double amount)  {
        
        savingsAccountBalance = savingsAccountBalance + amount; 
    }
    // method that takes the amount the user types from the checking account and 
    //places it in the savings account
    public void transferFromChecking(double amount) throws InsufficientFunds {
        
        if (amount <= checkingAccountBalance) {
        checkingAccountBalance = checkingAccountBalance - amount;
        } else {
            double needs = amount - savingsAccountBalance;
            throw new InsufficientFunds(needs);
        }            
    }
    //method that gives the current balance of the savings account 
     public double getSavingsAccountBalance() {
        return savingsAccountBalance;
    }
    // method that displays a message alerting the user of a service charge
    public void serviceCharge() {
        
        JOptionPane.showMessageDialog(null, 
                "Your account has been charged a fee of $1.50!", "Service Charge", 
                JOptionPane.ERROR_MESSAGE);
     
    }
    
}
