package com.tiger.fare.application.service.util;

import com.tiger.fare.application.service.JourneyExtractorService;
import com.tiger.fare.application.service.impl.CSVJourneyExtractServiceImpl;
import com.tiger.fare.application.service.impl.ApplicationPropertyLoader;
import com.tiger.fare.domain.service.config.ApplicationConfig;
import com.tiger.fare.domain.service.config.DayCapFareRevisorConfig;
import com.tiger.fare.domain.service.config.FareServiceConfig;
import com.tiger.fare.domain.service.config.PeakDateTimeConfig;
import com.tiger.fare.domain.service.config.WeekCapFareRevisorConfig;
import com.tiger.fare.domain.service.impl.DayCapFareRevisorServiceImpl;
import com.tiger.fare.domain.service.impl.SimpleFareAccumulatorServiceImpl;
import com.tiger.fare.domain.service.impl.SimpleFareCalculatorServiceImpl;
import com.tiger.fare.domain.service.impl.WeekCapFareRevisorServiceImpl;
import com.tiger.fare.application.service.config.AppInput;
import com.tiger.fare.domain.service.FareAccumulatorService;
import com.tiger.fare.domain.service.FareCalculatorService;
import com.tiger.fare.domain.service.FareRevisorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceInitializer {

  public static AppInput appInput(String[] args) {
    return new AppInput(args);
  }

  public static ApplicationConfig applicationConfig(String path) throws IOException {
    return new ApplicationConfig(new ApplicationPropertyLoader(path).properties());
  }

  private static FareRevisorService dayCapFareRevisorService(ApplicationConfig applicationConfig) {
    return new DayCapFareRevisorServiceImpl(new DayCapFareRevisorConfig(applicationConfig));
  }

  private static FareRevisorService weekCapFareRevisorService(ApplicationConfig applicationConfig) {
    return new WeekCapFareRevisorServiceImpl(new WeekCapFareRevisorConfig(applicationConfig));
  }

  public static FareCalculatorService fareCalculatorService(ApplicationConfig applicationConfig) {

    return new SimpleFareCalculatorServiceImpl(
        new FareServiceConfig(applicationConfig, new PeakDateTimeConfig(applicationConfig)),
        fareRevisorServices(applicationConfig));
  }

  private static List<FareRevisorService> fareRevisorServices(ApplicationConfig applicationConfig) {
    List<FareRevisorService> fareRevisorServices = new ArrayList<>();
    fareRevisorServices.add(dayCapFareRevisorService(applicationConfig));
    fareRevisorServices.add(weekCapFareRevisorService(applicationConfig));
    return fareRevisorServices;
  }

  public static JourneyExtractorService journeyExtractorService(String path, String errorPath) {
    return new CSVJourneyExtractServiceImpl(path, errorPath);
  }

  public static FareAccumulatorService fareAccumulatorService() {
    return new SimpleFareAccumulatorServiceImpl();
  }
}
