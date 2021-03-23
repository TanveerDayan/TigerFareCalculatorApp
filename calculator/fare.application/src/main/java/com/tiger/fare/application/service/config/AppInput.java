package com.tiger.fare.application.service.config;

import com.tiger.fare.domain.exception.ServiceInitializationException;

public class AppInput {

  private String appConigFilePath;
  private String inputFilePath;
  private String errorFilePath;

  public AppInput(String[] args) {
    if (args.length < 3)
      throw new ServiceInitializationException(
          "\nThree arguments should be provided.\n1.applicationConfigPath\n2.inputFilePath\n3.errorFilePath");

    this.appConigFilePath = args[0];
    this.inputFilePath = args[1];
    this.errorFilePath = args[2];
  }

  public String appConigFilePath() {
    return appConigFilePath;
  }

  public String inputFilePath() {
    return inputFilePath;
  }

  public String errorFilePath() {
    return errorFilePath;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AppInput{");
    sb.append("appConigFilePath='").append(appConigFilePath).append('\'');
    sb.append(", inputFilePath='").append(inputFilePath).append('\'');
    sb.append(", errorFilePath='").append(errorFilePath).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
