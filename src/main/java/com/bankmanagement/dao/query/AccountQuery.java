package com.bankmanagement.dao.query;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountQuery {
  public static final String CREATE_ACCOUNT = "INSERT INTO account (number, user_id, type, branch_id, balance, status, ) VALUES ()";
}
