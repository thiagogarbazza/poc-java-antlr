package com.github.thiagogarbazza.expressionresolver.resolver.sin;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.sin.ResolverFunctionSin.getResolverFunctionSin;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionSinTest {

  private ResolverFunctionSin resolverFunctionSin;

  @Before
  public void before() {
    resolverFunctionSin = getResolverFunctionSin();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.8939966636005579"), resolverFunctionSin.resolver(new BigDecimal("90")));
  }
}
