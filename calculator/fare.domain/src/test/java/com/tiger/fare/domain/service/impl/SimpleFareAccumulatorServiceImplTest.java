package com.tiger.fare.domain.service.impl;

import com.tiger.fare.domain.Date;
import com.tiger.fare.domain.DateTime;
import com.tiger.fare.domain.Fare;
import com.tiger.fare.domain.Fare.Builder;
import com.tiger.fare.domain.FareDetail;
import com.tiger.fare.domain.Journey;
import com.tiger.fare.domain.ZoneTravelDetail;
import com.tiger.fare.domain.service.FareAccumulatorService;
import com.tiger.fare.domain.service.impl.SimpleFareAccumulatorServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("SimpleFareAccumulatorServiceImpl test cases")
public class SimpleFareAccumulatorServiceImplTest {
  private FareAccumulatorService fareAccumulatorService;

  @BeforeEach
  void setUp() {
    this.fareAccumulatorService = new SimpleFareAccumulatorServiceImpl();
  }

  @DisplayName("Accumulate Fare details")
  @org.junit.jupiter.api.Test
  void accumulateValidData() {
    List<FareDetail> fareDetails = new ArrayList<>();
    fareDetails.add(
        new FareDetail.Builder()
            .withFare(new Builder().withCost(120).build())
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
            .build());
    fareDetails.add(
        new FareDetail.Builder()
            .withFare(new Builder().withCost(300).build())
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
            .build());
    Fare accumulatedFare = fareAccumulatorService.accumulate(fareDetails);

    Assertions.assertEquals(420, accumulatedFare.cost());
  }
}
