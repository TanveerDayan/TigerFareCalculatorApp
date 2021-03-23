package com.tiger.fare.domain.service.config;

import com.tiger.fare.domain.ZoneTravelDetail.Builder;
import com.tiger.fare.domain.exception.ConfigNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("WeekCapFareRevisorConfig test cases")
public class WeekCapFareRevisorConfigTest {
  private ApplicationConfig applicationConfig;
  private WeekCapFareRevisorConfig weekCapFareRevisorConfig;

  @BeforeEach
  void setUp() {
    this.applicationConfig = Mockito.mock(ApplicationConfig.class);
    this.weekCapFareRevisorConfig = new WeekCapFareRevisorConfig(this.applicationConfig);
  }

  @DisplayName("Valid Cap for configured zone")
  @Test
  void validCap() {
    Mockito.when(applicationConfig.getInt("WeekCap_1_2")).thenReturn(1200);
    int cap = weekCapFareRevisorConfig.cap(new Builder().withFromZone(1).withToZone(2).build());
    Assertions.assertEquals(1200, cap);
  }

  @DisplayName("InValid Cap for non configured zone")
  @Test
  void capForNonConfiguredZone() {

    Mockito.when(applicationConfig.getInt("WeekCap_1_3"))
        .thenThrow(new ConfigNotFoundException("WeekCap_1_3"));
    Assertions.assertThrows(
        ConfigNotFoundException.class,
        () -> weekCapFareRevisorConfig.cap(new Builder().withFromZone(1).withToZone(3).build()));
  }
}
