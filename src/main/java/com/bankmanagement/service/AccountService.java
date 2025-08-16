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
import com.bankmanagement.util.CommonUtil;
import com.bankmanagement.util.InputUtil;
import com.bankmanagement.util.SessionUtil;

import java.util.List;

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

    List<BranchDTO> branchList = branchDAOImpl
      .getAllBranches()
      .orElse(List.of());

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

    AccountDTO account = AccountDTO.builder()
      .number(accountNumber)
      .customerId(SessionUtil.customerId)
      .type(type)
      .branchId(branchId)
      .balance(0D)
      .status(AccountStatus.ACTIVE)
      .build();

    accountDAOImpl.createAccount(account);
  }
}
