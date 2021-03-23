package com.tiger.fare.domain.service.impl;

import com.tiger.fare.domain.FareDetail;
import com.tiger.fare.domain.comparator.FareDetailDateTimeComparator;
import com.tiger.fare.domain.comparator.ZoneTravelDetailComparator;
import com.tiger.fare.domain.service.FareRevisorService;
import com.tiger.fare.domain.service.config.WeekCapFareRevisorConfig;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static com.tiger.fare.domain.service.util.Validate.notNull;

/**
 * This service revises the values of the given fieldDetails based on the Cap limit set for the week
 * and reduces the cost of some fares if it crosses the configured threshold.
 *
 * <p>This service returns new fareDetails with new references.
 */
public class WeekCapFareRevisorServiceImpl implements FareRevisorService {

  private WeekCapFareRevisorConfig config;

  static Logger logger = Logger.getLogger(WeekCapFareRevisorServiceImpl.class.getName());

  public WeekCapFareRevisorServiceImpl(WeekCapFareRevisorConfig config) {
    notNull(config, "config cannot be null.");
    logger.log(
        Level.INFO,
        String.format("Starting Week Cap Fare Revisor Service with config: %s", config));
    this.config = config;
  }

  @Override
  public List<FareDetail> revise(List<FareDetail> fareDetails) {

    logger.log(Level.INFO, String.format("Starting revision of fareDetails."));

    Map<Integer, List<FareDetail>> dateGroupedFareDetails =
        fareDetails
            .stream()
            .collect(
                (Collectors.groupingBy(
                    fareDetail -> fareDetail.journey().dateTime().weekNumber())));

    int cap =
        config.cap(
            fareDetails
                .stream()
                .map(fareDetail -> fareDetail.journey().zoneTravelDetail())
                .sorted(new ZoneTravelDetailComparator())
                .findFirst()
                .get());

    logger.log(Level.INFO, String.format("Cap limit is set as : %s", cap));

    List<FareDetail> revisedFares =
        dateGroupedFareDetails
            .entrySet()
            .stream()
            .flatMap(
                entry -> {
                  AtomicInteger fareAccumulator = new AtomicInteger();

                  return entry
                      .getValue()
                      .stream()
                      .sorted(new FareDetailDateTimeComparator())
                      .map(
                          fareDetail -> {
                            if (fareAccumulator.addAndGet(fareDetail.fare().cost()) > cap) {
                              int valueExceeded = fareAccumulator.intValue() - cap;
                              fareAccumulator.set(fareAccumulator.intValue() - valueExceeded);
                              int revisedJourneyCost = fareDetail.fare().cost() - valueExceeded;
                              return fareDetail.newFareDetail(revisedJourneyCost);
                            } else return fareDetail.newFareDetail();
                          });
                })
            .collect(Collectors.toList());

    logger.log(Level.INFO, String.format("Completed revision of fareDetails."));

    return revisedFares;
  }
}
