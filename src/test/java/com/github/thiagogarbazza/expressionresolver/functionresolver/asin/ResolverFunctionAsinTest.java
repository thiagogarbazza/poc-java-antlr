package com.github.thiagogarbazza.expressionresolver.functionresolver.asin;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.asin.ResolverFunctionAsin.getResolverFunctionAsin;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionAsinTest {

  private ResolverFunctionAsin resolverFunctionAsin;

  @Before
  public void before() {
    resolverFunctionAsin = getResolverFunctionAsin();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.9272952180016123"), resolverFunctionAsin.resolver(new BigDecimal("0.8")));
  }
}
