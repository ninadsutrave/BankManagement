package com.bankmanagement.manager;

import com.bankmanagement.service.AccountService;
import com.bankmanagement.service.AuthenticationService;
import com.bankmanagement.service.CustomerService;
import com.bankmanagement.service.TransactionService;
import com.bankmanagement.util.InputUtil;
import com.bankmanagement.util.SessionUtil;

public class MenuManager {

  CustomerService customerService;
  AuthenticationService authenticationService;
  AccountService accountService;
  TransactionService transactionService;

  public MenuManager() {
    customerService = new CustomerService();
    authenticationService = new AuthenticationService();
    accountService = new AccountService();
    transactionService = new TransactionService();
  }

  public void showWelcomeScreen() {
    while(true) {
      System.out.println("\nBANK MANAGEMENT SYSTEM");

      System.out.println("1. Sign up");
      System.out.println("2. Sign in");
      System.out.println("3. Exit");

      int choice = InputUtil.readInt("Enter your choice: ");

      switch(choice) {
        case 1: authenticationService.signUp();
          break;
        case 2: if (authenticationService.signIn()) {
            showBankHomePage();
          }
          break;
        case 3: return;
        default: System.err.println("Invalid choice entered!");
      }
    }

  }

  public void showBankHomePage() {

    while(true) {
      System.out.println("\nBANK MANAGEMENT SYSTEM");
      System.out.println("Welcome " + SessionUtil.username + "!");

      System.out.println("1. Register User");
      System.out.println("2. Create Account");
      System.out.println("3. Deposit");
      System.out.println("4. Withdraw");
      System.out.println("5. Transfer");
      System.out.println("6. Sign out");

      int choice = InputUtil.readInt("Enter your choice: ");

      switch (choice) {
        case 1: customerService.register();
          break;
        case 2: accountService.createAccount();
          break;
        case 3: transactionService.deposit();
          break;
        case 4: transactionService.withdraw();
          break;
        case 5: transactionService.transfer();
          break;
        case 6: authenticationService.signOut();
          return;
        default: System.err.println("Invalid choice entered!");
      }
    }

  }
}
