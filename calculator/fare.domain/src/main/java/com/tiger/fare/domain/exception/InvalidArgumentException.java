package com.tiger.fare.domain.exception;

public class InvalidArgumentException extends FareCalculatorException {

  private String message;

  public InvalidArgumentException(String message) {
    this.message = message;
  }

  @Override
  public int getCode() {
    return 1001;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
