package com.tiger.fare.domain;

import com.tiger.fare.domain.ZoneTravelDetail.Builder;
import com.tiger.fare.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ZoneTravelDetail test cases")
public class ZoneTravelDetailTest {

  @DisplayName("Valid ZoneTravelDetail")
  @Test
  void validZoneTravelDetail() {
    ZoneTravelDetail zoneTravelDetail = new Builder().withFromZone(1).withToZone(2).build();
    Assertions.assertEquals(1, zoneTravelDetail.from());
    Assertions.assertEquals(2, zoneTravelDetail.to());
  }

  @DisplayName("ZoneTravelDetail with invalid from")
  @Test
  void zoneTravelDetailWithInvalidFrom() {

    Assertions.assertThrows(
        InvalidArgumentException.class, () -> new Builder().withFromZone(-1).withToZone(2).build());
  }

  @DisplayName("ZoneTravelDetail with invalid to")
  @Test
  void zoneTravelDetailWithInvalidTo() {

    Assertions.assertThrows(
        InvalidArgumentException.class, () -> new Builder().withFromZone(1).withToZone(-2).build());
  }
}
