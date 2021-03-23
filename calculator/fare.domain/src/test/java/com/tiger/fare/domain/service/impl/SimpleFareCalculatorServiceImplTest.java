package com.tiger.fare.domain.service.impl;

import com.tiger.fare.domain.Date;
import com.tiger.fare.domain.DateTime;
import com.tiger.fare.domain.Fare;
import com.tiger.fare.domain.FareDetail;
import com.tiger.fare.domain.Journey;
import com.tiger.fare.domain.Journey.Builder;
import com.tiger.fare.domain.ZoneTravelDetail;
import com.tiger.fare.domain.service.FareCalculatorService;
import com.tiger.fare.domain.service.config.FareServiceConfig;
import com.tiger.fare.domain.service.impl.SimpleFareCalculatorServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

@DisplayName("SimpleFareCalculatorServiceImpl test casess")
public class SimpleFareCalculatorServiceImplTest {

  private FareCalculatorService fareCalculatorService;
  private FareServiceConfig fareServiceConfig;

  @BeforeEach
  void setUp() {
    this.fareServiceConfig = Mockito.mock(FareServiceConfig.class);
    this.fareCalculatorService =
        new SimpleFareCalculatorServiceImpl(fareServiceConfig, new ArrayList<>());
  }

  @DisplayName("Calculate Fare details")
  @org.junit.jupiter.api.Test
  void calculateValidData() {

    Journey journey =
        new Builder()
            .withDateTime(
                new DateTime.Builder()
                    .withDate(new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
                    .withHour(2)
                    .withMinute(30)
                    .build())
            .withZoneTravelDetail(
                new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build())
            .build();

    List<Journey> journeys = new ArrayList<>();
    journeys.add(journey);

    Mockito.when(fareServiceConfig.configuredFare(journey))
        .thenReturn(new Fare.Builder().withCost(20).build());
    List<FareDetail> fareDetails = fareCalculatorService.calculate(journeys);

    Assertions.assertEquals(20, fareDetails.get(0).fare().cost());
  }
}
