package com.tiger.fare.domain.service.config;

import java.time.LocalTime;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("ApplicationConfig test cases")
public class ApplicationConfigTest {

  private Properties properties;
  private ApplicationConfig applicationConfig;

  @BeforeEach
  void setUp() {
    this.properties = Mockito.mock(Properties.class);
    this.applicationConfig = new ApplicationConfig(this.properties);
  }

  @DisplayName("Valid getString")
  @Test
  void validGetString() {
    Mockito.when(properties.getProperty("key1")).thenReturn("value1");
    String value = applicationConfig.getString("key1");
    Assertions.assertEquals("value1", value);
  }

  @DisplayName("Valid getInt")
  @Test
  void validGetInt() {
    Mockito.when(properties.getProperty("key1")).thenReturn("1");
    int value = applicationConfig.getInt("key1");
    Assertions.assertEquals(1, value);
  }

  @DisplayName("Valid getLocalTime")
  @Test
  void validGetDate() {
    Mockito.when(properties.getProperty("key1")).thenReturn("03:00:00");
    LocalTime value = applicationConfig.getLocalTime("key1");
    Assertions.assertEquals(LocalTime.of(03, 0, 0), value);
  }
}
