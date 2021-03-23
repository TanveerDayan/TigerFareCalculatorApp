package com.tiger.fare.domain;

import com.tiger.fare.domain.Fare.Builder;
import com.tiger.fare.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FareDetail test cases")
public class FareDetailTest {

  @DisplayName("Valid FareDetail")
  @Test
  void validFareDetail() {

    FareDetail fareDetail =
        new FareDetail.Builder()
            .withFare(new Builder().withCost(10).build())
            .withJourney(
                new Journey.Builder()
                    .withDateTime(
                        new DateTime.Builder()
                            .withDate(
                                new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
                            .withHour(2)
                            .withMinute(30)
                            .build())
                    .withZoneTravelDetail(
                        new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build())
                    .build())
            .build();
    Assertions.assertEquals(1, fareDetail.journey().dateTime().dateTime().getDayOfMonth());
    Assertions.assertEquals(2, fareDetail.journey().dateTime().dateTime().getMonthValue());
    Assertions.assertEquals(2021, fareDetail.journey().dateTime().dateTime().getYear());
    Assertions.assertEquals(1, fareDetail.journey().zoneTravelDetail().from());
    Assertions.assertEquals(2, fareDetail.journey().zoneTravelDetail().to());
    Assertions.assertEquals(10, fareDetail.fare().cost());
  }

  @DisplayName("FareDetail with invalid dateTime")
  @Test
  void fareDetailWithInvalidDateTime() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () ->
            new FareDetail.Builder()
                .withFare(new Builder().withCost(10).build())
                .withJourney(
                    new Journey.Builder()
                        .withDateTime(
                            new DateTime.Builder()
                                .withDate(
                                    new Date.Builder()
                                        .withDay(-1)
                                        .withMonth(2)
                                        .withYear(2021)
                                        .build())
                                .withHour(2)
                                .withMinute(30)
                                .build())
                        .withZoneTravelDetail(
                            new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build())
                        .build())
                .build());
  }

  @DisplayName("FareDetail with invalid ZoneTravelDetail")
  @Test
  void fareDetailWithInvalidZoneTraveDetail() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () ->
            new FareDetail.Builder()
                .withFare(new Builder().withCost(10).build())
                .withJourney(
                    new Journey.Builder()
                        .withDateTime(
                            new DateTime.Builder()
                                .withDate(
                                    new Date.Builder()
                                        .withDay(1)
                                        .withMonth(2)
                                        .withYear(2021)
                                        .build())
                                .withHour(2)
                                .withMinute(30)
                                .build())
                        .withZoneTravelDetail(
                            new ZoneTravelDetail.Builder().withFromZone(-1).withToZone(2).build())
                        .build())
                .build());
  }

  @DisplayName("FareDetail with invalid Fare")
  @Test
  void fareDetailWithInvalidFare() {
    Assertions.assertThrows(
        InvalidArgumentException.class,
        () ->
            new FareDetail.Builder()
                .withFare(new Builder().withCost(-10).build())
                .withJourney(
                    new Journey.Builder()
                        .withDateTime(
                            new DateTime.Builder()
                                .withDate(
                                    new Date.Builder()
                                        .withDay(1)
                                        .withMonth(2)
                                        .withYear(2021)
                                        .build())
                                .withHour(2)
                                .withMinute(30)
                                .build())
                        .withZoneTravelDetail(
                            new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build())
                        .build())
                .build());
  }
}
