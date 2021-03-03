package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class ATM {

    private final String[] menuOptions = {"View Balance", "Make Deposit", "Make Withdrawal", "Open Account", "Quit"};
    private final String[] accountType = {"Checking Account", "Savings Account", "Back"};
    private final Prompt prompt;
    private IntegerInputScanner accountDepositScanner;
    private DoubleInputScanner amountScanner;
    private final AccountManager manager;
    private final Bank bank;
    private int enteredNum = 0;
    private int num;
    private double amount;
    private int menuAnswer;
    private boolean isAccountChosen;

    public ATM() {
        manager = new AccountManager();
        bank = new Bank(manager);
        bank.addCustomer(new Customer("Fabio", 666));
        bank.addCustomer(new Customer("João", 555));
        bank.addCustomer(new Customer("Francisco", 111));
        prompt = new Prompt(System.in, System.out);
        loadMenus();
    }

    public void collectUserName() {
        IntegerInputScanner insertCustomerNumber = new IntegerInputScanner();
        insertCustomerNumber.setMessage("Please enter your customer number:\n");
        enteredNum = prompt.getUserInput(insertCustomerNumber);
        bank.getCustomerByAccountNum(enteredNum);
    }

    public void selectOperation() {
        MenuInputScanner menuScanner = new MenuInputScanner(menuOptions);
        menuScanner.setMessage("What operation would you like to do?");
        menuAnswer = prompt.getUserInput(menuScanner);
        switch (menuAnswer) {
            case 1 -> showBalance();
            case 2 -> makeDeposit();
            case 3 -> makeWithdrawal();
            case 4 -> chooseAccountType();
            case 5 -> {
                System.out.println("Goodbye! Thanks for your money :)");
                System.out.close();
            }
            default -> System.out.println("please choose a valid option.");
        }
    }

    private void makeDeposit() {

        if (manager.accountsAmount() == 0) {
            System.out.println("You must have at least one account to deposit.");
        } else {
            showBalance();
            accountDepositScanner = new IntegerInputScanner();
            amountScanner = new DoubleInputScanner();
            accountDepositScanner.setMessage("What account would you like to make a deposit?\n");
            amountScanner.setMessage("How much would you like to deposit?\n");
            num = prompt.getUserInput(accountDepositScanner);
            amount = prompt.getUserInput(amountScanner);
            manager.deposit(num, amount);
        }
    }

    private void makeWithdrawal() {
        if (manager.accountsAmount() == 0) {
            System.out.println("You must have at least one account to withdraw.");
        } else {
            showBalance();
            accountDepositScanner = new IntegerInputScanner();
            amountScanner = new DoubleInputScanner();
            accountDepositScanner.setMessage("What account would you like to make a withdraw?\n");
            amountScanner.setMessage("How much would you like to withdraw?\n");
            num = prompt.getUserInput(accountDepositScanner);
            amount = prompt.getUserInput(amountScanner);
            manager.withdraw(num, amount);
        }
    }

    private boolean isAccountChosen() {
        return isAccountChosen;
    }

    private void chooseAccountType() {
        isAccountChosen = false;
        while (!isAccountChosen()) {
            MenuInputScanner accountTypeScanner = new MenuInputScanner(accountType);
            accountTypeScanner.setMessage("What kind of account would you like to open?");
            int accountTypeAnswer = prompt.getUserInput(accountTypeScanner);
            switch (accountTypeAnswer) {
                case 1 -> {
                    bank.iterateCustomers(enteredNum).openAccount(AccountType.CHECKING);
                    isAccountChosen = true;
                }
                case 2 -> {
                    bank.iterateCustomers(enteredNum).openAccount(AccountType.SAVINGS);
                    isAccountChosen = true;
                }
                case 3 -> isAccountChosen = true;
                default -> System.out.println("choose a valid option");
            }
        }
    }

    private void getAccountIds(){
        manager.printAccountNums();
    }

    public void showBalance(){
        System.out.println("You have the following accounts:");
        getAccountIds();
        System.out.println("TOTAL: " + bank.iterateCustomers(enteredNum).getBalance());
    }

    public void loadMenus() {
        while (!bank.isValidAccountFound()) { collectUserName(); }
        while (true) { selectOperation(); }
    }

}
