package com.github.thiagogarbazza.expressionresolver.resolver.comparestring;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.comparestring.ResolverFunctionCompareString.getResolverFunctionCompareString;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionCompareStringTest {

  private ResolverFunctionCompareString resolverFunctionCompareString;

  @Before
  public void before() {
    resolverFunctionCompareString = getResolverFunctionCompareString();
  }

  @Test
  public void verifyResolverEquals() {
    String left = "A";
    String right = "A";

    assertEquals(new BigDecimal("0"), resolverFunctionCompareString.resolver(left, right));
  }

  @Test
  public void verifyResolverGreater() {
    String left = "B";
    String right = "A";

    assertEquals(new BigDecimal("1"), resolverFunctionCompareString.resolver(left, right));
  }

  @Test
  public void verifyResolverLess() {
    String left = "A";
    String right = "B";

    assertEquals(new BigDecimal("-1"), resolverFunctionCompareString.resolver(left, right));
  }
}
