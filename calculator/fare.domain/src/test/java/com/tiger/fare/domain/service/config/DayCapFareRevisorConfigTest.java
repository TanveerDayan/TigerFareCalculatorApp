package com.tiger.fare.domain.service.config;

import com.tiger.fare.domain.ZoneTravelDetail.Builder;
import com.tiger.fare.domain.exception.ConfigNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("DayCapFareRevisorConfig test cases")
public class DayCapFareRevisorConfigTest {
  private ApplicationConfig applicationConfig;
  private DayCapFareRevisorConfig dayCapFareRevisorConfig;

  @BeforeEach
  void setUp() {
    this.applicationConfig = Mockito.mock(ApplicationConfig.class);
    this.dayCapFareRevisorConfig = new DayCapFareRevisorConfig(this.applicationConfig);
  }

  @DisplayName("Valid Cap for configured zone")
  @Test
  void validCap() {
    Mockito.when(applicationConfig.getInt("DayCap_1_2")).thenReturn(200);
    int cap = dayCapFareRevisorConfig.cap(new Builder().withFromZone(1).withToZone(2).build());
    Assertions.assertEquals(200, cap);
  }

  @DisplayName("InValid Cap for non configured zone")
  @Test
  void capForNonConfiguredZone() {

    Mockito.when(applicationConfig.getInt("DayCap_1_3"))
        .thenThrow(new ConfigNotFoundException("DayCap_1_3"));
    Assertions.assertThrows(
        ConfigNotFoundException.class,
        () -> dayCapFareRevisorConfig.cap(new Builder().withFromZone(1).withToZone(3).build()));
  }
}
