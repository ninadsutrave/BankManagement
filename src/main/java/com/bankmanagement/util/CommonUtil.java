package com.bankmanagement.util;

import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.regex.Pattern;

@UtilityClass
public class CommonUtil {

  private static final Long ACCOUNT_NUMBER_PREFIX = 1_0000_0000_0000L;
  private static final Integer PIN_LENGTH = 4;
  private static final Random RANDOM_NUMBER = new Random();

  // Simple email regex (RFC 5322 simplified)
  private static final Pattern EMAIL_PATTERN =
    Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

  // Phone regex: allows digits, +, - and length between 5 and 20
  private static final Pattern PHONE_PATTERN =
    Pattern.compile("^[0-9+-]{5,20}$");

  public static Long generateRandomAccountNumber() {
    long randomPart = RANDOM_NUMBER.nextLong() % ACCOUNT_NUMBER_PREFIX;
    return ACCOUNT_NUMBER_PREFIX + randomPart;
  }

  public static boolean isValidPin(String pin) {
    if (pin == null || pin.length() != PIN_LENGTH) return false;
    try {
      Long.parseLong(pin);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isValidEmail(String email) {
    return email != null && EMAIL_PATTERN.matcher(email).matches();
  }

  public static boolean isValidPhoneNumber(String phone) {
    return phone != null && PHONE_PATTERN.matcher(phone).matches();
  }

}
