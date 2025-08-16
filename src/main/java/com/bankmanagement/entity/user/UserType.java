package com.bankmanagement.entity.user;

import lombok.Getter;

@Getter
public enum UserType {
  ADMIN("ADMIN"),
  MANAGER("MANAGER"),
  CUSTOMER("CUSTOMER");

  private final String value;

  UserType(String value) { this.value = value; }

}
