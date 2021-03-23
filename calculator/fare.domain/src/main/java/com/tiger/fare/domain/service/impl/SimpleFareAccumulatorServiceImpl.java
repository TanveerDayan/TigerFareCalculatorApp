package com.tiger.fare.domain.service.impl;

import com.tiger.fare.domain.Fare;
import com.tiger.fare.domain.FareDetail;
import com.tiger.fare.domain.service.FareAccumulatorService;
import java.util.List;

public class SimpleFareAccumulatorServiceImpl implements FareAccumulatorService {

  @Override
  public Fare accumulate(List<FareDetail> fareDetails) {
    return fareDetails
        .stream()
        .map(fareDetail -> fareDetail.fare())
        .reduce((fair1, fair2) -> fair1.add(fair2))
        .orElse(new Fare.Builder().withCost(0).build());
  }
}
