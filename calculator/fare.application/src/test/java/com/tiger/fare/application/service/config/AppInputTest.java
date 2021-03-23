package com.tiger.fare.application.service.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AppInput test cases")
public class AppInputTest {

  @DisplayName("Valid AppInput")
  @Test
  void validAppInput() {
    String[] strArray = {"config", "inputPath", "errorPath"};
    AppInput appInput = new AppInput(strArray);
    Assertions.assertEquals("config", appInput.appConigFilePath());
    Assertions.assertEquals("inputPath", appInput.inputFilePath());
    Assertions.assertEquals("errorPath", appInput.errorFilePath());
  }

  @DisplayName("AppInput with invalid arguments")
  @Test
  void appInputWithInvalidArguments() {
    String[] strArray = {
      "config", "inputPath",
    };
    Assertions.assertThrows(IllegalArgumentException.class, () -> new AppInput(strArray));
  }
}
