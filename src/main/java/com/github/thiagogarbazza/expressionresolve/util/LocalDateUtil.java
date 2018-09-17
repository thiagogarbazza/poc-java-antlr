package com.github.thiagogarbazza.expressionresolve.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  public static LocalDate toLocalDate(String date) {
    return LocalDate.parse(date, DATE_FORMAT);
  }
}
