package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.exception.SyntaxExpressionException;
import com.github.thiagogarbazza.expressionresolver.interpreter.compile.CompilerService;
import com.github.thiagogarbazza.expressionresolver.interpreter.runnable.RunnableService;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.github.thiagogarbazza.expressionresolver.Data4T.EXPRESSION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ExpressionInterpreterTest {

  @Mock
  private CompilerService compilerService;
  @InjectMocks
  private ExpressionInterpreter expressionInterpreter;
  @Mock
  private RunnableService runnableService;

  @BeforeEach
  void beforeEach() {
    openMocks(this);
  }

  @Nested
  class MethodToInterpret {

    @Test
    void verifyToInterpret() {
      ExpressionContext context = mock(ExpressionContext.class);
      Result result = mock(Result.class);
      ParseTree parseTree = mock(ParseTree.class);

      when(compilerService.compile(EXPRESSION)).thenReturn(parseTree);
      when(runnableService.run(parseTree, context)).thenReturn(result);

      assertEquals(result, expressionInterpreter.toInterpret(EXPRESSION, context));

      verify(compilerService, timeout(1)).compile(EXPRESSION);
    }

    @Test
    void verifyToInterpretContextNotBeNull() {
      final Exception exception = assertThrows(NullPointerException.class, () -> expressionInterpreter.toInterpret(EXPRESSION, null));
      assertEquals("Expression context can not be null.", exception.getMessage());
    }

    @Test
    void verifyToInterpretExpressionNotBeNull() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionInterpreter.toInterpret(null, null));
      assertEquals("Expression can not be null or empty.", exception.getMessage());
    }
  }

  @Nested
  class MethodToValid {

    @Test
    void verifyToValidExpressionNotBeNull() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionInterpreter.toValid(null));
      assertEquals("Expression can not be null or empty.", exception.getMessage());
    }
  }
}
