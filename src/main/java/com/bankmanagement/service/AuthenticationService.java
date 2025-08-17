package com.bankmanagement.service;

import com.bankmanagement.dao.implementation.UserDAOImpl;
import com.bankmanagement.dao.interfaces.UserDAO;
import com.bankmanagement.entity.user.UserDTO;
import com.bankmanagement.entity.user.UserType;
import com.bankmanagement.error.DatabaseError;
import com.bankmanagement.util.EncryptionUtil;
import com.bankmanagement.util.InputUtil;
import com.bankmanagement.util.SessionUtil;

import java.util.Optional;

public class AuthenticationService {

  UserDAO userDAOImpl;

  public AuthenticationService() {
    userDAOImpl = new UserDAOImpl();
  }

  public void signUp() {
    System.out.println("\nSIGN UP");

    String username = getUsername();
    String password = InputUtil.readPassword("Create a strong password: ");

    String hashedPassword = EncryptionUtil.encrypt(password);

    UserDTO user = UserDTO.builder()
      .username(username)
      .hashedPassword(hashedPassword)
      .type(UserType.CUSTOMER)
      .build();

    try {
      if(userDAOImpl.insertUserCredentials(user)) {
        System.out.println("Sign up successful! 🥳");
      } else {
        System.err.println("Sign up unsuccessful! ☹️");
      }
    } catch (DatabaseError e) {
      System.err.println("Sign up unsuccessful! ☹️");
    }

  }

  public boolean signIn() {
    System.out.println("\nSIGN IN");

    String username = InputUtil.readLine("Enter username: ");
    UserDTO user = null;

    try {
      user = userDAOImpl
        .getUserByUsername(username)
        .orElse(null);
    } catch(DatabaseError e) {
      System.err.println("Database error getting User details!" + e.getError().getErrorMessage());
    }

    if(user == null) {
      System.err.println("Username does not exist!");
      return false;
    }

    String password = InputUtil.readPassword("Enter password: ");

    if(EncryptionUtil.verify(password, user.getHashedPassword())) {
      System.out.println("You're signed in! 🥳");

      SessionUtil.userId = user.getId();
      SessionUtil.username = username;
      SessionUtil.isLoggedIn = true;

      return true;
    } else {
      System.err.println("Password incorrect, please try again! ⚙️");
      return false;
    }

  }

  public void signOut() {
    SessionUtil.userId = null;
    SessionUtil.username = null;
    SessionUtil.isLoggedIn = null;
    System.out.println("See you again! 🥺");
  }

  private String getUsername() {
    String username;
    while(true) {
      username = InputUtil.readLine("Create a username: ");

      try {
        Optional<UserDTO> userDetails = userDAOImpl
          .getUserByUsername(username);

        if (userDetails.isEmpty()) {
          System.out.println("Username is available! ✅");
          break;
        } else {
          System.err.println("Username is taken! Try again!☹️");
        }
      } catch (DatabaseError e) {
        System.err.println("Try again! Getting database errors!");
        break;
      }
    }
    return username;
  }

}
