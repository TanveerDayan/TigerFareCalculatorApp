package com.tiger.fare.domain.service.config;

import static com.tiger.fare.domain.service.util.Validate.notNull;

import com.tiger.fare.domain.exception.ConfigNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationConfig {

  private Properties mainProperties;

  static Logger logger = Logger.getLogger(ApplicationConfig.class.getName());

  public ApplicationConfig(Properties properties) {
    notNull(properties, "properties cannot be null.");
    this.mainProperties = properties;
  }

  public LocalTime getLocalTime(String key) {
    String value = getString(key);
    String[] timeSplit = value.split(":");
    if (timeSplit.length < 3)
      throw new IllegalArgumentException(String.format("Configured Time is invalid : %s", value));
    return LocalTime.of(
        Integer.parseInt(timeSplit[0]),
        Integer.parseInt(timeSplit[1]),
        Integer.parseInt(timeSplit[2]));
  }

  public int getInt(String key) {
    return Integer.parseInt(getString(key));
  }

  public String getString(String key) {
    return Optional.ofNullable(mainProperties.getProperty(key))
        .orElseThrow(() -> new ConfigNotFoundException(key));
  }
}
