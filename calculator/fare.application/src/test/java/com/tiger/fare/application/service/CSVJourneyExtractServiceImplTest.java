package com.tiger.fare.application.service;

import com.tiger.fare.application.service.impl.CSVJourneyExtractServiceImpl;
import com.tiger.fare.domain.Journey;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("CSVJourneyExtractServiceImpl test cases")
public class CSVJourneyExtractServiceImplTest {

  private JourneyExtractorService journeyExtractorService;

  @BeforeEach
  void setUp() {
    this.journeyExtractorService =
        new CSVJourneyExtractServiceImpl(
            "src/test/journeyData/input.csv", "src/test/journeyData/error.csv");
  }

  @DisplayName("Revise Fare details")
  @org.junit.jupiter.api.Test
  void extractWithValidData() {

    List<Journey> extractedJourneys = journeyExtractorService.extract();
    Assertions.assertEquals(true, !extractedJourneys.isEmpty());
  }
}
