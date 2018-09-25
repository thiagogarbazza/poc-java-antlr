package com.github.thiagogarbazza.expressionresolver.functionresolver.acos;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.acos.FunctionResolverAcos.getFunctionResolverAcos;
import static org.junit.Assert.assertEquals;

public class FunctionResolverAcosTest {

  private FunctionResolverAcos functionResolverAcos;

  @Before
  public void before() {
    functionResolverAcos = getFunctionResolverAcos();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.45102681179626236"), functionResolverAcos.resolver(new BigDecimal("0.9")));
  }
}
