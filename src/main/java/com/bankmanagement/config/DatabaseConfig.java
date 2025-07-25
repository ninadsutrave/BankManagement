package com.bankmanagement.config;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Getter
public class DatabaseConfig {
  private String url;
  private String user;
  private String password;

  public DatabaseConfig() {
    try (FileInputStream input =  new FileInputStream("src/main/resources/config/mysql/mysql-default.conf")) {
      Properties properties = new Properties();
      properties.load(input);

      this.url = properties.getProperty("db.url");
      this.user = properties.getProperty("db.user");
      this.password = properties.getProperty("db.password");
    } catch(IOException e) {
      System.err.println("Failed to load database configuration: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
