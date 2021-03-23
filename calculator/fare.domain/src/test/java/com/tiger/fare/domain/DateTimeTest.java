package com.tiger.fare.domain;

import com.tiger.fare.domain.DateTime.Builder;
import com.tiger.fare.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DateTime test cases")
public class DateTimeTest {

  @DisplayName("Valid DateTime")
  @Test
  void validDateTime() {

    DateTime dateTime = new Builder()
        .withDate(new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
        .withHour(2)
        .withMinute(30)
        .build();

    Assertions.assertEquals(dateTime.date().localDate().getDayOfMonth(),1);
    Assertions.assertEquals(dateTime.date().localDate().getMonthValue(),2);
    Assertions.assertEquals(dateTime.date().localDate().getYear(),2021);
    Assertions.assertEquals(dateTime.dateTime().toLocalTime().getHour(),2);
    Assertions.assertEquals(dateTime.dateTime().toLocalTime().getMinute(),30);
  }

  @DisplayName("DateTime with invalid hour")
  @Test
  void dateTimeWithNegativeHour() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () ->
            new DateTime.Builder()
                .withDate(new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
                .withHour(-2)
                .withMinute(30)
                .build());
  }

  @DisplayName("DateTime with invalid minute")
  @Test
  void dateTimeWithNegativeMinute() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () ->
            new DateTime.Builder()
                .withDate(new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
                .withHour(2)
                .withMinute(-30)
                .build());
  }

  @DisplayName("Date with invalid date")
  @Test
  void dateTimeWithInvalidDate() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () ->
            new DateTime.Builder()
                .withDate(new Date.Builder().withDay(-1).withMonth(2).withYear(2021).build())
                .withHour(2)
                .withMinute(30)
                .build());
  }
}
