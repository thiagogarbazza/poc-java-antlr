package com.github.thiagogarbazza.expressionresolve.functionresolver.ln;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.ln.FunctionResolverLn.getFunctionResolverLn;
import static org.junit.Assert.assertEquals;

public class FunctionResolverLnTest {

  private FunctionResolverLn functionResolverLn;

  @Before
  public void before() {
    functionResolverLn = getFunctionResolverLn();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("1.6094379124341003"), functionResolverLn.resolver(new BigDecimal("5")));
  }
}
