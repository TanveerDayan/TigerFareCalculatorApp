package com.tiger.fare.domain.service.config;


import com.tiger.fare.domain.ZoneTravelDetail;

public class DayCapFareRevisorConfig {

  private final ApplicationConfig applicationConfig;

  public DayCapFareRevisorConfig(ApplicationConfig applicationConfig) {
    this.applicationConfig = applicationConfig;
  }

  public int cap(ZoneTravelDetail zoneTravelDetail) {
    String configKey = configKey(zoneTravelDetail);
    return applicationConfig.getInt(configKey);
  }

  private String configKey(ZoneTravelDetail zoneTravelDetail) {
    return new StringBuilder()
        .append("DayCap_")
        .append(zoneTravelDetail.from())
        .append("_")
        .append(zoneTravelDetail.to())
        .toString();
  }
}
