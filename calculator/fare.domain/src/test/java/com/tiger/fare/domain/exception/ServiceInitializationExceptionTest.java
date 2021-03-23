package com.tiger.fare.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ServiceInitializationException test cases")
public class ServiceInitializationExceptionTest {

  @DisplayName("ServiceInitializationException valid message.")
  @Test
  void validMessage() {
    try {
      throw new ServiceInitializationException("message");
    } catch (ServiceInitializationException exception) {
      Assertions.assertEquals("message", exception.getMessage());
      Assertions.assertEquals(2001, exception.getCode());
    }
  }
}
