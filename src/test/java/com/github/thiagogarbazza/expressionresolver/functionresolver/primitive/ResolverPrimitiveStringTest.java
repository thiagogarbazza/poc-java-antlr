package com.github.thiagogarbazza.expressionresolver.functionresolver.primitive;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ResolverPrimitiveStringTest {

  @InjectMocks
  private ResolverPrimitiveString resolverPrimitiveString;

  @Before
  public void before() {
    initMocks(this);
  }

  @Test
  public void verifyResult() {
    assertEquals("any-string", resolverPrimitiveString.resolver("any-string"));
  }

  @Test
  public void verifyResultDoubleQuote() {
    assertEquals("any-string", resolverPrimitiveString.resolver("\"any-string\""));
  }

  @Test
  public void verifyResultSingleQuote() {
    assertEquals("any-string", resolverPrimitiveString.resolver("'any-string'"));
  }
}
