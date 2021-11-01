package com.github.thiagogarbazza.expressionresolver.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.util.LocalDateUtil.toLocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDateUtilTest {

  @Nested
  class MethodToLocalDate {

    @Test
    void verifyToLocalDate() {
      assertEquals(LocalDate.of(2018, 12, 31), toLocalDate("2018-12-31"));
    }

    @Test
    void verifyToLocalDateLeapYear() {
      assertEquals(LocalDate.of(2016, 2, 29), toLocalDate("2016-02-29"));
    }
  }
}
