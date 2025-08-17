package com.bankmanagement.dao.interfaces;

import com.bankmanagement.entity.customer.CustomerDTO;
import com.bankmanagement.error.DatabaseError;

import java.util.Optional;

public interface CustomerDAO {

  Optional<CustomerDTO> getCustomerById(int id);

  Optional<Integer> createCustomer(CustomerDTO customer);

  boolean updateCustomerHasBankAccount(int customerId);
}
