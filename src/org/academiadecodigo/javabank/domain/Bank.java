package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Set;

/**
 * The bank entity
 */
public class Bank {

    private AccountManager accountManager;
    private Set<Customer> customers = new HashSet<>();
    private boolean validAccountFound;

    /**
     * Creates a new instance of Bank and initializes it with the given account manager
     *
     * @param accountManager the account manager
     */
    public Bank(AccountManager accountManager) {
        this.accountManager = accountManager; }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     * @see Customer#setAccountManager(AccountManager)
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setAccountManager(accountManager);
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */
    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers) {
            balance += customer.getBalance();
        }

        return balance;
    }

    public boolean isValidAccountFound(){
        return validAccountFound;
    }

    public void getCustomerByAccountNum(int accountNum){
        for(Customer customer : customers){
            if(customer.getAccountNum() == accountNum){
                System.out.println("Welcome " + customer.getName() + ". Thank you for choosing JavaBank.");
                validAccountFound = true;
                return;
            }
        }
        System.out.println("That is not a valid account number.");
    }

    public Customer iterateCustomers(int accountNum){
        for(Customer customer : customers){
            if(customer.getAccountNum() == accountNum){
                return customer;
            }
        }
        System.out.println("no customers");
        return null;
    }

}
