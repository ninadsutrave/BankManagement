package com.bankmanagement.error;

import lombok.experimental.UtilityClass;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLTimeoutException;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;

@UtilityClass
public class DatabaseErrorMapper {

  public static DatabaseError fromException(SQLException e) {

    // Check specific exception types first
    if (e instanceof SQLIntegrityConstraintViolationException) {
      return new DatabaseError(e, DatabaseErrorEnum.DUPLICATE_KEY);
    }
    if (e instanceof SQLTimeoutException) {
      return new DatabaseError(e, DatabaseErrorEnum.QUERY_TIMEOUT);
    }
    if (e instanceof SQLTransactionRollbackException) {
      // often caused by deadlock or serialization failures
      return new DatabaseError(e, DatabaseErrorEnum.DEADLOCK);
    }
    if (e instanceof SQLNonTransientConnectionException) {
      return new DatabaseError(e, DatabaseErrorEnum.CONNECTION_FAILED);
    }

    // Check SQLState codes for finer control
    String sqlState = e.getSQLState();
    if (sqlState != null) {
      switch (sqlState) {
        case "40001": // serialization failure, often deadlock
          return new DatabaseError(e, DatabaseErrorEnum.DEADLOCK);
        case "08001": // SQL client unable to establish connection
        case "08006": // connection failure
          return new DatabaseError(e, DatabaseErrorEnum.CONNECTION_FAILED);
        case "23505": // unique violation (Postgres, Oracle, etc.)
          return new DatabaseError(e, DatabaseErrorEnum.DUPLICATE_KEY);
        case "02000": // no data (standard SQLState)
          return new DatabaseError(e, DatabaseErrorEnum.RECORD_NOT_FOUND);
        default:
          break; // fall through to generic mapping
      }
    }

    // If nothing matches, fallback to generic
    return new DatabaseError(e, DatabaseErrorEnum.DATABASE_EXCEPTION);
  }
}


