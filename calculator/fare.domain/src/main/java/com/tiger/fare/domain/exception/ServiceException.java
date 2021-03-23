package com.tiger.fare.domain.exception;

public abstract class ServiceException extends RuntimeException {
  public abstract int getCode();
}
