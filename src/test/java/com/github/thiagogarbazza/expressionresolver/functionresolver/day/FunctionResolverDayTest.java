package com.github.thiagogarbazza.expressionresolver.functionresolver.day;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.day.FunctionResolverDay.getFunctionResolverDay;
import static org.junit.Assert.assertEquals;

public class FunctionResolverDayTest {

  private FunctionResolverDay functionResolverDay;

  @Before
  public void before() {
    functionResolverDay = getFunctionResolverDay();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("31"), functionResolverDay.resolver(LocalDate.of(2018, 1, 31)));
  }
}
