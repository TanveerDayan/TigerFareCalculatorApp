package com.tiger.fare.domain.exception;

public class ServiceIOException extends ServiceException {

  private String message;

  public ServiceIOException(String message) {
    this.message = message;
  }

  @Override
  public int getCode() {
    return 2002;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
