package com.bankmanagement.entity.transaction;

import lombok.Getter;

@Getter
public enum TransactionStatus {
  INITIATED("INITIATED"),
  PENDING("PENDING"),
  SUCCESS("SUCCESS"),
  FAILED("FAILED");

  private final String value;

  TransactionStatus(String value) {  this.value = value; }

}
