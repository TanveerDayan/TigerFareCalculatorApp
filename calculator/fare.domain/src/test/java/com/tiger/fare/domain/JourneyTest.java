package com.tiger.fare.domain;

import com.tiger.fare.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Journey test cases")
public class JourneyTest {

  @DisplayName("Valid Journey")
  @Test
  void validJourney() {
    Journey journey =
        new Journey.Builder()
            .withDateTime(
                new DateTime.Builder()
                    .withDate(new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
                    .withHour(2)
                    .withMinute(30)
                    .build())
            .withZoneTravelDetail(
                new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build())
            .build();
    Assertions.assertEquals(1, journey.dateTime().dateTime().getDayOfMonth());
    Assertions.assertEquals(2, journey.dateTime().dateTime().getMonthValue());
    Assertions.assertEquals(2021, journey.dateTime().dateTime().getYear());
    Assertions.assertEquals(1, journey.zoneTravelDetail().from());
    Assertions.assertEquals(2, journey.zoneTravelDetail().to());
  }

  @DisplayName("Journey with invalid date")
  @Test
  void journeyWithInvalidDate() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () ->
            new Journey.Builder()
                .withDateTime(
                    new DateTime.Builder()
                        .withDate(
                            new Date.Builder().withDay(-1).withMonth(2).withYear(2021).build())
                        .withHour(2)
                        .withMinute(30)
                        .build())
                .withZoneTravelDetail(
                    new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build())
                .build());
  }

  @DisplayName("Journey with invalid ZoneTravelDetail")
  @Test
  void journeyWithInvalidZoneTravelDetail() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () ->
            new Journey.Builder()
                .withDateTime(
                    new DateTime.Builder()
                        .withDate(new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
                        .withHour(2)
                        .withMinute(30)
                        .build())
                .withZoneTravelDetail(
                    new ZoneTravelDetail.Builder().withFromZone(-1).withToZone(2).build())
                .build());
  }
}
