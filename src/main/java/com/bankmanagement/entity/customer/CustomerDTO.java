package com.bankmanagement.entity.customer;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Data
public class CustomerDTO {
  private Integer id;
  private String name;
  private Date dateOfBirth;
  private String emailId;
  private String phoneNumber;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;
}
