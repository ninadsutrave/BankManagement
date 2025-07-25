package com.bankmanagement.service;

import com.bankmanagement.dao.implementation.CustomerDAOImpl;
import com.bankmanagement.dao.interfaces.CustomerDAO;
import com.bankmanagement.entity.customer.CustomerDTO;
import com.bankmanagement.util.InputUtil;

import java.sql.Date;

import static com.bankmanagement.util.SessionUtil.username;

public class CustomerService {

  CustomerDAO customerDAOImpl;
  VerificationService verificationService;

  public CustomerService() {
    customerDAOImpl = new CustomerDAOImpl();
    verificationService = new VerificationService();
  }

  public void register() {

    if(isCustomerRegistered(username)) {
      System.out.println("You're already registered! That's great! 😃");
      return;
    }

    System.out.println("REGISTER CUSTOMER");
    System.out.println("Hey! Please help us with your details!");

    String name = InputUtil.readLine("Enter your name: ");
    Date dateOfBirth = InputUtil.readDate("Enter your DOB (YYYY-MM-DD):");
    String emailId = InputUtil.readLine("Enter email ID: ");
    String phoneNumber = InputUtil.readLine("Enter phone number: ");

    CustomerDTO customer = CustomerDTO.builder()
      .name(name)
      .dateOfBirth(dateOfBirth)
      .emailId(emailId)
      .phoneNumber(phoneNumber)
      .build();

    Integer generatedId = customerDAOImpl.createCustomer(customer);

    if(generatedId == null) {
      System.err.println("We're facing some issues registering your details! Please try again! 😓");
      return;
    }

    System.out.println("You're registered! Your Customer ID: " + generatedId);

  }

  private boolean isFourDigit(int pin) {
    return (pin >= 1000 && pin <= 9999);
  }

  private boolean isCustomerRegistered(String username) {

    CustomerDTO customerDTO = customerDAOImpl
      .getCustomerByUsername(username)
      .orElse(null);

    return (customerDTO != null);

  }

}
