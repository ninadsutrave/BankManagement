package com.bankmanagement.service;

import com.bankmanagement.dao.implementation.UserDAOImpl;
import com.bankmanagement.dao.interfaces.UserDAO;
import com.bankmanagement.entity.user.UserDTO;
import com.bankmanagement.entity.user.UserType;
import com.bankmanagement.util.EncryptionUtil;
import com.bankmanagement.util.InputUtil;
import com.bankmanagement.util.SessionUtil;

public class AuthenticationService {

  UserDAO userDAOImpl;

  public AuthenticationService() {  userDAOImpl = new UserDAOImpl(); }

  public void signUp() {
    System.out.println("\nSIGN UP");

    String username;
    while(true) {
      username = InputUtil.readLine("Create a username: ");

      UserDTO userDetails = userDAOImpl
        .getUserByUsername(username)
        .orElse(null);

      if(userDetails == null) {
        System.out.println("Username is available! ✅");
        break;
      } else {
        System.err.println("Username is taken! Try again!☹️");
      }
    }

    String password = InputUtil.readPassword("Create a strong password: ");

    byte[] salt = EncryptionUtil.generateSalt(16);
    String hashedPassword = EncryptionUtil.encrypt(password, salt);

    UserDTO user = UserDTO.builder()
      .username(username)
      .hashedPassword(hashedPassword)
      .salt(new String(salt))
      .type(UserType.CUSTOMER)
      .build();

    if(userDAOImpl.insertUserCredentials(user)) {
      System.out.println("Sign up successful! 🥳");
    } else {
      System.err.println("Sign up unsuccessful! ☹️");
    }

  }

  public boolean signIn() {
    System.out.println("\nSIGN IN");

    String username = InputUtil.readLine("Enter username: ");

    UserDTO user = userDAOImpl
      .getUserByUsername(username)
      .orElse(null);

    if(user == null) {
      System.err.println("No user registered against username: " + username);
      return false;
    }

    String password = InputUtil.readPassword("Enter password: ");

    if(EncryptionUtil.verify(password, user.getHashedPassword())) {
      System.out.println("You're signed in! 🥳");

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
  }

}
