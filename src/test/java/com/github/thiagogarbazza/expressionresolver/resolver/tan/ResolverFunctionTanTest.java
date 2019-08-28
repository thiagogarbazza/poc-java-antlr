package com.github.thiagogarbazza.expressionresolver.resolver.tan;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.toBigDecimal;
import static com.github.thiagogarbazza.expressionresolver.resolver.tan.ResolverFunctionTan.getResolverFunctionTan;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionTanTest {

  private ResolverFunctionTan resolverFunctionTan;

  @Before
  public void before() {
    resolverFunctionTan = getResolverFunctionTan();
  }

  @Test
  public void verifyResolver() {
    assertEquals(toBigDecimal("-1.995200412208242"), resolverFunctionTan.resolver(new BigDecimal("90")));
  }
}
