package com.bankmanagement.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

import static com.bankmanagement.constants.Constant.ACCOUNT_NUMBER_PREFIX;
import static com.bankmanagement.constants.Constant.PIN_LENGTH;

@UtilityClass
public class CommonUtil {

  private static final Random RANDOM_NUMBER = new Random();

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

}
