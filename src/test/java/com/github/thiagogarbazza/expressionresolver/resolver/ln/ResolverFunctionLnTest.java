package com.github.thiagogarbazza.expressionresolver.resolver.ln;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.toBigDecimal;
import static com.github.thiagogarbazza.expressionresolver.resolver.ln.ResolverFunctionLn.getResolverFunctionLn;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionLnTest {

  private ResolverFunctionLn resolverFunctionLn;

  @Before
  public void before() {
    resolverFunctionLn = getResolverFunctionLn();
  }

  @Test
  public void verifyResolver() {
    assertEquals(toBigDecimal("1.6094379124341003"), resolverFunctionLn.resolver(new BigDecimal("5")));
  }
}
