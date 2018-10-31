package com.github.thiagogarbazza.expressionresolver.resolver.date;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.resolver.date.ResolverFunctionDate.getResolverFunctionDate;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionDateTest {

  private ResolverFunctionDate resolverFunctionDate;

  @Before
  public void before() {
    resolverFunctionDate = getResolverFunctionDate();
  }

  @Test
  public void verifyResolver() {
    assertEquals(LocalDate.of(2018, 1, 31), resolverFunctionDate.resolver(BigDecimal.valueOf(2018), BigDecimal.valueOf(1), BigDecimal.valueOf(31)));
  }
}
