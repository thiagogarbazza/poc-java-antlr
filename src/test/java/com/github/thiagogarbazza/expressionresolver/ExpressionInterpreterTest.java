package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.interpreter.compile.Compiler;
import com.github.thiagogarbazza.expressionresolver.interpreter.runnable.Runnable;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExpressionInterpreterTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  @Mock
  private Compiler compiler;
  @InjectMocks
  private ExpressionInterpreter expressionInterpreter;
  @Mock
  private Runnable runnable;

  @Before
  public void before() {
    initMocks(this);
  }

  @Test
  public void verifyToInterpret() {
    Expression expression = mock(Expression.class);
    ExpressionContext context = mock(ExpressionContext.class);
    Result result = mock(Result.class);
    ParseTree parseTree = mock(ParseTree.class);

    when(compiler.compile(expression)).thenReturn(parseTree);
    when(runnable.run(parseTree, context)).thenReturn(result);

    assertEquals(result, expressionInterpreter.toInterpret(expression, context));

    verify(compiler, timeout(1)).compile(expression);
  }

  @Test
  public void verifyToInterpretContextNotBeNull() {
    thrown.expectMessage("Expression context can not be null.");
    thrown.expect(NullPointerException.class);

    expressionInterpreter.toInterpret(new Expression("return 5;"), null);
  }

  @Test
  public void verifyToInterpretExpressionNotBeNull() {
    thrown.expectMessage("Expression can not be null or empty.");
    thrown.expect(NullPointerException.class);

    expressionInterpreter.toInterpret(null, new ExpressionContext());
  }

  @Test
  public void verifyToValid() {
    Expression expression = mock(Expression.class);
    ParseTree parseTree = mock(ParseTree.class);

    when(compiler.compile(expression)).thenReturn(parseTree);

    expressionInterpreter.toValid(expression);

    verify(compiler, timeout(1)).compile(expression);
  }

  @Test
  public void verifyToValidExpressionNotBeNull() {
    thrown.expectMessage("Expression can not be null or empty.");
    thrown.expect(NullPointerException.class);

    expressionInterpreter.toValid(null);
  }
}
