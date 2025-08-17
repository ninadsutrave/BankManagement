package com.bankmanagement.entity.user;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class UserDTO {
  private Integer id;
  private String username;
  private String hashedPassword;
  private UserType type;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;
}
