package com.bankmanagement.service;

import com.bankmanagement.dao.implementation.CustomerDAOImpl;
import com.bankmanagement.dao.interfaces.CustomerDAO;
import com.bankmanagement.entity.customer.CustomerDTO;
import com.bankmanagement.util.InputUtil;
import com.bankmanagement.util.SessionUtil;

import java.sql.Date;

public class CustomerService {

  CustomerDAO customerDAOImpl;

  public CustomerService() {
    customerDAOImpl = new CustomerDAOImpl();
  }

  public void register() {

    if(isCustomerRegistered(SessionUtil.customerId)) {
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

    Integer generatedId = customerDAOImpl
      .createCustomer(customer)
      .orElse(null);

    if(generatedId == null) {
      System.err.println("We're facing some issues registering your details! Please try again! 😓");
      return;
    }

    SessionUtil.customerId = generatedId;
    System.out.println("You're registered! Your Customer ID: " + generatedId);

  }

  private boolean isFourDigit(int pin) {
    return (pin >= 1000 && pin <= 9999);
  }

  private boolean isCustomerRegistered(Integer customerId) {

    CustomerDTO customerDTO = customerDAOImpl
      .getCustomerById(customerId)
      .orElse(null);

    return (customerDTO != null);

  }

}
