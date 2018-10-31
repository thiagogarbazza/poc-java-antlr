package com.github.thiagogarbazza.expressionresolver.functionresolver.atan;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.atan.ResolverFunctionAtan.getResolverFunctionAtan;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionAtanTest {

  private ResolverFunctionAtan functionResolverAtan;

  @Before
  public void before() {
    functionResolverAtan = getResolverFunctionAtan();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.6747409422235527"), functionResolverAtan.resolver(new BigDecimal("0.8")));
  }
}
