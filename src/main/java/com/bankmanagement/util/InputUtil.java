package com.bankmanagement.util;

import lombok.experimental.UtilityClass;
import java.io.Console;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

@UtilityClass
public final class InputUtil {

  private static final Console console = System.console();
  private static final Scanner scanner = new Scanner(System.in);

  public static int readInt(String prompt) {
    while(true) {
      try {
        System.out.print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine();

        return input;
      } catch (IllegalArgumentException | InputMismatchException e) {
        System.err.println("Invalid integer. Please try again!");
      }
    }
  }

  public static String readLine(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  public static Date readDate(String prompt) {
    while(true) {
      try {
        System.out.print(prompt);
        String input = scanner.nextLine();

        LocalDate parsedDate = LocalDate.parse(input);
        return Date.valueOf(parsedDate);
      } catch(DateTimeParseException e) {
        System.err.println("Invalid input. Please folow YYYY-MM-DD format!");
      }
    }
  }

  public static String readPassword(String prompt) {

    if (console == null) {
      // console not available in IDEs
      System.out.print(prompt);
      return scanner.nextLine();
    }

    char[] password = console.readPassword(prompt);
    return new String(password);
  }

  public static <E extends Enum<E>> E readEnum(String prompt, Class<E> enumType) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine().trim().toUpperCase();

      try {
        return Enum.valueOf(enumType, input);
      } catch (IllegalArgumentException e) {
        System.err.println("Invalid input. Options are: ");
        for (E constant : enumType.getEnumConstants()) {
          System.err.print(constant.name().charAt(0) + constant.name().substring(1).toLowerCase() + " ");
        }
        System.err.println();
      }
    }
  }

  public static double readDouble(String prompt) {
    while(true) {
      try {
        System.out.print(prompt);
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
      } catch (IllegalArgumentException | InputMismatchException e) {
        System.err.println("Invalid decimal number. Please try again!");
        scanner.nextLine();
      }
    }
  }

}
