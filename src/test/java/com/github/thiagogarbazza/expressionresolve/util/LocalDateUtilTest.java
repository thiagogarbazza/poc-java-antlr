package com.github.thiagogarbazza.expressionresolve.util;

import org.junit.Test;

import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolve.util.LocalDateUtil.toLocalDate;
import static org.junit.Assert.assertEquals;

public class LocalDateUtilTest {

  @Test
  public void verifyStringToLocalDate() {
    assertEquals(LocalDate.of(2018, 12, 31), toLocalDate("2018/12/31"));
  }
}
