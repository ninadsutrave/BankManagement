package com.bankmanagement.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

@Slf4j
@UtilityClass
public final class EncryptionUtil {

  public static String encrypt(String password, byte[] salt) {
    return Arrays.toString(BCrypt.withDefaults().hash(6, salt, password.getBytes(StandardCharsets.UTF_8)));
  }

  public static boolean verify(String password, String bcryptHash) {
    if(password == null) {
      log.error("Password passed for verification is null!");
      return false;
    }

    BCrypt.Result result =  BCrypt.verifyer().verify(password.toCharArray(), bcryptHash);
    if(result == null) {
      log.error("BCrypt result returned is null!");
      return false;
    }

    log.info("BCrypt result returned is {}", result.verified);
    return result.verified;
  }

  public static byte[] generateSalt(int length) {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[length];
    random.nextBytes(salt);
    return salt;
  }

}
