package com.tiger.fare.domain.exception;

public abstract class ConfigException extends RuntimeException {
  public abstract int getCode();
}
