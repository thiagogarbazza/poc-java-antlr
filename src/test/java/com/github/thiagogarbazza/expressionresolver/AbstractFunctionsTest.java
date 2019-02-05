package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.domain.ExpressionContext;
import org.junit.Assert;
import org.junit.Before;

import java.math.BigDecimal;
import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;

public abstract class AbstractFunctionsTest {

  protected static final Calendar CURRENT = Calendar.getInstance();

  protected static final Result RESULT_0 = new Result(BigDecimal.valueOf(0));
  protected static final Result RESULT_1 = new Result(BigDecimal.valueOf(1));
  protected static final Result RESULT_10 = new Result(BigDecimal.valueOf(10));
  protected static final Result RESULT_100 = new Result(BigDecimal.valueOf(100));
  protected static final Result RESULT_10_0 = new Result(BigDecimal.valueOf(10.0));
  protected static final Result RESULT_10_3 = new Result(BigDecimal.valueOf(10.3));
  protected static final Result RESULT_120 = new Result(BigDecimal.valueOf(120));
  protected static final Result RESULT_144 = new Result(BigDecimal.valueOf(144));
  protected static final Result RESULT_15 = new Result(BigDecimal.valueOf(15));
  protected static final Result RESULT_1_NEGATIVE = new Result(BigDecimal.valueOf(-1));
  protected static final Result RESULT_20 = new Result(BigDecimal.valueOf(20));
  protected static final Result RESULT_2015 = new Result(BigDecimal.valueOf(2015));
  protected static final Result RESULT_20_NEGATIVE = new Result(BigDecimal.valueOf(-20));
  protected static final Result RESULT_21 = new Result(BigDecimal.valueOf(21));
  protected static final Result RESULT_25 = new Result(BigDecimal.valueOf(25));
  protected static final Result RESULT_3 = new Result(BigDecimal.valueOf(3));
  protected static final Result RESULT_4 = new Result(BigDecimal.valueOf(4));
  protected static final Result RESULT_5 = new Result(BigDecimal.valueOf(5));
  protected static final Result RESULT_50 = new Result(BigDecimal.valueOf(50));
  protected static final Result RESULT_512 = new Result(BigDecimal.valueOf(512));
  protected static final Result RESULT_5_0 = new Result(BigDecimal.valueOf(5.0));
  protected static final Result RESULT_7 = new Result(BigDecimal.valueOf(7));
  protected static final Result RESULT_7_NEGATIVE = new Result(BigDecimal.valueOf(-7));
  protected static final Result RESULT_8_NEGATIVE = new Result(BigDecimal.valueOf(-8));
  protected static final Result RESULT_9 = new Result(BigDecimal.valueOf(9));
  protected static final Result RESULT_9_96 = new Result(BigDecimal.valueOf(9.96));
  protected static final Result RESULT_ACOS_0_5 = new Result(BigDecimal.valueOf(1.0471975511965979));
  protected static final Result RESULT_ASIN_0_5 = new Result(BigDecimal.valueOf(0.5235987755982989));
  protected static final Result RESULT_ATAN_0_5 = new Result(BigDecimal.valueOf(0.4636476090008061));
  protected static final Result RESULT_COS_90 = new Result(BigDecimal.valueOf(-0.4480736161291702));
  protected static final Result RESULT_CURRENT_DAY_OF_MONTH = new Result(BigDecimal.valueOf(CURRENT.get(DAY_OF_MONTH)));
  protected static final Result RESULT_CURRENT_MONTH = new Result(BigDecimal.valueOf(CURRENT.get(Calendar.MONTH) + 1));
  protected static final Result RESULT_CURRENT_YEAR = new Result(BigDecimal.valueOf(CURRENT.get(Calendar.YEAR)));
  protected static final Result RESULT_FALSE = new Result(Boolean.FALSE);
  protected static final Result RESULT_LN_5 = new Result(BigDecimal.valueOf(1.6094379124341003));
  protected static final Result RESULT_LOG_5 = new Result(BigDecimal.valueOf(0.6989700043360189));
  protected static final Result RESULT_SIN_90 = new Result(BigDecimal.valueOf(0.8939966636005579));
  protected static final Result RESULT_SQRT_25 = new Result(BigDecimal.valueOf(5));
  protected static final Result RESULT_TAN_90 = new Result(BigDecimal.valueOf(-1.995200412208242));
  protected static final Result RESULT_TRUE = new Result(Boolean.TRUE);
  private final ExpressionInterpreter interpreter = ExpressionInterpreter.getExpressionInterpreter();
  protected ExpressionContext EXPRESSION_CONTEXT;

  @Before
  public void setUpAbstract() {
    EXPRESSION_CONTEXT = new ExpressionContext();
  }

  protected void assertException(final Expression expression, final String message) {
    try {
      interpreter.toInterpret(expression, EXPRESSION_CONTEXT);
      Assert.fail("Exception expected!");
    } catch (Exception e) {
      Assert.assertEquals("Exception messageProperty invalid.", message, e.getMessage());
    }
  }

  protected void assertExpression(final Expression expression, final Result expected) {
    final Result result = interpreter.toInterpret(expression, EXPRESSION_CONTEXT);
    Assert.assertEquals("Result value invalid.", expected, result);
  }
}
