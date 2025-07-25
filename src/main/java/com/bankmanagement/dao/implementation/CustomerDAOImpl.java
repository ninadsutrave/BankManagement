package com.bankmanagement.dao.implementation;

import com.bankmanagement.config.DatabaseConfig;
import com.bankmanagement.dao.database.DatabaseConnectionManager;
import com.bankmanagement.dao.interfaces.CustomerDAO;
import com.bankmanagement.entity.customer.CustomerDTO;
import lombok.extern.slf4j.Slf4j;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static com.bankmanagement.dao.mapper.CustomerMapper.mapToCustomer;
import static com.bankmanagement.dao.query.CustomerQuery.GET_CUSTOMER_BY_USERNAME;
import static com.bankmanagement.dao.query.CustomerQuery.INSERT_CUSTOMER;

@Slf4j
public class CustomerDAOImpl implements CustomerDAO {

  private static final DatabaseConfig databaseConfig = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(databaseConfig);

  @Override
  public Optional<CustomerDTO> getCustomerByUsername(String username) {

    try(Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_USERNAME)) {

      preparedStatement.setString(1, username);

      try(ResultSet resultSet = preparedStatement.executeQuery()){
        return mapToCustomer(resultSet);
      }

    } catch(SQLException e) {
      log.error("SQLException occurred while getting customer details for username: {}", username, e);
      return Optional.empty();
    }

  }

  @Override
  public Integer createCustomer(CustomerDTO customer) {

    try(Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS)) {

      preparedStatement.setString(1, customer.getName());
      preparedStatement.setDate(2, customer.getDateOfBirth());
      preparedStatement.setString(3, customer.getEmailId());
      preparedStatement.setString(4, customer.getPhoneNumber());

      int rowsUpdated = preparedStatement.executeUpdate();

      if(rowsUpdated > 0) {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            log.info("Insertion successful for customer: {}", customer);
            return generatedId;
          } else {
            log.error("Insertion succeeded but no ID returned for customer: {}", customer);
            return null;
          }
        }
      } else {
        log.error("Error inserting customer details for customer: {}", customer);
        return null;
      }

    } catch(SQLException e) {
      log.error("SQLException occurred while inserting customer details for customer: {}", customer, e);
      return null;
    }

  }

}
