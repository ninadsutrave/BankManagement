package com.bankmanagement.dao.mapper;

import com.bankmanagement.entity.user.UserDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@UtilityClass
public class UserMapper {

  public static Optional<UserDTO> mapToUser(ResultSet resultSet) {

    try {

      if(resultSet == null || resultSet.isClosed() || !resultSet.next()) {
        return Optional.empty();
      }

      UserDTO user = UserDTO.builder()
        .id(resultSet.getInt("id"))
        .username(resultSet.getString("username"))
        .hashedPassword(resultSet.getString("hashed_password"))
        .build();

      log.info("User mapped successfully for username: {}", user.getUsername());
      return Optional.of(user);

    } catch (SQLException e) {
      log.error("SQLException occurred while mapping User DTO for Resultset: {}", resultSet, e);
    }

    return Optional.empty();

  }

}
