package com.tiger.fare.domain.service;

import com.tiger.fare.domain.Fare;
import com.tiger.fare.domain.FareDetail;
import java.util.List;

public interface FareAccumulatorService {

  /**
   * Accumulates multiple fareDetails and returns the fare.
   *
   * @param fareDetails
   * @return Fare
   */
  Fare accumulate(List<FareDetail> fareDetails);
}
