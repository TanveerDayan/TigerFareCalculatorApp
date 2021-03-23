package com.tiger.fare.domain.exception;

public abstract class FareCalculatorException extends RuntimeException {
  public abstract int getCode();

}
