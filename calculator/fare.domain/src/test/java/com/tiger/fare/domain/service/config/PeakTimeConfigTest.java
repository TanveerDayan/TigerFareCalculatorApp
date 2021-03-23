package com.tiger.fare.domain.service.config;

import java.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("PeakTimeConfig test cases")
public class PeakTimeConfigTest {

  private ApplicationConfig applicationConfig;
  private PeakDateTimeConfig peakDateTimeConfig;

  @BeforeEach
  void setUp() {
    this.applicationConfig = Mockito.mock(ApplicationConfig.class);
    Mockito.when(this.applicationConfig.getLocalTime("WeekDayMorningPeakTimeStart"))
        .thenReturn(LocalTime.of(7, 00, 00));
    Mockito.when(this.applicationConfig.getLocalTime("WeekDayMorningPeakTimeEnd"))
        .thenReturn(LocalTime.of(10, 00, 00));

    Mockito.when(this.applicationConfig.getLocalTime("WeekDayEveningPeakTimeStart"))
        .thenReturn(LocalTime.of(18, 00, 00));
    Mockito.when(this.applicationConfig.getLocalTime("WeekDayEveningPeakTimeEnd"))
        .thenReturn(LocalTime.of(20, 00, 00));

    Mockito.when(this.applicationConfig.getLocalTime("WeekEndMorningPeakTimeStart"))
        .thenReturn(LocalTime.of(7, 00, 00));
    Mockito.when(this.applicationConfig.getLocalTime("WeekEndMorningPeakTimeEnd"))
        .thenReturn(LocalTime.of(10, 00, 00));

    Mockito.when(this.applicationConfig.getLocalTime("WeekEndEveningPeakTimeStart"))
        .thenReturn(LocalTime.of(18, 00, 00));
    Mockito.when(this.applicationConfig.getLocalTime("WeekEndEveningPeakTimeEnd"))
        .thenReturn(LocalTime.of(20, 00, 00));
    this.peakDateTimeConfig = new PeakDateTimeConfig(this.applicationConfig);
  }

  @DisplayName("Configured peakTime")
  @Test
  void configuredPeakTime() {
    Assertions.assertEquals(
        LocalTime.of(7, 0, 0), peakDateTimeConfig.weekDayMorningPeakTimeStart());
  }
}
