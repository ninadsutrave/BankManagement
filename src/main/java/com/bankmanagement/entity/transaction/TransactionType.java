package com.bankmanagement.entity.transaction;

import lombok.Getter;

@Getter
public enum TransactionType {
  WITHDRAW("WITHDRAWAL"),
  DEPOSIT("DEPOSIT"),
  TRANSFER("TRANSFER");

  private final String value;

  TransactionType(String value) { this.value = value; }

}
