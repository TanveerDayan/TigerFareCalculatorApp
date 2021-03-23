package com.tiger.fare.domain.service.impl;

import com.tiger.fare.domain.Date;
import com.tiger.fare.domain.DateTime;
import com.tiger.fare.domain.Fare.Builder;
import com.tiger.fare.domain.FareDetail;
import com.tiger.fare.domain.Journey;
import com.tiger.fare.domain.ZoneTravelDetail;
import com.tiger.fare.domain.service.FareRevisorService;
import com.tiger.fare.domain.service.config.DayCapFareRevisorConfig;
import com.tiger.fare.domain.service.impl.DayCapFareRevisorServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

@DisplayName("DayCapFareRevisorServiceImpl test cases")
class DayCapFareRevisorServiceImplTest {

  private FareRevisorService fareRevisorService;
  private DayCapFareRevisorConfig dayCapFareRevisorConfig;

  @BeforeEach
  void setUp() {
    this.dayCapFareRevisorConfig = Mockito.mock(DayCapFareRevisorConfig.class);
    this.fareRevisorService = new DayCapFareRevisorServiceImpl(dayCapFareRevisorConfig);
  }

  @DisplayName("Revise Fare details")
  @org.junit.jupiter.api.Test
  void reviseValidData() {

    ZoneTravelDetail zoneTravelDetail =
        new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build();
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
                    .withZoneTravelDetail(zoneTravelDetail)
                    .build())
            .build());
    Mockito.when(dayCapFareRevisorConfig.cap(zoneTravelDetail)).thenReturn(100);
    List<FareDetail> revisedFare = fareRevisorService.revise(fareDetails);

    Assertions.assertEquals(100, revisedFare.get(0).fare().cost());
  }
}
