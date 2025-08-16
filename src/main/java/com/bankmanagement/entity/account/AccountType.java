package com.bankmanagement.entity.account;

import lombok.Getter;

@Getter
public enum AccountType {
  SAVINGS("SAVINGS"),
  CURRENT("CURRENT"),
  FIXED_DEPOSIT("FIXED_DEPOSIT"),
  RECURRING_DEPOSIT("RECURRING_DEPOSIT"),
  SALARY("SALARY"),
  JOINT("JOINT");

  private final String value;

  AccountType(String value) { this.value = value; }

}
