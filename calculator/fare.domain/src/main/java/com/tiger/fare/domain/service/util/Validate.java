package com.tiger.fare.domain.service.util;

import com.tiger.fare.domain.exception.ServiceInitializationException;
import java.util.Objects;

public class Validate {

  public static void notNull(Object object, String message) {
    if (Objects.isNull(object)) {
      throw new ServiceInitializationException(message);
    }
  }
}
