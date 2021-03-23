package com.tiger.fare.domain.service;

import com.tiger.fare.domain.FareDetail;
import java.util.List;

public interface FareRevisorService {

  /**
   * Revises the values of the fareDetails passed as input. Returns new references.
   *
   * @param fareDetails
   * @return List<FareDetail>
   */
  List<FareDetail> revise(List<FareDetail> fareDetails);
}
