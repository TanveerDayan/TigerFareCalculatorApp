package com.tiger.fare.domain;

import com.tiger.fare.domain.Fare.Builder;
import com.tiger.fare.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Fare test cases")
public class FareTest {

  @DisplayName("Valid Fare")
  @Test
  void validFare() {
    Fare fare = new Builder().withCost(10).build();
    Assertions.assertEquals(fare.cost(), 10);
  }

  @DisplayName("Fare with invalid cost")
  @Test
  void fareWithInvalidCost() {
    Assertions.assertThrows(
        InvalidArgumentException.class, () -> new Builder().withCost(-10).build());
  }
}
