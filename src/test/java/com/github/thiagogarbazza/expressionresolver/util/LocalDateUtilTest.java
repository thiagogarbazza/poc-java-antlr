package com.github.thiagogarbazza.expressionresolver.util;

import org.junit.Test;

import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.util.LocalDateUtil.toLocalDate;
import static org.junit.Assert.assertEquals;

public class LocalDateUtilTest {

  @Test
  public void verifyToLocalDate() {
    assertEquals(LocalDate.of(2018, 12, 31), toLocalDate("2018/12/31"));
  }


  @Test
  public void verifyToLocalDateLeapYear() {
    assertEquals(LocalDate.of(2016, 02, 29), toLocalDate("2016/02/29"));
  }
}
