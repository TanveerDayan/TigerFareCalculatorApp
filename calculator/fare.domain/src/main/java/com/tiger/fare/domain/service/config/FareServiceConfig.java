package com.tiger.fare.domain.service.config;

import com.tiger.fare.domain.DateTime;
import com.tiger.fare.domain.Fare;
import com.tiger.fare.domain.Journey;

public class FareServiceConfig {

  private final ApplicationConfig applicationConfig;
  private final PeakDateTimeConfig peakDateTimeConfig;

  public FareServiceConfig(
      ApplicationConfig applicationConfig, PeakDateTimeConfig peakDateTimeConfig) {
    this.applicationConfig = applicationConfig;
    this.peakDateTimeConfig = peakDateTimeConfig;
  }

  public Fare configuredFare(Journey journey) {

    boolean isPeakTime =
        isPeakTime(
            journey.dateTime(), journey.zoneTravelDetail().from(), journey.zoneTravelDetail().to());
    String fareConfigKey =
        configKey(isPeakTime, journey.zoneTravelDetail().from(), journey.zoneTravelDetail().to());
    return new Fare.Builder().withCost(applicationConfig.getInt(fareConfigKey)).build();
  }

  private boolean isPeakTime(DateTime dateTime, int fromZone, int toZone) {
    return (fromZone > 1 && toZone == 1)
        ? (isWeekDayMorningPeakTime(dateTime) || isWeekendMorningPeakTime(dateTime))
        : isAnyPeakTime(dateTime);
  }

  private String configKey(boolean isPeakTime, int fromZone, int toZone) {
    return new StringBuilder()
        .append(fromZone)
        .append("_")
        .append(toZone)
        .append("_")
        .append(peakTimeIdentifier(isPeakTime))
        .toString();
  }

  private char peakTimeIdentifier(boolean isPeakTime) {
    return isPeakTime ? 'P' : 'O';
  }

  private boolean isAnyPeakTime(DateTime dateTime) {
    return isWeekDayMorningPeakTime(dateTime)
        || isWeekDayEveningPeakTime(dateTime)
        || isWeekendMorningPeakTime(dateTime)
        || isWeekendEveningPeakTime(dateTime);
  }

  private boolean isWeekDayMorningPeakTime(DateTime dateTime) {
    return dateTime.date().isWeekDay()
        && (dateTime.time().isAfter(peakDateTimeConfig.weekDayMorningPeakTimeStart())
            && dateTime.time().isBefore(peakDateTimeConfig.weekDayMorningPeakTimeEnd()));
  }

  private boolean isWeekDayEveningPeakTime(DateTime dateTime) {
    return dateTime.date().isWeekDay()
        && (dateTime.time().isAfter(peakDateTimeConfig.weekDayEveningPeakTimeStart())
            && dateTime.time().isBefore(peakDateTimeConfig.weekDayEveningPeakTimeEnd()));
  }

  private boolean isWeekendMorningPeakTime(DateTime dateTime) {
    return !dateTime.date().isWeekDay()
        && (dateTime.time().isAfter(peakDateTimeConfig.weekEndMorningPeakTimeStart())
            && dateTime.time().isBefore(peakDateTimeConfig.weekEndMorningPeakTimeEnd()));
  }

  private boolean isWeekendEveningPeakTime(DateTime dateTime) {
    return !dateTime.date().isWeekDay()
        && (dateTime.time().isAfter(peakDateTimeConfig.weekEndEveningPeakTimeStart())
            && dateTime.time().isBefore(peakDateTimeConfig.weekEndEveningPeakTimeEnd()));
  }
}
