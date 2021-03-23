package com.tiger.fare.domain.service.config;

import com.tiger.fare.domain.ZoneTravelDetail;

public class WeekCapFareRevisorConfig {

  private final ApplicationConfig applicationConfig;

  public WeekCapFareRevisorConfig(ApplicationConfig applicationConfig) {
    this.applicationConfig = applicationConfig;
  }

  public int cap(ZoneTravelDetail zoneTravelDetail) {

    String configKey = configKey(zoneTravelDetail);
    return applicationConfig.getInt(configKey);
  }

  public String configKey(ZoneTravelDetail zoneTravelDetail) {
    return new StringBuilder()
        .append("WeekCap_")
        .append(zoneTravelDetail.from())
        .append("_")
        .append(zoneTravelDetail.to())
        .toString();
  }
}
