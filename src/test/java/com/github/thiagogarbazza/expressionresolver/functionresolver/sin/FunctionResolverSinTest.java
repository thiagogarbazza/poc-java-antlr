package com.github.thiagogarbazza.expressionresolver.functionresolver.sin;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.sin.FunctionResolverSin.getFunctionResolverSin;
import static org.junit.Assert.assertEquals;

public class FunctionResolverSinTest {

  private FunctionResolverSin functionResolverSin;

  @Before
  public void before() {
    functionResolverSin = getFunctionResolverSin();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("0.8939966636005579"), functionResolverSin.resolver(new BigDecimal("90")));
  }
}
