package com.bankmanagement.service;

import com.bankmanagement.dao.implementation.AccountDAOImpl;
import com.bankmanagement.dao.implementation.BranchDAOImpl;
import com.bankmanagement.dao.interfaces.AccountDAO;
import com.bankmanagement.dao.interfaces.BranchDAO;
import com.bankmanagement.entity.account.AccountDTO;
import com.bankmanagement.entity.account.AccountType;
import com.bankmanagement.entity.branch.BranchDTO;
import com.bankmanagement.util.InputUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.bankmanagement.util.SessionUtil.userId;

@Slf4j
public class AccountService {

  BranchDAO branchDAOImpl;
  AccountDAO accountDAOImpl;

  public AccountService() {
    branchDAOImpl = new BranchDAOImpl();
    accountDAOImpl = new AccountDAOImpl();
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

    System.out.println("Please enter branch id: ");

    AccountType type = InputUtil.readEnum("Account type (SAVINGS / CURRENT): ", AccountType.class);
    Long accountNumber = generateAccountNumber();

  }

  private Long generateAccountNumber() {
    return 0L;
  }

}
