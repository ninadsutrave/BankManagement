package com.bankmanagement.entity.account;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class AccountDTO {
  private Integer number;
  private Integer customerId;
  private AccountType type;
  private Integer branchId;
  private Double balance;
  private AccountStatus status;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;
}
