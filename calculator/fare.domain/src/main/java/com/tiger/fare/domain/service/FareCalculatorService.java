package com.tiger.fare.domain.service;


import com.tiger.fare.domain.FareDetail;
import com.tiger.fare.domain.Journey;
import java.util.List;

public interface FareCalculatorService {

  /**
   * Calculates the fare details of the given journeys.
   *
   * @param journeys
   * @return List<FareDetail>
   */
  List<FareDetail> calculate(List<Journey> journeys);
}
