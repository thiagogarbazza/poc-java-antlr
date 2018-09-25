package com.github.thiagogarbazza.expressionresolver.functionresolver.month;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.month.FunctionResolverMonth.getFunctionResolverMonth;
import static org.junit.Assert.assertEquals;

public class FunctionResolverMonthTest {

  private FunctionResolverMonth functionResolverMonth;

  @Before
  public void before() {
    functionResolverMonth = getFunctionResolverMonth();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("2"), functionResolverMonth.resolver(LocalDate.of(2018, 2, 28)));
  }
}
