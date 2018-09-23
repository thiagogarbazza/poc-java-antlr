package com.github.thiagogarbazza.expressionresolve.functionresolver.comparenumber;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.comparenumber.FunctionResolverCompareNumber.getFunctionResolverCompareNumber;
import static org.junit.Assert.assertEquals;

public class FunctionResolverCompareNumberTest {

  private FunctionResolverCompareNumber functionResolverCompareNumber;

  @Before
  public void before() {
    functionResolverCompareNumber = getFunctionResolverCompareNumber();
  }

  @Test
  public void verifyResolverEquals() {
    BigDecimal left = BigDecimal.valueOf(100);
    BigDecimal right = BigDecimal.valueOf(100);

    assertEquals(new BigDecimal("0"), functionResolverCompareNumber.resolver(left, right));
  }

  @Test
  public void verifyResolverGreater() {
    BigDecimal left = BigDecimal.valueOf(100);
    BigDecimal right = BigDecimal.valueOf(0);

    assertEquals(new BigDecimal("1"), functionResolverCompareNumber.resolver(left, right));
  }

  @Test
  public void verifyResolverLess() {
    BigDecimal left = BigDecimal.valueOf(0);
    BigDecimal right = BigDecimal.valueOf(100);

    assertEquals(new BigDecimal("-1"), functionResolverCompareNumber.resolver(left, right));
  }
}
