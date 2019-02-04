package com.github.thiagogarbazza.expressionresolver.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class LocalDateUtil {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  public static LocalDate toLocalDate(String date) {
    return LocalDate.parse(date, DATE_FORMAT);
  }
}
