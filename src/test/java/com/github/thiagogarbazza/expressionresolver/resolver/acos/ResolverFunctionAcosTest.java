package com.github.thiagogarbazza.expressionresolver.resolver.acos;

import com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.toBigDecimal;
import static com.github.thiagogarbazza.expressionresolver.resolver.acos.ResolverFunctionAcos.getResolverFunctionAcos;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionAcosTest {

  private ResolverFunctionAcos resolverFunctionAcos;

  @Before
  public void before() {
    resolverFunctionAcos = getResolverFunctionAcos();
  }

  @Test
  public void verifyResolver() {
    assertEquals(toBigDecimal("0.45102681179626236"), resolverFunctionAcos.resolver(new BigDecimal("0.9")));
  }
}
