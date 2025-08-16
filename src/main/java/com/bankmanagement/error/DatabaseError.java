package com.bankmanagement.error;

import lombok.Getter;

@Getter
public class DatabaseError extends RuntimeException {

  private final DatabaseErrorEnum error;

  public DatabaseError(DatabaseErrorEnum error) {
    super(error.getErrorMessage());
    this.error = error;
  }

  public DatabaseError(Throwable throwable, DatabaseErrorEnum error) {
    super(error.getErrorMessage(), throwable);
    this.error = error;
  }

  public DatabaseError(String message, DatabaseErrorEnum error) {
    super(message);
    this.error = error;
  }

}
