package com.bankmanagement.service;

import com.bankmanagement.dao.implementation.AccountDAOImpl;
import com.bankmanagement.dao.implementation.BranchDAOImpl;
import com.bankmanagement.dao.implementation.CustomerDAOImpl;
import com.bankmanagement.dao.interfaces.AccountDAO;
import com.bankmanagement.dao.interfaces.BranchDAO;
import com.bankmanagement.dao.interfaces.CustomerDAO;
import com.bankmanagement.entity.account.AccountDTO;
import com.bankmanagement.entity.account.AccountType;
import com.bankmanagement.entity.account.AccountStatus;
import com.bankmanagement.entity.branch.BranchDTO;
import com.bankmanagement.error.DatabaseError;
import com.bankmanagement.util.CommonUtil;
import com.bankmanagement.util.EncryptionUtil;
import com.bankmanagement.util.InputUtil;
import com.bankmanagement.util.SessionUtil;

import java.util.List;

import static com.bankmanagement.constants.Constant.MINIMUM_ACCOUNT_BALANCE;
import static com.bankmanagement.util.CommonUtil.isValidPin;

public class AccountService {

  BranchDAO branchDAOImpl;
  AccountDAO accountDAOImpl;
  CustomerDAO customerDAOImpl;

  public AccountService() {
    branchDAOImpl = new BranchDAOImpl();
    accountDAOImpl = new AccountDAOImpl();
    customerDAOImpl = new CustomerDAOImpl();
  }

  public void createAccount() {

    System.out.println("CREATE ACCOUNT");
    List<BranchDTO> branchList;

    try {
      branchList = branchDAOImpl
        .getAllBranches()
        .orElse(List.of());
    } catch (DatabaseError e) {
      System.err.println("Database error while getting branch list");
      return;
    }

    if(branchList.isEmpty()) {
      System.err.println("Error getting branch list!");
      return;
    }

    for(BranchDTO branch: branchList) {
      System.out.println(branch.getId() + ". " + branch.getName());
    }

    int branchId = InputUtil.readInt("Please enter branch id: ");
    AccountType type = InputUtil.readEnum("Account type (SAVINGS / CURRENT): ", AccountType.class);
    long accountNumber = CommonUtil.generateRandomAccountNumber();

    String pin;
    while(true) {
      pin = InputUtil.readPassword("Create a 4 digit pin: ");
      if(!isValidPin(pin)) {
        System.err.println("Please create a valid pin!");
      } else {
        System.out.println("Your pin is set!");
        break;
      }
    }

    byte[] salt = EncryptionUtil.generateSalt(16);
    String hashedPin = EncryptionUtil.encrypt(pin, salt);

    AccountDTO account = AccountDTO.builder()
      .number(accountNumber)
      .customerId(SessionUtil.customerId)
      .type(type)
      .branchId(branchId)
      .hashedPin(hashedPin)
      .balance(MINIMUM_ACCOUNT_BALANCE)
      .status(AccountStatus.ACTIVE)
      .build();

    try {
      accountDAOImpl.createAccount(account);
    } catch (DatabaseError e) {
      System.err.println("Database error while creating account");
    }
  }
}
