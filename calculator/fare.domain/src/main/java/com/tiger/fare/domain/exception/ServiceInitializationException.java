package com.tiger.fare.domain.exception;

public class ServiceInitializationException extends ServiceException {

  private String message;

  public ServiceInitializationException(String message) {
    this.message = message;
  }

  @Override
  public int getCode() {
    return 2001;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
