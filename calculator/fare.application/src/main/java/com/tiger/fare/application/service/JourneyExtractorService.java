package com.tiger.fare.application.service;

import com.tiger.fare.domain.Journey;
import java.io.IOException;
import java.util.List;


public interface JourneyExtractorService {

  /**
   * Extracts data from source and returns objects.
   *
   * @return List<Journey>
   */
  List<Journey> extract();
}
