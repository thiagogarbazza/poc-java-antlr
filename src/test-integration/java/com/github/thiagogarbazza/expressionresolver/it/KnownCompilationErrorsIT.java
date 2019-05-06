package com.github.thiagogarbazza.expressionresolver.it;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.exception.SyntaxException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class KnownCompilationErrorsIT extends AbstractIT {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void verificarExpressaoIncorreta01() {
    thrown.expect(SyntaxException.class);

    toValid(new Expression("t"));
  }

  @Test
  public void verificarExpressaoIncorreta02() {
    thrown.expect(SyntaxException.class);

    toValid(new Expression("r"));
  }

  @Test
  public void verificarExpressaoIncorreta03() {
    thrown.expect(SyntaxException.class);

    toValid(new Expression("return t;"));
  }
}
