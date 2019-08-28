package com.github.thiagogarbazza.expressionresolver.resolver.asin;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.toBigDecimal;
import static com.github.thiagogarbazza.expressionresolver.resolver.asin.ResolverFunctionAsin.getResolverFunctionAsin;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionAsinTest {

  private ResolverFunctionAsin resolverFunctionAsin;

  @Before
  public void before() {
    resolverFunctionAsin = getResolverFunctionAsin();
  }

  @Test
  public void verifyResolver() {
    assertEquals(toBigDecimal("0.9272952180016123"), resolverFunctionAsin.resolver(new BigDecimal("0.8")));
  }
}
