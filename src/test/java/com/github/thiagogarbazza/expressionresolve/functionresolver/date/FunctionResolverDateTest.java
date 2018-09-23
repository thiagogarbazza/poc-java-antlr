package com.github.thiagogarbazza.expressionresolve.functionresolver.date;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.date.FunctionResolverDate.getFunctionResolverDate;
import static org.junit.Assert.assertEquals;

public class FunctionResolverDateTest {

  private FunctionResolverDate functionResolverDate;

  @Before
  public void before() {
    functionResolverDate = getFunctionResolverDate();
  }

  @Test
  public void verifyResolver() {
    assertEquals(LocalDate.of(2018, 1, 31), functionResolverDate.resolver(BigDecimal.valueOf(2018), BigDecimal.valueOf(1), BigDecimal.valueOf(31)));
  }
}
