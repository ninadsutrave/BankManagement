package com.bankmanagement.dao.query;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerQuery {
  public static final String GET_CUSTOMER_BY_USERNAME = "SELECT * FROM customer WHERE id = ?";
  public static final String INSERT_CUSTOMER = "INSERT INTO customer(user_id, name, date_of_birth, email_id, phone_number) VALUES (?, ?, ?, ?, ?)";
  public static final String UPDATE_CUSTOMER_HAS_BANK_ACCOUNT = "UPDATE customer SET has_bank_account = TRUE WHERE id = ?";
}
