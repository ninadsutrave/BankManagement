package com.bankmanagement.dao.query;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountQuery {
  public static final String CREATE_ACCOUNT = "INSERT INTO account (number, customer_id, type, branch_id, balance, status, created_at, rec_updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
  public static final String GET_MAX_ACCOUNT_NUMBER = "SELECT MAX(number) FROM account";
}
