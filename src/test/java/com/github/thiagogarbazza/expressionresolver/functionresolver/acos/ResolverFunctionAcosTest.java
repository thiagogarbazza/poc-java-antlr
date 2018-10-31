package com.github.thiagogarbazza.expressionresolver.functionresolver.acos;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.acos.ResolverFunctionAcos.getResolverFunctionAcos;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionAcosTest {

  private ResolverFunctionAcos functionResolverAcos;

  @Before
  public void before() {
    functionResolverAcos = getResolverFunctionAcos();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.45102681179626236"), functionResolverAcos.resolver(new BigDecimal("0.9")));
  }
}
