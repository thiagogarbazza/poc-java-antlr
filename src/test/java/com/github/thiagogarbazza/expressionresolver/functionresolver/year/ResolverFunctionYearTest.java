package com.github.thiagogarbazza.expressionresolver.functionresolver.year;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.year.ResolverFunctionYear.getResolverFunctionYear;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionYearTest {

  private ResolverFunctionYear resolverFunctionYear;

  @Before
  public void before() {
    resolverFunctionYear = getResolverFunctionYear();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("2018"), resolverFunctionYear.resolver(LocalDate.of(2018, 2, 28)));
  }
}
