package com.tiger.fare.domain.service.config;

import java.time.LocalTime;

public class PeakDateTimeConfig {

  private static final String WeekDayMorningPeakTimeStart = "WeekDayMorningPeakTimeStart";
  private static final String WeekDayMorningPeakTimeEnd = "WeekDayMorningPeakTimeEnd";
  private static final String WeekDayEveningPeakTimeStart = "WeekDayEveningPeakTimeStart";
  private static final String WeekDayEveningPeakTimeEnd = "WeekDayEveningPeakTimeEnd";
  private static final String WeekEndMorningPeakTimeStart = "WeekEndMorningPeakTimeStart";
  private static final String WeekEndMorningPeakTimeEnd = "WeekEndMorningPeakTimeEnd";
  private static final String WeekEndEveningPeakTimeStart = "WeekEndEveningPeakTimeStart";
  private static final String WeekEndEveningPeakTimeEnd = "WeekEndEveningPeakTimeEnd";

  private LocalTime weekDayMorningPeakTimeStart;
  private LocalTime weekDayMorningPeakTimeEnd;
  private LocalTime weekDayEveningPeakTimeStart;
  private LocalTime weekDayEveningPeakTimeEnd;
  private LocalTime weekEndMorningPeakTimeStart;
  private LocalTime weekEndMorningPeakTimeEnd;
  private LocalTime weekEndEveningPeakTimeStart;
  private LocalTime weekEndEveningPeakTimeEnd;

  public PeakDateTimeConfig(ApplicationConfig applicationConfig) {
    this.weekDayMorningPeakTimeStart = applicationConfig.getLocalTime(WeekDayMorningPeakTimeStart);
    this.weekDayMorningPeakTimeEnd = applicationConfig.getLocalTime(WeekDayMorningPeakTimeEnd);
    this.weekDayEveningPeakTimeStart = applicationConfig.getLocalTime(WeekDayEveningPeakTimeStart);
    this.weekDayEveningPeakTimeEnd = applicationConfig.getLocalTime(WeekDayEveningPeakTimeEnd);
    this.weekEndMorningPeakTimeStart = applicationConfig.getLocalTime(WeekEndMorningPeakTimeStart);
    this.weekEndMorningPeakTimeEnd = applicationConfig.getLocalTime(WeekEndMorningPeakTimeEnd);
    this.weekEndEveningPeakTimeStart = applicationConfig.getLocalTime(WeekEndEveningPeakTimeStart);
    this.weekEndEveningPeakTimeEnd = applicationConfig.getLocalTime(WeekEndEveningPeakTimeEnd);
  }

  public LocalTime weekDayMorningPeakTimeStart() {
    return weekDayMorningPeakTimeStart;
  }

  public LocalTime weekDayMorningPeakTimeEnd() {
    return weekDayMorningPeakTimeEnd;
  }

  public LocalTime weekDayEveningPeakTimeStart() {
    return weekDayEveningPeakTimeStart;
  }

  public LocalTime weekDayEveningPeakTimeEnd() {
    return weekDayEveningPeakTimeEnd;
  }

  public LocalTime weekEndMorningPeakTimeStart() {
    return weekEndMorningPeakTimeStart;
  }

  public LocalTime weekEndMorningPeakTimeEnd() {
    return weekEndMorningPeakTimeEnd;
  }

  public LocalTime weekEndEveningPeakTimeStart() {
    return weekEndEveningPeakTimeStart;
  }

  public LocalTime weekEndEveningPeakTimeEnd() {
    return weekEndEveningPeakTimeEnd;
  }
}
