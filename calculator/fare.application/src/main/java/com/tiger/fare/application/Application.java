package com.tiger.fare.application;

import com.tiger.fare.application.service.config.AppInput;
import com.tiger.fare.application.service.util.ServiceInitializer;
import com.tiger.fare.domain.FareDetail;
import com.tiger.fare.domain.Journey;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

  static Logger logger = Logger.getLogger(Application.class.getName());

  public static void main(String[] args) throws IOException {

    logger.log(Level.INFO, "Starting Tiger Fare Calculator...");

    AppInput appInput = ServiceInitializer.appInput(args);

    List<Journey> journeys =
        ServiceInitializer.journeyExtractorService(
                appInput.inputFilePath(), appInput.errorFilePath())
            .extract();

    List<FareDetail> fareDetails =
        ServiceInitializer.fareCalculatorService(
                ServiceInitializer.applicationConfig(appInput.appConigFilePath()))
            .calculate(journeys);

    int accumulatedFare =
        ServiceInitializer.fareAccumulatorService().accumulate(fareDetails).cost();

    logger.log(Level.INFO, "Stopping Tiger Fare Calculator...");

    System.out.println(accumulatedFare);
  }
}
