package com.bankmanagement.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DatabaseErrorEnum {

  CONNECTION_FAILED("DB-001", "Failed to connect to the database"),
  DUPLICATE_KEY("DB-003", "Duplicate entry violates unique constraint"),
  RECORD_NOT_FOUND("DB-004", "No record found for the given input"),
  QUERY_TIMEOUT("DB-005", "Database operation timed out"),
  DEADLOCK("DB-006", "Database deadlock occurred"),
  TRANSACTION_FAILED("DB-007", "Database transaction could not be completed"),
  DATABASE_EXCEPTION("DB-999", "Unexpected database error");


  private final String errorCode;
  private final String errorMessage;

}
