package com.bankmanagement.dao.query;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserQuery {
  public static final String GET_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
  public static final String INSERT_USER_CREDENTIALS = "INSERT INTO user (username, hashed_password, salt, type) VALUES (?, ?, ?, ?)";
  public static final String CREATE_USER_ACCOUNT = "INSERT INTO user (name, date_of_birth, email_id, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
}
