package com.github.thiagogarbazza.expressionresolve.functionresolver.log;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.log.FunctionResolverLog.getFunctionResolverLog;
import static org.junit.Assert.assertEquals;

public class FunctionResolverLogTest {

  private FunctionResolverLog functionResolverLog;

  @Before
  public void before() {
    functionResolverLog = getFunctionResolverLog();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.6989700043360189"), functionResolverLog.resolver(new BigDecimal("5")));
  }
}
