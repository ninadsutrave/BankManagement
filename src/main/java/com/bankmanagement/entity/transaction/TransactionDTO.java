package com.bankmanagement.entity.transaction;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class TransactionDTO {
  private Integer id;
  private Integer sourceAccountId;
  private Integer targetAccountId;
  private TransactionType type;
  private Double amount;
  private TransactionStatus status;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;
}
