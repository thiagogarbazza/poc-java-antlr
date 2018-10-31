package com.github.thiagogarbazza.expressionresolver.resolver.atan;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.atan.ResolverFunctionAtan.getResolverFunctionAtan;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionAtanTest {

  private ResolverFunctionAtan resolverFunctionAtan;

  @Before
  public void before() {
    resolverFunctionAtan = getResolverFunctionAtan();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.6747409422235527"), resolverFunctionAtan.resolver(new BigDecimal("0.8")));
  }
}
