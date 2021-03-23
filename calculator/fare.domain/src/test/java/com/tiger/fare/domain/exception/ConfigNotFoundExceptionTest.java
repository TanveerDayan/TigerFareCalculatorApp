package com.tiger.fare.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ConfigNotFoundException test cases")
public class ConfigNotFoundExceptionTest {

  @DisplayName("ConfigNotFoundException valid message.")
  @Test
  void validMessage() {
    try {
      throw new ConfigNotFoundException("key1");
    } catch (ConfigNotFoundException exception) {
      Assertions.assertEquals(
          exception.getMessage(), String.format("ConfigValue not found for key: %s", "key1"));
      Assertions.assertEquals(exception.getCode(), 3001);
    }
  }
}
