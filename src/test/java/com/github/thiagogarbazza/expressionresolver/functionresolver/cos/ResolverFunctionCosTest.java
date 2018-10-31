package com.github.thiagogarbazza.expressionresolver.functionresolver.cos;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.cos.ResolverFunctionCos.getResolverFunctionCos;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionCosTest {

  private ResolverFunctionCos resolverFunctionCos;

  @Before
  public void before() {
    resolverFunctionCos = getResolverFunctionCos();
  }

  @Test
  public void verifyResolver() {
    assertEquals(new BigDecimal("-0.4480736161291702"), resolverFunctionCos.resolver(new BigDecimal("90")));
  }
}
