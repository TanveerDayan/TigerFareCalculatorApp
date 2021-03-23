package com.tiger.fare.domain.exception;

public class ConfigNotFoundException extends ServiceException {

  private final String configKey;

  public ConfigNotFoundException(String configKey) {
    this.configKey = configKey;
  }

  @Override
  public int getCode() {
    return 3001;
  }

  @Override
  public String getMessage() {
    return String.format("ConfigValue not found for key: %s", configKey);
  }
}
