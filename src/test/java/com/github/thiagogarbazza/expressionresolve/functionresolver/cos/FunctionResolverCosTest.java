package com.github.thiagogarbazza.expressionresolve.functionresolver.cos;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.cos.FunctionResolverCos.getFunctionResolverCos;
import static org.junit.Assert.assertEquals;

public class FunctionResolverCosTest {

  private FunctionResolverCos functionResolverCos;

  @Before
  public void before() {
    functionResolverCos = getFunctionResolverCos();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("-0.4480736161291702"), functionResolverCos.resolver(new BigDecimal("90")));
  }
}
