package com.github.thiagogarbazza.expressionresolve.functionresolver.sqrt;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.sqrt.FunctionResolverSqrt.getFunctionResolverSqrt;
import static org.junit.Assert.assertEquals;

public class FunctionResolverSqrtTest {

  private FunctionResolverSqrt functionResolverSqrt;

  @Before
  public void before() {
    functionResolverSqrt = getFunctionResolverSqrt();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("3.0"), functionResolverSqrt.resolver(new BigDecimal("9")));
  }
}
