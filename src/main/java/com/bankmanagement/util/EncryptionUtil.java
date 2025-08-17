package com.bankmanagement.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public final class EncryptionUtil {

  private static final int BCRYPT_COST_FACTOR = 12;

  public static String encrypt(String password) {
    return BCrypt.withDefaults().hashToString(BCRYPT_COST_FACTOR, password.toCharArray());
  }

  public static boolean verify(String password, String bcryptHash) {
    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHash);
    return result.verified;
  }

}
