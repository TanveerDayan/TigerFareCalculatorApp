package com.tiger.fare.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("InvalidArgumentException test cases")
public class InvalidArgumentExceptionTest {

  @DisplayName("InvalidArgumentException valid message.")
  @Test
  void validMessage() {
    try {
      throw new InvalidArgumentException("message");
    } catch (InvalidArgumentException exception) {
      Assertions.assertEquals("message", exception.getMessage());
      Assertions.assertEquals(1001, exception.getCode());
    }
  }
}
