package com.github.thiagogarbazza.expressionresolver.functionresolver.sqrt;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.sqrt.ResolverFunctionSqrt.getResolverFunctionSqrt;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionSqrtTest {

  private ResolverFunctionSqrt resolverFunctionSqrt;

  @Before
  public void before() {
    resolverFunctionSqrt = getResolverFunctionSqrt();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("3.0"), resolverFunctionSqrt.resolver(new BigDecimal("9")));
  }
}
