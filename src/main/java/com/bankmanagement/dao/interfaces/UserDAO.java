package com.bankmanagement.dao.interfaces;

import com.bankmanagement.entity.user.UserDTO;

import java.util.Optional;

public interface UserDAO {

  Optional<UserDTO> getUserByUsername(String username);

  boolean insertUserCredentials(UserDTO user);



}
