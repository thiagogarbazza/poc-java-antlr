package com.github.thiagogarbazza.expressionresolver.it;

import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.ExpressionInterpreter;
import com.github.thiagogarbazza.expressionresolver.Result;
import com.github.thiagogarbazza.expressionresolver.exception.SyntaxExpressionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionInterpreterIntegrationTest {

  private ExpressionInterpreter expressionInterpreter;

  @BeforeEach
  void before() {
    expressionInterpreter = ExpressionInterpreter.getExpressionInterpreter();
  }

  @Nested
  class MethodToInterpret {

    @Test
    void verifyExpressionInvalid() {
      final Exception exception = assertThrows(SyntaxExpressionException.class,
        () -> expressionInterpreter.toInterpret("return 5 * 5", new ExpressionContext()));
      assertEquals("In position 1:12 missing ';' at '<EOF>'.", exception.getMessage());
    }

    @Test
    void verifyExpressionValid() {
      assertEquals(new Result(toBigDecimal("25")), expressionInterpreter.toInterpret("return 5 * 5;", new ExpressionContext()));
    }
  }

  @Nested
  class MethodToValid {

    @Test
    void verifyExpressionInvalid() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionInterpreter.toValid("return 5 * 5"));
      assertEquals("In position 1:12 missing ';' at '<EOF>'.", exception.getMessage());
    }

    @Test
    void verifyExpressionValid() {
      expressionInterpreter.toValid("return 5 * 5;");
    }
  }
}
