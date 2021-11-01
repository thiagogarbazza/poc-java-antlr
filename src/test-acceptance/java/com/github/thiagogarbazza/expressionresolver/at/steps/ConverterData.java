package com.github.thiagogarbazza.expressionresolver.at.steps;

import com.github.thiagogarbazza.expressionresolver.util.json.JsonUtils;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
class ConverterData {

  public static Object stringToValue(final String expected) {
    if ("is today".equals(expected)) {
      return LocalDate.now();
    }

    return JsonUtils.stringToObject(expected, Object.class);
  }
}
