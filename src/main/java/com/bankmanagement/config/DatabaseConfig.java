package com.bankmanagement.config;

import lombok.Getter;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class DatabaseConfig {
  private final String url;
  private final String user;
  private final String password;

  public DatabaseConfig() {
    Properties properties = new Properties();

    try (InputStream input = getClass().getClassLoader()
      .getResourceAsStream("config/mysql/mysql-default.conf")) {
      if (input == null) {
        throw new IllegalStateException("Config file not found in resources");
      }
      properties.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load database configuration", e);
    }

    this.url = requireProperty(properties, "db.url");
    this.user = requireProperty(properties, "db.user");
    this.password = requireProperty(properties, "db.password");
  }

  private String requireProperty(Properties props, String key) {
    String value = props.getProperty(key);
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Missing required config: " + key);
    }
    return value;
  }
}
