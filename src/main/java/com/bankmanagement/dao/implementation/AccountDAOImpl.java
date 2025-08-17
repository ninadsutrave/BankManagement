package com.bankmanagement.dao.implementation;

import com.bankmanagement.dao.interfaces.AccountDAO;
import com.bankmanagement.error.DatabaseErrorMapper;
import lombok.extern.slf4j.Slf4j;

import com.bankmanagement.entity.account.AccountDTO;
import com.bankmanagement.dao.query.AccountQuery;
import com.bankmanagement.dao.database.DatabaseConnectionManager;
import com.bankmanagement.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class AccountDAOImpl implements AccountDAO {

  private static final DatabaseConfig databaseConfig = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(databaseConfig);

  @Override
  public boolean createAccount(AccountDTO account) {
    try (Connection connection = connectionManager.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(AccountQuery.CREATE_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setLong(1, account.getNumber());
      preparedStatement.setInt(2, account.getCustomerId());
      preparedStatement.setString(3, account.getType().getValue());
      preparedStatement.setInt(4, account.getBranchId());
      preparedStatement.setDouble(5, account.getBalance());
      preparedStatement.setString(6, account.getStatus().getValue());
      preparedStatement.setTimestamp(7, account.getCreatedAt());
      preparedStatement.setTimestamp(8, account.getRecUpdatedAt());
      int updatedRows = preparedStatement.executeUpdate();

      if (updatedRows > 0) {
        System.out.println("Congrats on creating a new Account! Account Number: " + account.getNumber());
        return true;
      } else {
        System.err.println("Account creation failed for customer id: " + account.getCustomerId());
        return false;
      }

    } catch (SQLException e) {
      System.err.println("An error occurred while creating account for customer id: " + account.getCustomerId());
      e.printStackTrace();
      throw DatabaseErrorMapper.fromException(e);
    }
  }
}
