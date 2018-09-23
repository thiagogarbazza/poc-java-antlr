package com.github.thiagogarbazza.expressionresolve.functionresolver.comparedate;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.comparedate.FunctionResolverCompareDate.getFunctionResolverCompareDate;
import static org.junit.Assert.assertEquals;

public class FunctionResolverCompareDateTest {

  private FunctionResolverCompareDate functionResolverCompareDate;

  @Before
  public void before() {
    functionResolverCompareDate = getFunctionResolverCompareDate();
  }

  @Test
  public void verifyResolverEquals() {
    LocalDate left = LocalDate.of(2000, 1, 1);
    LocalDate right = LocalDate.of(2000, 1, 1);

    assertEquals(new BigDecimal("0"), functionResolverCompareDate.resolver(left, right));
  }

  @Test
  public void verifyResolverGreater() {
    LocalDate left = LocalDate.of(2020, 1, 1);
    LocalDate right = LocalDate.of(2000, 1, 1);

    assertEquals(new BigDecimal("1"), functionResolverCompareDate.resolver(left, right));
  }

  @Test
  public void verifyResolverLess() {
    LocalDate left = LocalDate.of(2000, 1, 1);
    LocalDate right = LocalDate.of(2020, 1, 1);

    assertEquals(new BigDecimal("-1"), functionResolverCompareDate.resolver(left, right));
  }
}
