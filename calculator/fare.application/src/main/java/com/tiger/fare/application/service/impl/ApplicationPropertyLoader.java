package com.tiger.fare.application.service.impl;

import static com.tiger.fare.domain.service.util.Validate.notNull;

import com.tiger.fare.domain.service.config.ApplicationConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationPropertyLoader {

  private Properties mainProperties;

  static Logger logger = Logger.getLogger(ApplicationConfig.class.getName());

  public ApplicationPropertyLoader(String propertyFilePath) throws IOException {
    notNull(propertyFilePath, "propertyFilePath cannot be null.");
    logger.log(Level.INFO, String.format("Loading properties for path : %s", propertyFilePath));
    mainProperties = new Properties();
    FileInputStream file = new FileInputStream(propertyFilePath);
    mainProperties.load(file);
    logger.log(
        Level.INFO, String.format("Completed loading properties for path : %s", propertyFilePath));
    file.close();
  }

  public Properties properties() {
    return mainProperties;
  }
}
