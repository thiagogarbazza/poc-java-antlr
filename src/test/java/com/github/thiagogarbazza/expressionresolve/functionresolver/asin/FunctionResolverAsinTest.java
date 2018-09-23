package com.github.thiagogarbazza.expressionresolve.functionresolver.asin;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.asin.FunctionResolverAsin.getFunctionResolverAsin;
import static org.junit.Assert.assertEquals;

public class FunctionResolverAsinTest {

  private FunctionResolverAsin functionResolverAsin;

  @Before
  public void before() {
    functionResolverAsin = getFunctionResolverAsin();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.9272952180016123"), functionResolverAsin.resolver(new BigDecimal("0.8")));
  }
}
