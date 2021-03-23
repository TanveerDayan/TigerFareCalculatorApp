package com.tiger.fare.domain.service.impl;

import com.tiger.fare.domain.FareDetail;
import com.tiger.fare.domain.FareDetail.Builder;
import com.tiger.fare.domain.Journey;
import com.tiger.fare.domain.service.FareCalculatorService;
import com.tiger.fare.domain.service.FareRevisorService;
import com.tiger.fare.domain.service.config.FareServiceConfig;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static com.tiger.fare.domain.service.util.Validate.notNull;

/**
 * Simple Fare Calculator Service which is an implementation of FareCalculatorService. This takes
 * FareServiceConfig as an input argument.
 *
 * <p>This service performs calculation by first calculating the raw fare details and applying
 * revisions on it depending on the input fareRevisorServices.
 */
public class SimpleFareCalculatorServiceImpl implements FareCalculatorService {

  private FareServiceConfig config;
  private List<FareRevisorService> fareRevisorServices;

  static Logger logger = Logger.getLogger(SimpleFareCalculatorServiceImpl.class.getName());

  public SimpleFareCalculatorServiceImpl(
      FareServiceConfig config, List<FareRevisorService> fareRevisorServices) {
    notNull(config, "config cannot be null.");
    notNull(fareRevisorServices, "fareRevisors cannot be null.");
    logger.log(
        Level.INFO,
        String.format("Starting Week Cap Fare Revisor Service with config : %s", config));
    this.config = config;
    this.fareRevisorServices = fareRevisorServices;
  }

  @Override
  public List<FareDetail> calculate(List<Journey> journeys) {

    logger.log(Level.INFO, "Calculating fare details for journeys");

    List<FareDetail> rawFareDetails =
        journeys
            .stream()
            .map(
                journey ->
                    new Builder()
                        .withFare(config.configuredFare(journey))
                        .withJourney(journey)
                        .build())
            .collect(Collectors.toList());

    List<FareDetail> revisedFareDetails = rawFareDetails;

    for (FareRevisorService fareRevisorService : fareRevisorServices) {
      revisedFareDetails = fareRevisorService.revise(revisedFareDetails);
    }
    return revisedFareDetails;
  }
}
