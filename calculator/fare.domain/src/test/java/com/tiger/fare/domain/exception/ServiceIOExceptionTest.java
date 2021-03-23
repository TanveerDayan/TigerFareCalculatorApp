package com.tiger.fare.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ServiceIOException test cases")
public class ServiceIOExceptionTest {

  @DisplayName("ServiceInitializationException valid message.")
  @Test
  void validMessage() {
    try {
      throw new ServiceIOException("message");
    } catch (ServiceIOException exception) {
      Assertions.assertEquals("message", exception.getMessage());
      Assertions.assertEquals(2002, exception.getCode());
    }
  }
}
