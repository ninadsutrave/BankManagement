package com.bankmanagement.dao.implementation;

import com.bankmanagement.config.DatabaseConfig;
import com.bankmanagement.dao.database.DatabaseConnectionManager;
import com.bankmanagement.dao.interfaces.UserDAO;
import com.bankmanagement.entity.user.UserDTO;
import com.bankmanagement.error.DatabaseError;
import com.bankmanagement.error.DatabaseErrorMapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.bankmanagement.dao.mapper.UserMapper.mapToUser;
import static com.bankmanagement.dao.query.UserQuery.GET_USER_BY_USERNAME;
import static com.bankmanagement.dao.query.UserQuery.INSERT_USER_CREDENTIALS;

@Slf4j
public class UserDAOImpl implements UserDAO {

  private static final DatabaseConfig config = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);

  @Override
  public Optional<UserDTO> getUserByUsername(String username) {

    try (Connection conn = connectionManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_BY_USERNAME)) {

      preparedStatement.setString(1, username);

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToUser(resultSet);
      }
    } catch (SQLException e) {
      log.error("SQLException occurred while getting user by username: {}", username, e);
      throw DatabaseErrorMapper.fromException(e);
    }

  }

  @Override
  public boolean insertUserCredentials(UserDTO user) {

    try(Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_CREDENTIALS)) {

      preparedStatement.setString(1, user.getUsername());
      preparedStatement.setString(2, user.getHashedPassword());
      preparedStatement.setString(3, String.valueOf(user.getType()));

      int rowsUpdated = preparedStatement.executeUpdate();

      if(rowsUpdated > 0) {
        log.info("User record insertion successful for username: {}", user.getUsername());
        return true;
      } else {
        log.error("User record insertion failed for username: {}", user.getUsername());
        return false;
      }

    } catch(SQLException e) {
      log.error("SQLException in inserting user credentials Up for username: {}, error: ", user.getUsername(), e);
      throw DatabaseErrorMapper.fromException(e);
    }

  }

}
