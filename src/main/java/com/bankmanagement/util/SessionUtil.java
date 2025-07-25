package com.bankmanagement.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class SessionUtil {
  public static Integer userId;
  public static String username;
  public static Boolean isLoggedIn;
  public static Integer customerId; // needed for account creation
}
