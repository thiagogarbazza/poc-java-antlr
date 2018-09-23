package com.github.thiagogarbazza.expressionresolve.functionresolver.atan;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.atan.FunctionResolverAtan.getFunctionResolverAtan;
import static org.junit.Assert.assertEquals;

public class FunctionResolverAtanTest {

  private FunctionResolverAtan functionResolverAtan;

  @Before
  public void before() {
    functionResolverAtan = getFunctionResolverAtan();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.6747409422235527"), functionResolverAtan.resolver(new BigDecimal("0.8")));
  }
}
