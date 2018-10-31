package com.github.thiagogarbazza.expressionresolver.resolver.comparenumber;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.comparenumber.ResolverFunctionCompareNumber.getResolverFunctionCompareNumber;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionCompareNumberTest {

  private ResolverFunctionCompareNumber resolverFunctionCompareNumber;

  @Before
  public void before() {
    resolverFunctionCompareNumber = getResolverFunctionCompareNumber();
  }

  @Test
  public void verifyResolverEquals() {
    BigDecimal left = BigDecimal.valueOf(100);
    BigDecimal right = BigDecimal.valueOf(100);

    assertEquals(new BigDecimal("0"), resolverFunctionCompareNumber.resolver(left, right));
  }

  @Test
  public void verifyResolverGreater() {
    BigDecimal left = BigDecimal.valueOf(100);
    BigDecimal right = BigDecimal.valueOf(0);

    assertEquals(new BigDecimal("1"), resolverFunctionCompareNumber.resolver(left, right));
  }

  @Test
  public void verifyResolverLess() {
    BigDecimal left = BigDecimal.valueOf(0);
    BigDecimal right = BigDecimal.valueOf(100);

    assertEquals(new BigDecimal("-1"), resolverFunctionCompareNumber.resolver(left, right));
  }
}
