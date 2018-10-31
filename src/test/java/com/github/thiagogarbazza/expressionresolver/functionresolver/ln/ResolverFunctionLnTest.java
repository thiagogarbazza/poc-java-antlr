package com.github.thiagogarbazza.expressionresolver.functionresolver.ln;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.ln.ResolverFunctionLn.getResolverFunctionLn;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionLnTest {

  private ResolverFunctionLn resolverFunctionLn;

  @Before
  public void before() {
    resolverFunctionLn = getResolverFunctionLn();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("1.6094379124341003"), resolverFunctionLn.resolver(new BigDecimal("5")));
  }
}
