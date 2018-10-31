package com.github.thiagogarbazza.expressionresolver.resolver.log;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.log.ResolverFunctionLog.getResolverFunctionLog;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionLogTest {

  private ResolverFunctionLog resolverFunctionLog;

  @Before
  public void before() {
    resolverFunctionLog = getResolverFunctionLog();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.6989700043360189"), resolverFunctionLog.resolver(new BigDecimal("5")));
  }
}
