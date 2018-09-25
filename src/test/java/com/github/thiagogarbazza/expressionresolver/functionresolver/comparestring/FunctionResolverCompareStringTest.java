package com.github.thiagogarbazza.expressionresolver.functionresolver.comparestring;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.comparestring.FunctionResolverCompareString.getFunctionResolverCompareString;
import static org.junit.Assert.assertEquals;

public class FunctionResolverCompareStringTest {

  private FunctionResolverCompareString functionResolverCompareString;

  @Before
  public void before() {
    functionResolverCompareString = getFunctionResolverCompareString();
  }

  @Test
  public void verifyResolverEquals() {
    String left = "A";
    String right = "A";

    assertEquals(new BigDecimal("0"), functionResolverCompareString.resolver(left, right));
  }

  @Test
  public void verifyResolverGreater() {
    String left = "B";
    String right = "A";

    assertEquals(new BigDecimal("1"), functionResolverCompareString.resolver(left, right));
  }

  @Test
  public void verifyResolverLess() {
    String left = "A";
    String right = "B";

    assertEquals(new BigDecimal("-1"), functionResolverCompareString.resolver(left, right));
  }
}
