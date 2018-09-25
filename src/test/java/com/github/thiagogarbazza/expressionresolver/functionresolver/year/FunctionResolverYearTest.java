package com.github.thiagogarbazza.expressionresolver.functionresolver.year;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.year.FunctionResolverYear.getFunctionResolvergetYear;
import static org.junit.Assert.assertEquals;

public class FunctionResolverYearTest {

  private FunctionResolverYear functionResolverYear;

  @Before
  public void before() {
    functionResolverYear = getFunctionResolvergetYear();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("2018"), functionResolverYear.resolver(LocalDate.of(2018, 2, 28)));
  }
}
