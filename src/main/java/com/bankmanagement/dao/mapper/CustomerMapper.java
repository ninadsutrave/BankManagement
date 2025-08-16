package com.bankmanagement.dao.mapper;

import com.bankmanagement.entity.customer.CustomerDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@UtilityClass
public class CustomerMapper {

  public static Optional<CustomerDTO> mapToCustomer(ResultSet resultSet) {
    try {

      if(resultSet == null || resultSet.isClosed() || !resultSet.next()) {
        log.error("Customer result set cannot be processed or is empty");
        return Optional.empty();
      }

      return Optional.of(createCustomerDTO(resultSet));

    } catch (SQLException e) {
      log.error("SQLException occurred while mapping to customer DTO for result set: {}", resultSet, e);
      return Optional.empty();
    }
  }

  private static CustomerDTO createCustomerDTO(ResultSet resultSet) throws SQLException {
    return CustomerDTO.builder()
      .id(resultSet.getInt("id"))
      .name(resultSet.getString("name"))
      .name(resultSet.getString("name"))
      .dateOfBirth(resultSet.getDate("date_of_birth"))
      .emailId(resultSet.getString("email_id"))
      .phoneNumber(resultSet.getString("phone_number"))
      .build();
  }

}
