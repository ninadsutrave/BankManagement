package com.bankmanagement.dao.implementation;

import com.bankmanagement.config.DatabaseConfig;
import com.bankmanagement.dao.database.DatabaseConnectionManager;
import com.bankmanagement.dao.interfaces.BranchDAO;
import com.bankmanagement.entity.branch.BranchDTO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.bankmanagement.dao.mapper.BranchMapper.mapToBranchList;
import static com.bankmanagement.dao.query.BranchQuery.GET_ALL_BRANCHES;

@Slf4j
public class BranchDAOImpl implements BranchDAO {

  private static final DatabaseConfig databaseConfig = new DatabaseConfig();
  private static final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(databaseConfig);

  @Override
  public Optional<List<BranchDTO>> getAllBranches() {

    try(Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BRANCHES)) {

      try(ResultSet resultSet = preparedStatement.executeQuery()) {
        return mapToBranchList(resultSet);
      }

    } catch (SQLException e) {
      log.error("SQLException occurred while getting all branches", e);
    }

    return Optional.empty();

  }


}
