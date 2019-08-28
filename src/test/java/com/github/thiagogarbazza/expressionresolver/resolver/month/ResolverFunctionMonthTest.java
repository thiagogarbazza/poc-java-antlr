package com.github.thiagogarbazza.expressionresolver.resolver.month;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.toBigDecimal;
import static com.github.thiagogarbazza.expressionresolver.resolver.month.ResolverFunctionMonth.getResolverFunctionMonth;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionMonthTest {

  private ResolverFunctionMonth resolverFunctionMonth;

  @Before
  public void before() {
    resolverFunctionMonth = getResolverFunctionMonth();
  }

  @Test
  public void verifyResolver() {
    assertEquals(toBigDecimal("2"), resolverFunctionMonth.resolver(LocalDate.of(2018, 2, 28)));
  }
}
