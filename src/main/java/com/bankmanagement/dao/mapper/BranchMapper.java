package com.bankmanagement.dao.mapper;

import com.bankmanagement.entity.branch.BranchDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@UtilityClass
public class BranchMapper {

  public static Optional<List<BranchDTO>> mapToBranchList(ResultSet resultSet) {

    List<BranchDTO> branchList = new ArrayList<>();
    try {

      if(resultSet == null || resultSet.isClosed()) {
        log.error("Branch result set cannot be processed or is empty");
        return Optional.empty();
      }

      while(resultSet.next()) {
        BranchDTO branch = createBranchDTO(resultSet);
        branchList.add(branch);
      }

    } catch(SQLException e) {
      log.error("SQLException occurred while mapping BranchDTO", e);
    }

    return branchList.isEmpty() ? Optional.empty() : Optional.of(branchList);

  }

  private static BranchDTO createBranchDTO(ResultSet resultSet) throws SQLException {
    return BranchDTO.builder()
      .id(resultSet.getInt("id"))
      .name(resultSet.getString("name"))
      .address(resultSet.getString("address"))
      .city(resultSet.getString("city"))
      .state(resultSet.getString("state"))
      .zipcode(resultSet.getInt("zipcode"))
      .phoneNumber(resultSet.getString("phone_number"))
      .build();
  }

}
