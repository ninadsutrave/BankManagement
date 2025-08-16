package com.bankmanagement.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class CommonUtil {

  private static final Random RANDOM = new Random();

  public static Long generateRandomAccountNumber() {
    long prefix = 1_0000_0000L;
    long randomPart = (long) (RANDOM.nextDouble() * 1_0000_0000L);
    return prefix + randomPart;
  }

}
