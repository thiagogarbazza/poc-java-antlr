package com.github.thiagogarbazza.expressionresolver.functionresolver.tan;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.tan.FunctionResolverTan.getFunctionResolverTan;
import static org.junit.Assert.assertEquals;

public class FunctionResolverTanTest {

  private FunctionResolverTan functionResolverTan;

  @Before
  public void before() {
    functionResolverTan = getFunctionResolverTan();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("-1.995200412208242"), functionResolverTan.resolver(new BigDecimal("90")));
  }
}
