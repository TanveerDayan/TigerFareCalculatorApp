package com.tiger.fare.domain.service.config;

import com.tiger.fare.domain.Date;
import com.tiger.fare.domain.DateTime;
import com.tiger.fare.domain.Fare;
import com.tiger.fare.domain.Journey;
import com.tiger.fare.domain.Journey.Builder;
import com.tiger.fare.domain.ZoneTravelDetail;
import com.tiger.fare.domain.exception.ConfigNotFoundException;
import java.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("FareServiceConfig test cases")
public class FareServiceConfigTest {

  private FareServiceConfig fareServiceConfig;
  private ApplicationConfig applicationConfig;
  private PeakDateTimeConfig peakDateTimeConfig;

  @BeforeEach
  void setUp() {
    this.applicationConfig = Mockito.mock(ApplicationConfig.class);
    this.peakDateTimeConfig = Mockito.mock(PeakDateTimeConfig.class);
    Mockito.when(peakDateTimeConfig.weekDayMorningPeakTimeStart())
        .thenReturn(LocalTime.of(07, 00, 00));
    Mockito.when(peakDateTimeConfig.weekDayMorningPeakTimeEnd())
        .thenReturn(LocalTime.of(10, 00, 00));
    Mockito.when(peakDateTimeConfig.weekEndMorningPeakTimeStart())
        .thenReturn(LocalTime.of(07, 00, 00));
    Mockito.when(peakDateTimeConfig.weekEndMorningPeakTimeEnd())
        .thenReturn(LocalTime.of(10, 00, 00));
    Mockito.when(peakDateTimeConfig.weekDayEveningPeakTimeStart())
        .thenReturn(LocalTime.of(18, 00, 00));
    Mockito.when(peakDateTimeConfig.weekDayEveningPeakTimeEnd())
        .thenReturn(LocalTime.of(20, 00, 00));
    Mockito.when(peakDateTimeConfig.weekEndEveningPeakTimeStart())
        .thenReturn(LocalTime.of(18, 00, 00));
    Mockito.when(peakDateTimeConfig.weekEndEveningPeakTimeEnd())
        .thenReturn(LocalTime.of(20, 00, 00));
    this.fareServiceConfig = new FareServiceConfig(applicationConfig, peakDateTimeConfig);
  }

  @DisplayName("Configured Fare")
  @Test
  void configuredFare() {

    Mockito.when(applicationConfig.getInt("1_2_O")).thenReturn(20);

    Fare fare =
        fareServiceConfig.configuredFare(
            new Builder()
                .withDateTime(
                    new DateTime.Builder()
                        .withDate(new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
                        .withHour(2)
                        .withMinute(30)
                        .build())
                .withZoneTravelDetail(
                    new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build())
                .build());

    Assertions.assertEquals(20, fare.cost());
  }

  @DisplayName("Not Configured Fare")
  @Test
  void nonConfiguredFare() {

    Mockito.when(applicationConfig.getInt("1_2_O")).thenThrow(new ConfigNotFoundException("1_2_0"));
    Assertions.assertThrows(
        ConfigNotFoundException.class,
        () ->
            fareServiceConfig.configuredFare(
                new Builder()
                    .withDateTime(
                        new DateTime.Builder()
                            .withDate(
                                new Date.Builder().withDay(1).withMonth(2).withYear(2021).build())
                            .withHour(2)
                            .withMinute(30)
                            .build())
                    .withZoneTravelDetail(
                        new ZoneTravelDetail.Builder().withFromZone(1).withToZone(2).build())
                    .build()));
  }
}
