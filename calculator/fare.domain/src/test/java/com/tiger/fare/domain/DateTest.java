package com.tiger.fare.domain;

import com.tiger.fare.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Date test cases")
public class DateTest {

  @DisplayName("Valid Date")
  @Test
  void validDate() {
    Date date1 = new Date.Builder().withDay(1).withMonth(2).withYear(2021).build();
    Assertions.assertEquals(date1.localDate().getDayOfMonth(), 1);
    Assertions.assertEquals(date1.localDate().getMonthValue(), 2);
    Assertions.assertEquals(date1.localDate().getYear(), 2021);
  }

  @DisplayName("Date with invalid day")
  @Test
  void dateWithNegativeDay() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () -> new Date.Builder().withDay(-1).withMonth(2).withYear(2021).build());
  }

  @DisplayName("Date with invalid month")
  @Test
  void dateWithNegativeMonth() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () -> new Date.Builder().withDay(1).withMonth(-2).withYear(2021).build());
  }

  @DisplayName("Date with invalid year")
  @Test
  void dateWithNegativeYear() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () -> new Date.Builder().withDay(1).withMonth(2).withYear(-2021).build());
  }
}
