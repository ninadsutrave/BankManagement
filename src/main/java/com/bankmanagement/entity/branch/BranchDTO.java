package com.bankmanagement.entity.branch;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class BranchDTO {
  private Integer id;
  private String name;
  private String address;
  private String city;
  private String state;
  private Integer zipcode;
  private String phoneNumber;
  private Timestamp createdAt;
  private Timestamp recUpdatedAt;

  public void print() {
    System.out.println("  ID           : " + id);
    System.out.println("  Name         : " + name);
    System.out.println("  Address      : " + address);
    System.out.println("  City         : " + city);
    System.out.println("  State        : " + state);
    System.out.println("  Zipcode      : " + zipcode);
    System.out.println("  Phone Number : " + phoneNumber);
    System.out.println("  CreatedAt    : " + createdAt);
    System.out.println("  RecUpdatedAt : " + recUpdatedAt);
  }
}
