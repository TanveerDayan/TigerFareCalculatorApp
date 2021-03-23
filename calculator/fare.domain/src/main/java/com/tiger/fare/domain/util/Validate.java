package com.tiger.fare.domain.util;

import com.tiger.fare.domain.exception.InvalidArgumentException;
import java.util.Objects;

public class Validate {

  public static void notNull(Object object, String message) {
    if (Objects.isNull(object)) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void notZeroNonNegative(int value, String message) {
    if (value <= 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void nonNegative(int value, String message) {
    if (value < 0) {
      throw new InvalidArgumentException(message);
    }
  }

  public static void isNumber(String value, String message) {
    try {
      Integer.valueOf(value);
    } catch (NumberFormatException exception) {
      throw new InvalidArgumentException(message);
    }
  }
}
