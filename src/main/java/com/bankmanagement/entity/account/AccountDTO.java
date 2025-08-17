package com.bankmanagement.entity.account;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class AccountDTO {
  private Long number;
  private Integer customerId;
  private String hashedPin;
  private AccountType type;
  private Integer branchId;
  private Double balance;
  private AccountStatus status;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;
}
