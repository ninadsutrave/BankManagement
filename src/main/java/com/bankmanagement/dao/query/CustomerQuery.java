package com.bankmanagement.dao.query;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerQuery {
  public static final String GET_CUSTOMER_BY_USERNAME = "SELECT * FROM customer WHERE username = ?";
  public static final String INSERT_CUSTOMER = "INSERT INTO customer(name, date_of_birth, email_id, phone_number) VALUES (?, ?, ?, ?)";
}
