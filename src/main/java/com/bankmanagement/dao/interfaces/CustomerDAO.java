package com.bankmanagement.dao.interfaces;

import com.bankmanagement.entity.customer.CustomerDTO;

import java.util.Optional;

public interface CustomerDAO {

  Optional<CustomerDTO> getCustomerByUsername(String username);

  Integer createCustomer(CustomerDTO customer);

}
